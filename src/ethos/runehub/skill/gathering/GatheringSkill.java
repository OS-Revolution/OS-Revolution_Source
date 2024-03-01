package ethos.runehub.skill.gathering;

import ethos.model.players.Player;
import ethos.runehub.skill.Skill;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.gathering.tool.GatheringToolLoader;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GatheringSkill extends Skill {

    protected static final int GATHER_ODDS = 100;
    protected static final int DEPLETION_ODDS = 100;
    protected static final int EVENT_ODDS = 256;

    /**
     * Getter method to retrieve the gathering odds.
     * @return The gathering odds.
     */
    public int getGatherOdds() {
        return GATHER_ODDS;
    }

    /**
     * Getter method to retrieve the depletion odds.
     * @return The depletion odds.
     */
    public int getDepletionOdds() {
        return DEPLETION_ODDS;
    }

    /**
     * Getter method to retrieve the event odds.
     * @return The event odds.
     */
    public int getEventOdds() {
        return EVENT_ODDS;
    }

    /**
     * Method to get the best available gathering tool for the player.
     * @return The best available gathering tool.
     */
    public GatheringTool getBestAvailableTool() {
        // Retrieve all available tools for this skill from the tool loader
        final List<GatheringTool> availableTools = GatheringToolLoader.getInstance().readAll().stream()
                // Filter tools that match the skill ID
                .filter(tool -> tool.getSkillId() == this.getId())
                // Filter tools that the player has or is wearing
                .filter(tool -> this.getPlayer().getItems().playerHasItem(tool.getItemId()) || this.getPlayer().getItems().isWearingItem(tool.getItemId()))
                // Filter tools based on the player's skill level
                .filter(tool -> this.getPlayer().getSkillController().getLevel(this.getId()) >= tool.getLevelRequired())
                .collect(Collectors.toList());

        // Find the highest required level among the available tools
        final int bestToolLevel = availableTools.stream().mapToInt(GatheringTool::getLevelRequired).max().orElse(1);

        // Find the first tool with the highest required level
        GatheringTool bestTool = availableTools.stream().filter(tool -> tool.getLevelRequired() == bestToolLevel).findFirst().orElse(null);

        return bestTool;
    }

    /**
     * Constructor for GatheringSkill class.
     * @param player The player object associated with this skill.
     */
    protected GatheringSkill(Player player) {
        super(player);
    }

}
