package ethos.runehub.entity.combat;

import ethos.model.npcs.NPC;
import ethos.model.players.Player;

public interface CombatMechanics {

    void onHit();

    void onAttack(Player player, NPC npc);

    void onDeath();
}
