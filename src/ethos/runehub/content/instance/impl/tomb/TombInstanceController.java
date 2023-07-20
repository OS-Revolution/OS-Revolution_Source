package ethos.runehub.content.instance.impl.tomb;

import ethos.Config;
import ethos.Server;
import ethos.clip.Region;
import ethos.model.npcs.NPC;
import ethos.model.npcs.NPCHandler;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.content.instance.impl.BossArenaInstance;
import ethos.runehub.content.rift.RiftDifficulty;
import ethos.runehub.entity.node.Node;
import ethos.runehub.event.BossInstanceEvent;
import ethos.runehub.event.TombRaiderInstanceEvent;
import ethos.runehub.skill.Skill;
import ethos.runehub.ui.impl.tab.player.InstanceTab;
import ethos.runehub.ui.impl.tab.player.TombRaiderInstanceTab;
import ethos.util.Misc;
import org.runehub.api.model.math.impl.IntegerRange;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class TombInstanceController {

    private static final int MOB_CAP = 50;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";

    private static TombInstanceController instance = null;

    public static TombInstanceController getInstance() {
        if (instance == null)
            instance = new TombInstanceController();
        return instance;
    }


    private String generateRandomWord(int minLength, int maxLength) {
        int length = minLength + Misc.random(maxLength - minLength + 1);
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = new IntegerRange(0,CHARACTERS.length() - 1).getRandomValue();
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }


    public boolean createTombRaiderPortal(Player player, int nodeX, int nodeY, RiftDifficulty difficulty) {
        Node portalNode = new Node(13619, nodeX, nodeY, 0, 0, 10);
        player.getAttributes().addInstanceNode(RunehubUtils.getRegionId(nodeX, nodeY), portalNode);
        player.getPA().checkObjectSpawn(portalNode);
        this.startInstance(player, difficulty);
        return true;
    }

    public void startInstance(Player player, RiftDifficulty difficulty) {
        if (player.getAttributes().getInstanceId() == -1) {
            final int instanceIndex = this.getFirstAvailableInstanceIndex();
            if (instanceIndex != -1) {
                final int floorId = (instanceIndex * 4); //+ 6;
                final TombInstance instance = new TombInstance(
                        instanceIndex,
                        player,
                        Duration.of(10, ChronoUnit.MINUTES).toMillis(),
                        System.currentTimeMillis(),
                        floorId,
                        generateRandomWord(4, 7),
                        difficulty);
                this.spawnMobs(instance);
                player.getAttributes().setInstanceId(instanceIndex);
                player.getAttributes().setActiveInstance(instance);
                instances[instanceIndex] = instance;
                player.sendUI(new TombRaiderInstanceTab(player, instance));
                Server.getEventHandler().submit(new TombRaiderInstanceEvent(instanceIndex, instance, 1000));
            } else {
                player.sendMessage("There are no available instances. Please try again later.");
            }
        } else {
            player.sendMessage("You already have an active instance.");
        }
    }

    public void closeInstanceForPlayer(Player player) {
        if (player.getAttributes().getInstanceId() != -1 && getInstance(player.getAttributes().getInstanceId()) != null) {
            if (getInstance(player.getAttributes().getInstanceId()).getArea().contains(new Point(player.absX, player.absY))) {
                player.getPA().movePlayer(Config.RESPAWN_X, Config.RESPAWN_Y, 0);
            }
            player.sendMessage("Your active instance has closed.");
            Server.getEventHandler().stop("tomb-instance-" + player.getAttributes().getInstanceId());
            Node portalNode = new Node(13619, 3109, 3227, 0, 0, 10);
            Node emptyPortalNode = new Node(13638, 3109, 3227, 0, 0, 10);
            player.getAttributes().removeInstanceNode(12338, portalNode);
            player.getAttributes().dropNodesForRegion(12945);
            player.getPA().checkObjectSpawn(emptyPortalNode);
//            instances[player.getAttributes().getInstanceId()].getSpawnedNpcs().forEach(npc -> {
//                npc.isDead = true;
//                npc.applyDead = true;
//            });
            instances[player.getAttributes().getInstanceId()] = null;

            player.getAttributes().setInstanceId(-1);
            player.setSidebarInterface(13, 47500);


        }
    }

    public void searchSarcophagus(int instanceId, int nodeId, int nodeX, int nodeY) {
        TombInstance instance = this.getInstance(instanceId);
        if (!instance.isPasswordFound()) {
            int face = Region.getWorldObject(nodeId, nodeX, nodeY, instance.getOwner().heightLevel).get().face;
            Node openSarcophagusNode = new Node(6630, nodeX, nodeY, instance.getOwner().heightLevel, face, 0);
            instance.getOwner().getAttributes().addInstanceNode(RunehubUtils.getRegionId(nodeX, nodeY), openSarcophagusNode);
            instance.getOwner().getPA().checkObjectSpawn(openSarcophagusNode);
            if (Misc.random(25) <= 1) {
                instance.setPasswordFound(true);
                instance.getOwner().sendMessage("^Tomb_Raider You've found an ancient inscription: $" + instance.getPassword());
            } else {
                instance.getOwner().sendMessage("You dust off the sarcophagus, but find nothing unusual.");
            }
        } else {
            instance.getOwner().sendMessage("You've already found the inscription you came for.");
        }
    }

    public void enterTombPassword(int instanceId, String password) {
        TombInstance instance = this.getInstance(instanceId);
        if (password.equalsIgnoreCase(instance.getPassword())) {
            instance.getOwner().getPA().movePlayer(instance.getOwner().absX, instance.getOwner().absY - 2, instance.getFloodId());
            instance.getOwner().sendMessage("You enter the heart of the tomb and the doors slam shut.");
        } else {
            instance.getOwner().sendMessage("That is incorrect!");
            instance.getOwner().getPA().movePlayer(3232, 9294, instance.getFloodId());
        }
    }

    public TombInstance getInstance(int id) {
        if (id != -1)
            return instances[id];
        return null;
    }

    private void spawnMobs(TombInstance instance) {
        final Rectangle bossArea = new Rectangle(
                new Point(3226,9309),
                new Point(3240,9321)
        );
        instance.getArea().getAllPoints().stream()
                .filter(point -> Region.getClipping(point.getX(), point.getY(), instance.getFloodId()) == 0)
                .filter(point -> instance.getSpawnedNpcs().size() < 50)
                .filter(point -> bossArea.getAllPoints().stream().noneMatch(bPoint -> bPoint.getX() == point.getX() && bPoint.getY() == point.getY()))
                .forEach(point -> {
                    if (Misc.random(100) <= 8) {
                        spawnBasicMob(instance, point);
                    }
                    if (Misc.random(100) >= 99) {
                        spawnEliteMob(instance,point);
                    }
                });

    }

    private void spawnBasicMob(TombInstance instance, Point point) {
        final int baseHp = 30;
        final int maxHit = 5;
        final int baseAttack = 50;
        final int baseDefence = 30;
        NPC npc = NPCHandler.spawnRiftNpc(
                717,
                point.getX(),
                point.getY(),
                instance.getFloodId(),
                1,
                instance.getDifficulty().ordinal() > 0 ? (int) (baseHp + (instance.getDifficulty().getRewardBonus() * 0.5)) : baseHp,
                instance.getDifficulty().ordinal() > 0 ? maxHit * instance.getDifficulty().ordinal() : maxHit,
                instance.getDifficulty().ordinal() > 0 ? (int) (baseAttack + (instance.getDifficulty().getRewardBonus() * 0.25)) : baseAttack,
                instance.getDifficulty().ordinal() > 0 ? (int) (baseDefence + (instance.getDifficulty().getRewardBonus() * 0.25)) : baseDefence
        );
        instance.getSpawnedNpcs().add(npc);
    }

    private void spawnEliteMob(TombInstance instance, Point point) {
        final int baseHp = 50;
        final int maxHit = 7;
        final int baseAttack = 75;
        final int baseDefence = 50;
        NPC npc = NPCHandler.spawnRiftNpc(
                799,
                point.getX(),
                point.getY(),
                instance.getFloodId(),
                1,
                instance.getDifficulty().ordinal() > 0 ? (int) (baseHp + (instance.getDifficulty().getRewardBonus() * 0.5)) : baseHp,
                instance.getDifficulty().ordinal() > 0 ? maxHit * instance.getDifficulty().ordinal() : maxHit,
                instance.getDifficulty().ordinal() > 0 ? (int) (baseAttack + (instance.getDifficulty().getRewardBonus() * 0.25)) : baseAttack,
                instance.getDifficulty().ordinal() > 0 ? (int) (baseDefence + (instance.getDifficulty().getRewardBonus() * 0.25)) : baseDefence
        );
        instance.getSpawnedNpcs().add(npc);
    }

    private int getFirstAvailableInstanceIndex() {
        for (int i = 0; i < instances.length; i++) {
            if (instances[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private TombInstanceController() {
        this.instances = new TombInstance[50];
    }

    private final TombInstance[] instances;
}
