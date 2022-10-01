package ethos.runehub.skill.gathering.farming.foraging;

import ethos.model.players.Player;
import ethos.runehub.skill.gathering.GatheringSkill;

public class Foraging extends GatheringSkill {

    @Override
    public int getId() {
        return 19;
    }

    public Foraging(Player player) {
        super(player);
    }
}
