package ethos.runehub.content.instance;

import ethos.Config;
import ethos.Server;
import ethos.model.players.Player;
import ethos.runehub.TimeUtils;
import ethos.runehub.content.instance.impl.BossArenaInstance;
import ethos.runehub.entity.node.Node;
import ethos.runehub.event.BossInstanceEvent;
import ethos.runehub.ui.impl.tab.player.InstanceTab;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * move timestamps from atttributes to here
 */
public class BossArenaInstanceController {

    private static BossArenaInstanceController instance = null;

    public static BossArenaInstanceController getInstance() {
        if (instance == null)
            instance = new BossArenaInstanceController();
        return instance;
    }

    public void createInstanceForPlayer(Player player) {
        if (player.getAttributes().getInstanceId() == -1) {
            final int instanceIndex = this.getFirstAvailableInstanceIndex();
            if (instanceIndex != -1) {
                final int floorId = (instanceIndex * 4) + 6;
                final BossArenaInstance instance = new BossArenaInstance(
                        instanceIndex,
                        player,
                        new Rectangle(new Point(1807, 5135), new Point(1841, 5168)),
                        Duration.of(10, ChronoUnit.MINUTES).toMillis(),
                        System.currentTimeMillis(),
                        floorId);
                player.getAttributes().setInstanceId(instanceIndex);
                instances[instanceIndex] = instance;
                player.sendUI(new InstanceTab(player, instance));
                Node portalNode = new Node(13620, 3107, 3227, 0, 0, 10);
                player.getAttributes().addInstanceNode(12338, portalNode);
                player.getPA().checkObjectSpawn(portalNode);
                Server.getEventHandler().submit(new BossInstanceEvent(instanceIndex, instance, 1000));
            } else {
                player.sendMessage("There are no available instances. Please try again later.");
            }
        } else {
            player.sendMessage("You already have an active instance.");
        }
    }

    public void createInstance(BossArenaInstance instance) {
        instances[instance.getId()] = instance;
        instance.getOwner().sendUI(new InstanceTab(instance.getOwner(), instance));
        Node portalNode = new Node(13620, 3107, 3227, 0, 0, 10);
        instance.getOwner().getAttributes().addInstanceNode(12338, portalNode);
        instance.getOwner().getPA().checkObjectSpawn(portalNode);
        Server.getEventHandler().submit(new BossInstanceEvent(instance.getId(), instance, 1000));
    }

    public void closeInstanceForPlayer(Player player) {
        if (player.getAttributes().getInstanceId() != -1 && getInstance(player.getAttributes().getInstanceId()) != null) {
            if (getInstance(player.getAttributes().getInstanceId()).getArea().contains(new Point(player.absX, player.absY))) {
                player.getPA().movePlayer(Config.RESPAWN_X, Config.RESPAWN_Y, 0);
            }
            player.sendMessage("Your active instance has closed.");
            Server.getEventHandler().stop("boss-instance-" + player.getAttributes().getInstanceId());
            Node portalNode = new Node(13620, 3107, 3227, 0, 0, 10);
            Node emptyPortalNode = new Node(13637, 3107, 3227, 0, 0, 10);
            player.getAttributes().removeInstanceNode(12338, portalNode);
            player.getPA().checkObjectSpawn(emptyPortalNode);
            instances[player.getAttributes().getInstanceId()].getSpawnedNpcs().forEach(npc -> {
                npc.isDead = true;
                npc.applyDead = true;
            });
            instances[player.getAttributes().getInstanceId()] = null;

            player.getAttributes().setInstanceId(-1);
            player.setSidebarInterface(13, 47500);


        }
    }

    public BossArenaInstance getInstance(int id) {
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

    private BossArenaInstanceController() {
        this.instances = new BossArenaInstance[50];
    }


    //    private final Map<Integer, BossArenaInstance> instanceMap;
    private final BossArenaInstance[] instances;
}
