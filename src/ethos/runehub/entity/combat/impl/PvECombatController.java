package ethos.runehub.entity.combat.impl;

import ethos.model.npcs.NPC;
import ethos.model.players.Player;
import ethos.runehub.entity.combat.CombatController;
import ethos.runehub.entity.item.equipment.EquipmentSlot;
import ethos.runehub.entity.mob.hostile.HostileMobContext;
import ethos.runehub.entity.mob.hostile.HostileMobIdContextLoader;
import org.runehub.api.model.world.region.location.Location;

import java.util.logging.Logger;

public class PvECombatController extends CombatController<Player, NPC> {

    @Override
    public void target(NPC entity) {
        if (canTarget(entity)) {
            System.out.println("Targeting: " + entity.npcType);
            this.setTargetedEntity(entity);
            this.follow(entity);
        }
    }

    private void follow(NPC entity) {
//        final HostileMobContext ctx = HostileMobIdContextLoader.getInstance().read(entity.npcType);
//        if (ctx != null) {
//            final int entitySize = ctx.getSize();
//            int playerRange = targetingEntity.getEquipmentInSlot(EquipmentSlot.MAIN_HAND).isPresent() ?
//                    targetingEntity.getEquipmentInSlot(EquipmentSlot.MAIN_HAND).get().getRange()
//                    : 0;
//            System.out.println("Size: " + entitySize);
//            System.out.println("Range: " + playerRange);
//            targetingEntity.faceNPC(entity.getIndex());
////            targetingEntity.getPA().playerWalk(entity.getX(),entity.getY());
////            while (new Location(targetingEntity.getX(),targetingEntity.heightLevel,targetingEntity.getY()).distanceFrom(new Location(entity.getX(),entity.heightLevel,entity.getY())) > (playerRange + entitySize)) {
////                System.out.println("Approaching");
////                targetingEntity.getPA().playerWalk(entity.getX(),entity.getY());
////            }
//
//        } else {
//            Logger.getGlobal().severe("Attempting to target unregistered entity [" + entity.npcType + "]");
//        }
    }

    @Override
    protected boolean canTarget(NPC entity) {
        return true;
    }

    public PvECombatController(Player targetingEntity) {
        super(targetingEntity);
    }
}
