package ethos.runehub.skill.support.thieving;

import ethos.model.players.Player;
import ethos.runehub.skill.support.SupportSkill;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;

public class Thieving extends SupportSkill {

    public static final int[] MARKET_GUARDS = {2120};
    public static final int CAUGHT_DELAY_SECONDS = 2;

    public static int isMarketGuard(int npcId) {
        return Arrays.stream(MARKET_GUARDS).filter(value -> value == npcId).findAny().orElse(-1);
    }

    @Override
    public int getId() {
        return SkillDictionary.Skill.THIEVING.getId();
    }

    public Thieving(Player player) {
        super(player);
    }
}
