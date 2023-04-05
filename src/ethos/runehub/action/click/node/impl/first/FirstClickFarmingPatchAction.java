package ethos.runehub.action.click.node.impl.first;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.skill.gathering.farming.FarmingConfig;
import ethos.runehub.skill.gathering.farming.action.*;
import ethos.runehub.skill.gathering.farming.crop.CropDAO;
import ethos.runehub.skill.gathering.farming.patch.PatchType;

import java.util.Optional;

public class FirstClickFarmingPatchAction extends ClickNodeAction {

    @Override
    protected void onActionStart() {

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.getConfig().ifPresent(config -> {
            System.out.println("Crop: " + config.getCrop() + " Cycle: " + config.getStage());
            if (config.getCrop() == 0 && config.getStage() < 3) {
                System.out.println("Raking patch");
                this.getActor().getSkillController().getFarming().train(new RakePatchAction(this.getActor(), config, this.getNodeId(), this.getNodeX(), this.getNodeY()));
            } else if (config.diseased() && config.watered()) {
                System.out.println("Clearing patch");
                this.getActor().getSkillController().getFarming().train(new ClearPatchAction(this.getActor(), config, this.getNodeId(), this.getNodeX(), this.getNodeY()));
            } else if (config.getCrop() != 0 && config.getStage() + 1 >= CropDAO.getInstance().read(config.getCrop()).getGrowthCycles() && config.getType() == PatchType.ALLOTMENT.ordinal()) {
                this.getActor().getSkillController().getFarming().train(new HarvestAllotmentPatchAction(this.getActor(), config, this.getNodeId(), this.getNodeX(), this.getNodeY()));
            } else if (config.getCrop() != 0 && config.getStage() + 1 >= CropDAO.getInstance().read(config.getCrop()).getGrowthCycles() && config.getType() == PatchType.HERB.ordinal()) {
                this.getActor().getSkillController().getFarming().train(new HarvestHerbPatchAction(this.getActor(), config, this.getNodeId(), this.getNodeX(), this.getNodeY()));
            } else if (config.getCrop() != 0 && config.diseased()) {
                System.out.println("Curing patch");
                this.getActor().getSkillController().getFarming().train(new ApplyPlantCureAction(this.getActor(), new ItemInteractionContext(this.getNodeX(), this.getNodeY(), 0, 6036, this.getNodeId(), 1, 1)));
            } else {
                System.out.println("Other");
            }
        });
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    private Optional<FarmingConfig> getConfig() {
//        switch (this.getNodeId()) {
//            case 8554:
//            case 8552:
//            case 8556:
//                return Optional.of(this.getActor().getContext().getPlayerSaveData().farmingConfig().get(regionId).stream()
//                        .filter(config -> config.getPatch() == 0).findAny().orElseThrow(() -> new IllegalArgumentException("No Such Farm")));
//            case 8555:
//            case 8553:
//            case 8557:
//                return Optional.of(this.getActor().getContext().getPlayerSaveData().farmingConfig().get(regionId).stream()
//                        .filter(config -> config.getPatch() == 8).findAny().orElseThrow(() -> new IllegalArgumentException("No Such Farm")));
//            case 7849:
//            case 7848:
//            case 7850:
//                return Optional.of(this.getActor().getContext().getPlayerSaveData().farmingConfig().get(regionId).stream()
//                        .filter(config -> config.getPatch() == 16).findAny().orElseThrow(() -> new IllegalArgumentException("No Such Farm")));
//            case 8152:
//            case 8151:
//            case 8153:
//                return Optional.of(this.getActor().getContext().getPlayerSaveData().farmingConfig().get(regionId).stream()
//                        .filter(config -> config.getPatch() == 24).findAny().orElseThrow(() -> new IllegalArgumentException("No Such Farm")));
//        }
        return this.getActor().getSkillController().getFarming().getConfig(this.getNodeId(),regionId);
    }

    public FirstClickFarmingPatchAction(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 1, nodeId, nodeX, nodeY);
        this.regionId = RunehubUtils.getRegionId(nodeX, nodeY);
    }

    private final int regionId;
}
