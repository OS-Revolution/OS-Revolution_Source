package ethos.runehub.skill.gathering;

import ethos.model.players.Player;
import ethos.runehub.skill.Skill;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.gathering.tool.GatheringToolLoader;
import ethos.runehub.skill.node.context.ActiveSkillNodeContext;
import ethos.runehub.skill.node.impl.gatherable.GatheringNode;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GatheringSkill extends Skill {

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

    public GatheringTool getGetBestAvailableTool() {
            final List<GatheringTool> availableTools = GatheringToolLoader.getInstance().readAll().stream()
                    .filter(tool -> tool.getSkillId() == this.getId())
                    .filter(tool -> this.getPlayer().getItems().playerHasItem(tool.getItemId()) || this.getPlayer().getItems().isWearingItem(tool.getItemId()))
                    .filter(tool -> this.getPlayer().getSkillController().getLevel(this.getId()) >= tool.getLevelRequired())
                    .collect(Collectors.toList());
            final int bestToolLevel = availableTools.stream().mapToInt(GatheringTool::getLevelRequired).max().orElse(1);

            return availableTools.stream().filter(tool -> tool.getLevelRequired() == bestToolLevel).findFirst().orElse(null);
    }

    @Override
    public double getXpGainsRate() {
        return xpGainsRate * this.getGetBestAvailableTool().getXpGainMultiplier() * this.getGainsModifier();
    }

    @Override
    public double getEfficiency() {
        return Math.toIntExact(Math.round((efficiency + this.getGetBestAvailableTool().getBaseEfficiency())
                        * this.getEfficiencyModifier()
                )
        );
    }

    public double getPower() {
        return this.getGetBestAvailableTool().getBasePower() * this.getPowerModifier();

    }

    public ActiveSkillNodeContext<? extends GatheringNode> getActiveNodeContext() {
        return activeNodeContext;
    }

    public void setActiveNodeContext(ActiveSkillNodeContext<? extends GatheringNode> activeNodeContext) {
        this.activeNodeContext = activeNodeContext;
    }

    protected GatheringSkill(Player player) {
        super(player);
        this.xpGainsRate = 1.0f;
    }

    private ActiveSkillNodeContext<? extends GatheringNode> activeNodeContext;
    private int power; //adds to roll for chance to harvest node
    private int efficiency; //adds to min roll required to deplete node
    private float xpGainsRate; //multiplier for xp
}
