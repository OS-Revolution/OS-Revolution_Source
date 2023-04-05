package ethos.runehub.entity.combat.impl;

import ethos.Server;
import ethos.clip.Region;
import ethos.event.impl.PoisonEvent;
import ethos.model.entity.HealthStatus;
import ethos.model.npcs.NPC;
import ethos.model.npcs.NPCHandler;
import ethos.model.players.Player;
import ethos.model.players.combat.CombatType;
import ethos.runehub.entity.combat.CombatMechanics;
import ethos.runehub.entity.mob.hostile.HostileMobContext;
import ethos.runehub.entity.mob.hostile.HostileMobIdContextLoader;
import ethos.util.Misc;

import java.util.Optional;

public class BryophytaCombatMechanics implements CombatMechanics {

    private static BryophytaCombatMechanics instance = null;

    public static BryophytaCombatMechanics getInstance() {
        if (instance == null)
            instance = new BryophytaCombatMechanics();
        return instance;
    }

    @Override
    public void onHit() {

    }

    @Override
    public void onAttack(Player player, NPC npc) {
        //16 magic 17 range 18 melee
        int attackStyleRoll = Misc.random(100);
        if (attackStyleRoll > 40) {
            npc.attackType = CombatType.MELEE;
            npc.projectileId = -1;
            npc.maxHit = 16;
//            npc.hitDelayTimer = 0;
            if (Misc.random(100) >= 25) {
                player.getHealth().proposeStatus(HealthStatus.POISON,8,Optional.of(npc));
            }
        } else {
//            npc.startAnimation(7173);
            npc.attackType = CombatType.MAGE;
            npc.projectileId = 124;
            npc.hitDelayTimer = 3;
            npc.maxHit = 16;
        }
//        } else if (!growthlingsAlive){
//            npc.attackType = CombatType.RANGE;
//            for (int i = 0; i < 3; i++) {
//                spawnGrowthling(player);
//            }
//
//        }
    }

    @Override
    public void onDeath() {

    }

    private void spawnGrowthling(Player player) {
        HostileMobContext ctx = HostileMobIdContextLoader.getInstance().read(8194);
        if (!Region.blockedEastNPC(player.absX, player.absY, player.heightLevel, 1)) {
            NPCHandler.spawnNpc(8194, player.absX + 1, player.absY, player.heightLevel, 1, ctx.getHitpoints(), ctx.getMaxHit(), ctx.getAttackLevel(), ctx.getDefenceLevel());
        } else if (!Region.blockedNorthEastNPC(player.absX, player.absY, player.heightLevel, 1)) {
            NPCHandler.spawnNpc(8194, player.absX + 1, player.absY + 1, player.heightLevel, 1, ctx.getHitpoints(), ctx.getMaxHit(), ctx.getAttackLevel(), ctx.getDefenceLevel());
        } else if (!Region.blockedNorthNPC(player.absX, player.absY, player.heightLevel, 1)) {
            NPCHandler.spawnNpc(8194, player.absX, player.absY + 1, player.heightLevel, 1, ctx.getHitpoints(), ctx.getMaxHit(), ctx.getAttackLevel(), ctx.getDefenceLevel());
        } else if (!Region.blockedNorthWestNPC(player.absX, player.absY, player.heightLevel, 1)) {
            NPCHandler.spawnNpc(8194, player.absX - 1, player.absY + 1, player.heightLevel, 1, ctx.getHitpoints(), ctx.getMaxHit(), ctx.getAttackLevel(), ctx.getDefenceLevel());
        } else if (!Region.blockedWestNPC(player.absX, player.absY, player.heightLevel, 1)) {
            NPCHandler.spawnNpc(8194, player.absX - 1, player.absY, player.heightLevel, 1, ctx.getHitpoints(), ctx.getMaxHit(), ctx.getAttackLevel(), ctx.getDefenceLevel());
        } else if (!Region.blockedSouthWestNPC(player.absX, player.absY, player.heightLevel, 1)) {
            NPCHandler.spawnNpc(8194, player.absX - 1, player.absY - 1, player.heightLevel, 1, ctx.getHitpoints(), ctx.getMaxHit(), ctx.getAttackLevel(), ctx.getDefenceLevel());
        } else if (!Region.blockedSouthNPC(player.absX, player.absY, player.heightLevel, 1)) {
            NPCHandler.spawnNpc(8194, player.absX, player.absY - 1, player.heightLevel, 1, ctx.getHitpoints(), ctx.getMaxHit(), ctx.getAttackLevel(), ctx.getDefenceLevel());
        } else if (!Region.blockedSouthEastNPC(player.absX, player.absY, player.heightLevel, 1)) {
            NPCHandler.spawnNpc(8194, player.absX + 1, player.absY - 1, player.heightLevel, 1, ctx.getHitpoints(), ctx.getMaxHit(), ctx.getAttackLevel(), ctx.getDefenceLevel());
        }
    }

    private BryophytaCombatMechanics() {

    }

    private boolean growthlingsAlive;

}
