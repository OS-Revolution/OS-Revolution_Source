package ethos.runehub.action.click.npc;

import ethos.model.players.Player;
import ethos.runehub.action.click.npc.impl.FirstClickEstateAgentAction;
import ethos.runehub.action.click.npc.impl.FirstClickGuideAction;
import ethos.runehub.action.click.npc.impl.FirstClickPortMasterAction;
import ethos.runehub.entity.mob.passive.GamblingManHostileMob;
import ethos.runehub.entity.mob.passive.GuideNpcUtils;

public class FirstClickNpcListener {

    public static ClickNpcAction onClick(Player player, int npcId, int npcIndex) {
        switch (npcId) {
            case GuideNpcUtils.NPC_ID:
                return new FirstClickGuideAction(player,npcId,npcIndex);
            case 5419:
                return new FirstClickEstateAgentAction(player,npcId,npcIndex);
//            case 6999:
//                return new FirstClickPortMasterAction(player,npcId,npcIndex);
            case 5055:
                return GamblingManHostileMob.getInstance(npcIndex).talkTo(player,npcIndex );
            default: throw new NullPointerException("Nothing interesting happens.");
        }
    }
}
