package ethos.runehub.entity.combat.impl;

import ethos.model.npcs.NPC;
import ethos.model.npcs.NPCHandler;
import ethos.model.players.Player;
import ethos.model.players.combat.CombatType;
import ethos.model.players.packets.commands.owner.Npc;
import ethos.runehub.entity.combat.CombatContext;
import ethos.runehub.entity.combat.CombatMechanics;
import ethos.util.Misc;

public class RatKingCombatMechanics implements CombatMechanics {

    private static RatKingCombatMechanics instance = null;

    public static RatKingCombatMechanics getInstance() {
        if (instance == null)
            instance = new RatKingCombatMechanics();
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
            npc.attackType = CombatType.RANGE;
            npc.projectileId = 1078;
            npc.hitDelayTimer = 3;
            npc.maxHit = 30;
        } else {
            npc.attackType = CombatType.MELEE;
            npc.projectileId = -1;
            npc.hitDelayTimer = 3;
            npc.maxHit = 20;
        }
    }

    @Override
    public void onDeath() {

    }

    private RatKingCombatMechanics() {

    }

}
