package ethos.runehub.entity.combat.impl;

import ethos.model.npcs.NPC;
import ethos.model.players.Player;
import ethos.runehub.entity.combat.CombatController;
import ethos.runehub.entity.item.equipment.EquipmentCache;
import ethos.runehub.entity.item.equipment.EquipmentSlot;
import ethos.runehub.entity.item.equipment.Weapon;
import ethos.runehub.entity.item.equipment.WeaponCache;
import ethos.runehub.entity.mob.hostile.HostileMobContext;
import ethos.runehub.entity.mob.hostile.HostileMobIdContextLoader;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

import java.util.logging.Logger;

public class PvECombatController extends CombatController<Player, NPC> {

    @Override
    public void target(NPC entity) {
        if (canTarget(entity)) {
            final int mainHandId = targetingEntity.getEquipmentIdInSlot(EquipmentSlot.MAIN_HAND);
            this.setTargetedEntity(entity);
            if (mainHandId > 0) {
                final Weapon weapon = WeaponCache.getInstance().read(mainHandId);
                if (weapon.getCombatType() == MELEE) {

                } else if (weapon.getCombatType() == RANGED) {

                } else if (weapon.getCombatType() == MAGIC) {

                }
            }
            this.follow(entity);
        }
    }

    private int getDistance() {
        final int mainHandId = targetingEntity.getEquipmentIdInSlot(EquipmentSlot.MAIN_HAND);
        if (mainHandId > 0) {
            return EquipmentCache.getInstance().read(mainHandId).getRange();
        }

        return 1;
    }



    private void follow(NPC entity) {
        final HostileMobContext ctx = HostileMobIdContextLoader.getInstance().read(entity.npcType);

        if (ctx != null) {
            targetingEntity.faceUpdate(entity.getIndex());
            if (targetingEntity.getPA().withinRange(entity)) {
                targetingEntity.stopMovement();
            }
//            targetingEntity.faceNPC(entity.getIndex());
//            targetingEntity.getPA().playerWalk(entity.getX(),entity.getY());
//            while (new Location(targetingEntity.getX(),targetingEntity.heightLevel,targetingEntity.getY()).distanceFrom(new Location(entity.getX(),entity.heightLevel,entity.getY())) > (playerRange + entitySize)) {
//                System.out.println("Approaching");
//                targetingEntity.getPA().playerWalk(entity.getX(),entity.getY());
//            }

        } else {
            Logger.getGlobal().severe("Attempting to target unregistered entity [" + entity.npcType + "]");
        }
    }

    @Override
    protected boolean canTarget(NPC entity) {
        return true;
    }

    public PvECombatController(Player targetingEntity) {
        super(targetingEntity);
    }
}
