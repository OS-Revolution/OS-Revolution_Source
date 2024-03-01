package ethos.runehub.event;

import ethos.event.Event;
import ethos.model.npcs.NPC;
import ethos.model.npcs.NPCHandler;
import ethos.runehub.content.instance.BossArenaInstanceController;
import ethos.runehub.content.instance.impl.BossArenaInstance;
import ethos.runehub.content.instance.impl.tomb.TombInstance;
import ethos.runehub.content.instance.impl.tomb.TombInstanceController;
import ethos.runehub.skill.Skill;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

public class TombRaiderInstanceEvent extends Event<TombInstance> {


    @Override
    public void execute() {
        TombInstanceController.getInstance().closeInstanceForPlayer(attachment.getOwner());
    }

//    @Override
//    public void update() {
//        super.update();
//        if (elapsed == 10) {
//            attachment.getOwner().sendMessage("The boss has appeared!");
//            Rectangle spawnArea = new Rectangle(new Point(1817, 5147), new Point(1832, 5156));
//            Point spawnPoint = spawnArea.getAllPoints().get(Skill.SKILL_RANDOM.nextInt(spawnArea.getAllPoints().size()));
//            NPC npc = NPCHandler.spawnRiftNpc(
//                    attachment.getInstanceBoss().getNpcId(),
//                    spawnPoint.getX(),
//                    spawnPoint.getY(),
//                    attachment.getFloodId(),
//                    1,
//                    attachment.getInstanceBoss().getHitpoints(),
//                    attachment.getInstanceBoss().getMaxHit(),
//                    attachment.getInstanceBoss().getAttack(),
//                    attachment.getInstanceBoss().getDefence());
//            if (npc != null) {
//                npc.setRespawnTicks(attachment.getInstanceBoss().getRespawnTime());
//                npc.setInstanceSpawn(true);
//            }
//
//        } else if (elapsed < 10 && attachment.getArea().contains(new Point(attachment.getOwner().absX, attachment.getOwner().absY))) {
//            int remaining = 10 - elapsed;
//            attachment.getOwner().sendMessage("Boss will spawn in $" + remaining);
//        }
//    }

    public TombRaiderInstanceEvent(int id, TombInstance attachment, int ticks) {
        super("tomb-instance-" + id, attachment, ticks);
    }
}