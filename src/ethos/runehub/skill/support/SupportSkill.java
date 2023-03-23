package ethos.runehub.skill.support;

import ethos.model.players.Player;
import ethos.runehub.skill.Skill;

import java.util.Arrays;

public abstract class SupportSkill extends Skill {

    private static final int GATHER_ODDS = 100;
    private static final int DEPLETION_ODDS = 100;
    private static final int EVENT_ODDS = Short.MAX_VALUE;



    public int getGatherOdds() {
        return GATHER_ODDS;
    }

    public int getDepletionOdds() {
        return DEPLETION_ODDS;
    }

    public int getEventOdds() {
        return EVENT_ODDS;
    }

    protected SupportSkill(Player player) {
        super(player);
    }
}
