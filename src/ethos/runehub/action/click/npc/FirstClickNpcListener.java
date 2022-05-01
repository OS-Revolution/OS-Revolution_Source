package ethos.runehub.action.click.npc;

import ethos.model.players.Player;
import ethos.runehub.action.click.npc.impl.FirstClickEstateAgentAction;

public class FirstClickNpcListener {

    public static ClickNpcAction onClick(Player player, int npcId, int npcIndex) {
        switch (npcId) {
            case 5419:
                return new FirstClickEstateAgentAction(player,npcId,npcIndex);
            default: throw new NullPointerException("Nothing interesting happens.");
        }
    }
}
