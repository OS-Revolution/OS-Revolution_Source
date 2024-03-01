package ethos.runehub.skill.gathering.farming;

import ethos.model.content.achievement.AchievementType;
import ethos.model.content.achievement.Achievements;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.gathering.GatheringSkill;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.gathering.tool.GatheringToolLoader;
import ethos.runehub.skill.node.context.impl.GatheringNodeContext;
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.util.SkillDictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            case 8552:
            case 8556:
            case 8550://n falador allotment
                return Optional.of(this.getPlayer().getContext().getPlayerSaveData().farmingConfig().get(regionId).stream()
                        .filter(config -> config.getPatch() == 0).findAny().orElseThrow(() -> new IllegalArgumentException("No Such Farm")));
            case 8555:
            case 8553:
            case 8557:
            case 8551: //s falador allotment
                return Optional.of(this.getPlayer().getContext().getPlayerSaveData().farmingConfig().get(regionId).stream()
                        .filter(config -> config.getPatch() == 8).findAny().orElseThrow(() -> new IllegalArgumentException("No Such Farm")));
            case 7849:
            case 7848:
            case 7850:
            case 7847:
                return Optional.of(this.getPlayer().getContext().getPlayerSaveData().farmingConfig().get(regionId).stream()
                        .filter(config -> config.getPatch() == 16).findAny().orElseThrow(() -> new IllegalArgumentException("No Such Farm")));
            case 8152:
            case 8151:
            case 8153:
            case 8150:
                return Optional.of(this.getPlayer().getContext().getPlayerSaveData().farmingConfig().get(regionId).stream()
                        .filter(config -> config.getPatch() == 24).findAny().orElseThrow(() -> new IllegalArgumentException("No Such Farm")));
            case 8175: //hops
            case 8176:
            case 8173:
            case 8174:
            case 7577: //bush
            case 7578:
            case 7580:
            case 7579:
            case 8391: // tree
            case 8390:
            case 8389:
            case 8388:
            case 19147:
            case 7962: //fruit tree
            case 7965:
            case 7963:
            case 7964:
            case 26579:

                return Optional.of(this.getPlayer().getContext().getPlayerSaveData().farmingConfig().get(regionId).stream()
                        .filter(config -> config.getPatch() == 32).findAny().orElseThrow(() -> new IllegalArgumentException("No Such Farm")));
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

    public int getHarvestedAmount(GatheringNodeContext<?> context, FarmingConfig config) {
        final int regionId = RunehubUtils.getRegionId(context.getX(), context.getY());
        return this.getPlayer().getContext().getPlayerSaveData().getHarvestMap().computeIfAbsent(regionId, key -> {
                    final HashMap<Integer, AdjustableInteger> patchMap = new HashMap<>();
                    patchMap.put(config.getType(), new AdjustableInteger(0));
                    return patchMap;
                })
                .computeIfAbsent(config.getType(), key -> new AdjustableInteger(0)).value();
    }

    public void updateHarvestCounts(GatheringNodeContext<?> context, FarmingConfig config) {
        final int regionId = RunehubUtils.getRegionId(context.getX(), context.getY());
        this.getPlayer().getContext().getPlayerSaveData().getHarvestMap().computeIfAbsent(regionId, key -> {
                    final HashMap<Integer, AdjustableInteger> patchMap = new HashMap<>();
                    patchMap.put(config.getType(), new AdjustableInteger(0));
                    return patchMap;
                }).computeIfAbsent(config.getType(), key -> new AdjustableInteger(0)).increment();
        		//Achievements.increase(player, AchievementType.FARM, 1); MICHAEL ACHIEVEMENT FARMING
    }

    public void resetHarvestCounts(GatheringNodeContext<?> context, FarmingConfig config) {
        final int regionId = RunehubUtils.getRegionId(context.getX(), context.getY());
        this.getPlayer().getContext().getPlayerSaveData().getHarvestMap().computeIfAbsent(regionId, key -> {
                    final HashMap<Integer, AdjustableInteger> patchMap = new HashMap<>();
                    patchMap.put(config.getType(), new AdjustableInteger(0));
                    return patchMap;
                })
                .computeIfAbsent(config.getType(), key -> new AdjustableInteger(0)).setValue(0);
    }

    public void updateAllConfigs() {
        final AdjustableInteger varbit = new AdjustableInteger(0);
        this.getPlayer().getContext().getPlayerSaveData().farmingConfig().keySet().forEach(key -> this.getPlayer().getContext().getPlayerSaveData().farmingConfig().get(key).forEach(config -> {
                    varbit.add(config.varbit());
                }
        ));
        this.getPlayer().getPA().sendConfig(529, varbit.value());
    }

    public void updateFarm(int regionId) {
        if (this.getPlayer().getContext().getPlayerSaveData().farmingConfig().containsKey(regionId)) {
            final int varbit = this.getPlayer().getContext().getPlayerSaveData().farmingConfig().get(regionId).stream().mapToInt(FarmingConfig::varbit).sum();
            System.out.println("Varbit: " + varbit);
            this.getPlayer().getPA().sendConfig(529, varbit);
        }
    }

    @Override
    public void train(SkillAction skillAction) {
        super.train(skillAction);
    }

    @Override
    public GatheringTool getBestAvailableTool() {
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
