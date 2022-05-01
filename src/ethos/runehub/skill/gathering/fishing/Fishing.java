package ethos.runehub.skill.gathering.fishing;

import ethos.model.players.Player;
import ethos.runehub.WorldSettingsController;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.gathering.fishing.action.FishingSkillAction;
import ethos.runehub.skill.gathering.GatheringSkill;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.gathering.tool.GatheringToolLoader;
import ethos.runehub.skill.node.impl.gatherable.impl.FishingNode;
import org.rhd.api.math.impl.AdjustableLong;

import java.util.List;
import java.util.stream.Collectors;

public class Fishing extends GatheringSkill {

    @Override
    public void train(SkillAction action) {
        try {
            FishingSkillAction skillAction = (FishingSkillAction) action;
            this.setActiveNodeContext(skillAction.getTargetedNodeContext());
            skillAction.setTool(this.getGetBestAvailableTool());
            super.train(action);
        } catch (NullPointerException e) {
            this.getPlayer().sendMessage(e.getMessage());
        }
    }

    @Override
    public GatheringTool getGetBestAvailableTool() throws NullPointerException {
        final List<GatheringTool> availableTools = GatheringToolLoader.getInstance().readAll().stream().filter(tool -> tool.getSkillId() == this.getId()).filter(tool -> isNodeTool(tool.getItemId())).filter(tool -> hasNodeTool(tool.getItemId())).filter(tool -> this.getPlayer().getSkillController().getLevel(this.getId()) >= tool.getLevelRequired()).collect(Collectors.toList());
        final int bestToolLevel = availableTools.stream().mapToInt(GatheringTool::getLevelRequired).max().orElse(1);

        return availableTools.stream().filter(tool -> tool.getLevelRequired() == bestToolLevel).findFirst().orElseThrow(() -> new NullPointerException("No available tools."));
    }

    private boolean isNodeTool(int id) {
        final int nodeToolId = ((FishingNode) this.getActiveNodeContext().getNode()).getToolId();
        if (id == nodeToolId) {
            return true;
        } else return isHarpoon(id) && isHarpoon(nodeToolId);
    }

    private boolean hasNodeTool(int id) {
        final int nodeToolId = ((FishingNode) this.getActiveNodeContext().getNode()).getToolId();
        if (this.playerHasItem(id)) {
            return true;
        } else return isHarpoon(id) && isHarpoon(nodeToolId) && playerHasItem(id);
    }

    private boolean playerHasItem(int id) {
        return this.getPlayer().getItems().playerHasItem(id) || this.getPlayer().getItems().isWearingItem(id);
    }

    private boolean isHarpoon(int id) {
        return id == 311 | id == 10129 | id == 21028 | id == 21031;
    }

    @Override
    public int getId() {
        return 10;
    }

    @Override
    protected int getPowerModifier() {
        return Math.toIntExact(Math.round(WorldSettingsController.getInstance().getWorldSettings().getSkillPowerTimer().getOrDefault(this.getId(),new AdjustableLong(0L)).greaterThan(0L) ? WorldSettingsController.getInstance().getWorldSettings().getPowerModifer() : 1.0D));
    }

    @Override
    protected int getEfficiencyModifier() {
        return Math.toIntExact(Math.round(WorldSettingsController.getInstance().getWorldSettings().getSkillEfficiencyTimer().getOrDefault(this.getId(),new AdjustableLong(0L)).greaterThan(0L) ? WorldSettingsController.getInstance().getWorldSettings().getEfficiencyModifier() : 1.0D));
    }

    @Override
    protected int getGainsModifier() {
        return Math.toIntExact(Math.round(WorldSettingsController.getInstance().getWorldSettings().getSkillGainsTimer().getOrDefault(this.getId(),new AdjustableLong(0L)).greaterThan(0L) ? WorldSettingsController.getInstance().getWorldSettings().getGainsModifier() : 1.0D));
    }

    public Fishing(Player player) {
        super(player);
    }

}
