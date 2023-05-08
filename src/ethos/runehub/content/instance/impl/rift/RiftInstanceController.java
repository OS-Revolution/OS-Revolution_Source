package ethos.runehub.content.instance.impl.rift;

import ethos.Config;
import ethos.Server;
import ethos.model.players.Player;
import ethos.runehub.content.rift.RiftDifficulty;
import ethos.runehub.content.rift.RiftFloor;
import ethos.runehub.content.rift.RiftFloorDAO;
import ethos.runehub.entity.node.Node;
import ethos.runehub.event.RiftInstanceEvent;
import ethos.runehub.skill.Skill;
import ethos.runehub.ui.impl.tab.player.InstanceTab;
import ethos.runehub.ui.impl.tab.player.NephalemRiftTab;
import org.runehub.api.util.math.geometry.Point;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class RiftInstanceController {

    private static RiftInstanceController instance = null;

    public static RiftInstanceController getInstance() {
        if (instance == null)
            instance = new RiftInstanceController();
        return instance;
    }

    public void createNephalemRiftInstanceForPlayer(Player player, RiftDifficulty difficulty) {
        if (player.getAttributes().getInstanceId() == -1) {
            final int instanceIndex = this.getFirstAvailableInstanceIndex();
            if (instanceIndex != -1) {
                final int floorId = (instanceIndex * 4) + 6;
                final RiftFloor floor = RiftFloorDAO.getInstance().read(this.getStartingFloor());
                final RiftInstance instance = new NephalemRiftInstance(
                        instanceIndex,
                        player,
                        floor.getBoundingBox(),
                        Duration.of(10, ChronoUnit.MINUTES).toMillis(),
                        System.currentTimeMillis(),
                        floorId,
                        difficulty,
                        5,
                        70,
                        floor
                );
                player.getAttributes().setRiftInstance(instance);
                player.getAttributes().setInstanceId(instanceIndex);
                player.getAttributes().setInRift(true);
                player.getAttributes().setStartingFloor(floor.getFloorId());

                instances[instanceIndex] = instance;
                player.sendUI(new NephalemRiftTab(player, instance));
                instance.start(player);
                Server.getEventHandler().submit(new RiftInstanceEvent(instanceIndex, instance, 1000));
            } else {
                player.sendMessage("There are no available instances. Please try again later.");
            }
        } else {
            player.sendMessage("You already have an active instance.");
        }
    }

    public void closeInstanceForPlayer(Player player) {
        if (player.getAttributes().getInstanceId() != -1) {
            if (getInstance(player.getAttributes().getInstanceId()).getArea().contains(new Point(player.absX, player.absY))) {
                player.getPA().movePlayer(Config.RESPAWN_X, Config.RESPAWN_Y, 0);
            }
            player.sendMessage("Your active instance has closed.");
            Server.getEventHandler().stop("rift-instance-" + player.getAttributes().getInstanceId());
//            Node portalNode = new Node(13620, 3107, 3227, 0, 0, 10);
//            Node emptyPortalNode = new Node(13637, 3107, 3227, 0, 0, 10);
//            player.getAttributes().removeInstanceNode(12338, portalNode);
//            player.getPA().checkObjectSpawn(emptyPortalNode);
            instances[player.getAttributes().getInstanceId()].despawnAllMobs();
            instances[player.getAttributes().getInstanceId()].leave(player);
            instances[player.getAttributes().getInstanceId()] = null;
            player.getAttributes().setInstanceId(-1);
            player.getAttributes().setInRift(false);
            player.getAttributes().setStartingFloor(-1);
            player.getAttributes().setRiftInstance(null);
            player.setSidebarInterface(13, 47500);


        }
    }

    public void createInstance(RiftInstance instance) {
        instances[instance.getId()] = instance;
        instance.getOwner().sendUI(new InstanceTab(instance.getOwner(), instance));
        Node portalNode = new Node(13620, 3107, 3227, 0, 0, 10);
        instance.getOwner().getAttributes().addInstanceNode(12338, portalNode);
        instance.getOwner().getPA().checkObjectSpawn(portalNode);
        Server.getEventHandler().submit(new RiftInstanceEvent(instance.getId(), instance, 1000));
    }

    public RiftInstance getInstance(int id) {
        if (id != -1)
            return instances[id];
        return null;
    }


    public int getFirstAvailableInstanceIndex() {
        for (int i = 0; i < instances.length; i++) {
            if (instances[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int getStartingFloor() {
        return Skill.SKILL_RANDOM.nextInt(4);
    }

    public RiftInstanceController() {
        this.instances = new RiftInstance[50];
    }

    private final RiftInstance[] instances;
}
