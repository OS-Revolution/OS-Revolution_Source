package ethos.runehub.skill;

import ethos.Server;
import ethos.model.players.Player;
import ethos.util.Misc;
import org.runehub.api.util.SkillDictionary;

import java.util.Random;

public abstract class Skill {

    public static final Random SKILL_RANDOM = new Random();

    public abstract int getId();
    protected int getPowerModifier() {
        return 1;
    }
    protected int getEfficiencyModifier() {
        return 1;
    }
    protected int getGainsModifier() {
        return 1;
    }

    public double getXpGainsRate() {
        return Math.toIntExact(
                Math.round((1.0)
                        * this.getGainsModifier()
                )
        );
    }

    public double getEfficiency() {
        return Math.toIntExact(Math.round(this.getEfficiencyModifier()));
    }

    public void train(SkillAction action) {
        try {
            action.getActor().getSkilling().stop();
            action.getActor().getSkilling().setSkill(action.getSkillId());
            Server.getEventHandler().submit(action);
        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage(e.getMessage());
        }
    }

    public double getActionSuccessChance(int min, int max) {
        double brOne = Math.floor(min * (99 - player.getSkillController().getLevel(this.getId())) / 98.0);
        double brTwo = Math.floor(max * (player.getSkillController().getLevel(this.getId()) - 1) / 98.0);
        double top = 1 + brOne + brTwo;
        double value = top / 256;
        return value;
    }

    @Override
    public String toString() {
        if (this.getId() == SkillDictionary.Skill.FARMING.getId())
            return "Foraging";
        else if(this.getId() == 23)
            return "Sailing";
        return Misc.capitalize(SkillDictionary.getSkillNameFromId(this.getId()).toLowerCase());
    }

    public Player getPlayer() {
        return player;
    }

    protected Skill(Player player) {
        this.player = player;
    }

    private final Player player;
}
