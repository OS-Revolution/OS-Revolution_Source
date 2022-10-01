package ethos.runehub.skill.gathering.farming;

import ethos.model.players.Player;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.gathering.GatheringSkill;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.gathering.tool.GatheringToolLoader;
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.util.SkillDictionary;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Interaction process
 * 1. Use seeds on patch (provides seedId, x, y, z)
 * 2. return patch using x,y,z
 * 3. perform checks to validate seeds and patch and level
 * 4. create planted objectId in patch
 * 5. plant does growth check every farm tick to determine if it grows, gets sick, or dies
 * 6. gets objectId and chance data from crop database using seedId
 * 7. plant grows completely player interacts with like typical gathering node
 * <p>
 * IDs
 * 8150-8153 fully grown weeds in herb patch (8152 = ardougne test patch)
 * 8573-8576 weed cycles for falador allotment patch
 * 8550 - 8557 fully grown allotment weeds
 * 8558 - 8562 potato growth stages unaltered
 * 8563 - 8566 potato growth stages with water
 * 8567 -8569 potato diseased growth stages
 * 8570 - 8572 potato death growth stages
 * 8573 empty allotment
 * 8574 stage 1 weeds allotment
 * 8575 stage 2 weeds allotment
 * 8576 stage 3 weeds allotment
 * 8577-8579 weed growth stages allotment
 * 7557-7560 belladonna weed growth stages
 * 7576-7580 fully grown bush patch weeds
 * 7575 stage 2 bush patch weeds
 * 7574 stage 1 bush patch weeds
 * 7573 empty bush patch
 * 8139-8143 herb growth stages
 * 8136-8138 herb patch weeds stages //dark
 * 8133-8135 herb patch weed stages //default
 * 8132 empty herb patch
 */
public class Farming extends GatheringSkill {

    public static final int COMPOST = 6032;
    public static final int SUPERCOMPOST = 6034;
    public static final int ULTRACOMPOST = 7622;
    public static final int BOTTOMLESS_COMPOST = 7624;

    public Optional<FarmingConfig> getConfig(int nodeId, int regionId) {
        switch (nodeId) {
            case 8554:
                return Optional.of(this.getPlayer().getContext().getPlayerSaveData().getFarmingConfig().get(regionId).stream()
                        .filter(config -> config.getPatch() == 0).findAny().orElseThrow(() -> new IllegalArgumentException("No Such Farm")));
            case 8555:
                return Optional.of(this.getPlayer().getContext().getPlayerSaveData().getFarmingConfig().get(regionId).stream()
                        .filter(config -> config.getPatch() == 8).findAny().orElseThrow(() -> new IllegalArgumentException("No Such Farm")));
            case 7849:
                return Optional.of(this.getPlayer().getContext().getPlayerSaveData().getFarmingConfig().get(regionId).stream()
                        .filter(config -> config.getPatch() == 16).findAny().orElseThrow(() -> new IllegalArgumentException("No Such Farm")));
            case 8152:
                return Optional.of(this.getPlayer().getContext().getPlayerSaveData().getFarmingConfig().get(regionId).stream()
                        .filter(config -> config.getPatch() == 24).findAny().orElseThrow(() -> new IllegalArgumentException("No Such Farm")));
        }
        return Optional.empty();
    }

    private double getCompostYieldBonus(int compost) {
        switch (compost) {
            case COMPOST:
                return 1.2;
            case SUPERCOMPOST:
                return 1.4;
            case ULTRACOMPOST:
                return 1.6;
        }
        return 1.0;
    }

    public double getHarvestChanceBonus() {
        double bonus = 1;
        return bonus;
    }

    public double getHarvestMinMaxBonus(FarmingConfig config) {
        return this.getCompostYieldBonus(config.getCompost());
    }

    public void updateAllConfigs() {
        final AdjustableInteger varbit = new AdjustableInteger(0);
        this.getPlayer().getContext().getPlayerSaveData().getFarmingConfig().keySet().forEach(key -> this.getPlayer().getContext().getPlayerSaveData().getFarmingConfig().get(key).forEach(config -> {
                    varbit.add(config.varbit());
                }
        ));
        this.getPlayer().getPA().sendConfig(529, varbit.value());
    }

    public void updateFarm(int regionId) {
        if (this.getPlayer().getContext().getPlayerSaveData().getFarmingConfig().containsKey(regionId)) {
            final int varbit = this.getPlayer().getContext().getPlayerSaveData().getFarmingConfig().get(regionId).stream().mapToInt(FarmingConfig::varbit).sum();
            this.getPlayer().getPA().sendConfig(529,varbit);
        }
    }

    @Override
    public void train(SkillAction skillAction) {
        super.train(skillAction);
    }

    @Override
    public GatheringTool getGetBestAvailableTool() {
        final List<GatheringTool> availableTools = GatheringToolLoader.getInstance().readAll().stream()
                .filter(tool -> tool.getSkillId() == this.getId())
                .filter(tool -> this.getPlayer().getItems().playerHasItem(tool.getItemId()) || this.getPlayer().getItems().isWearingItem(tool.getItemId()))
                .filter(tool -> this.getPlayer().getSkillController().getLevel(this.getId()) >= tool.getLevelRequired())
                .collect(Collectors.toList());
        final int bestToolLevel = availableTools.stream().mapToInt(GatheringTool::getLevelRequired).max().orElse(1);

        return availableTools.stream().filter(tool -> tool.getLevelRequired() == bestToolLevel).findFirst().orElseThrow(() -> new NullPointerException("No available tools."));
    }

    @Override
    public int getId() {
        return SkillDictionary.Skill.FARMING.getId();
    }

    @Override
    protected int getPowerModifier() {
        return 0;
    }

    @Override
    protected int getEfficiencyModifier() {
        return 0;
    }

    @Override
    protected int getGainsModifier() {
        return 0;
    }

    public Farming(Player player) {
        super(player);
    }
}
