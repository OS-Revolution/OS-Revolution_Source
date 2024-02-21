package ethos.scaperune.skill;

import ethos.model.players.Player;

public abstract class Skill {

    public void train(SkillAction action) {
        player.getAttributes().getActionScheduler().schedule(action);
    }

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    protected Skill(int id, Player player) {
        this.id = id;
        this.player = player;
    }

    private final int id;
    private final Player player;
}
