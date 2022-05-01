package ethos.runehub.skill;

import ethos.model.players.Player;
import ethos.runehub.skill.artisan.cooking.Cooking;
import ethos.runehub.skill.artisan.herblore.Herblore;
import ethos.runehub.skill.artisan.runecraft.Runecraft;
import ethos.runehub.skill.artisan.smithing.Smithing;
import ethos.runehub.skill.gathering.GatheringSkill;
import ethos.runehub.skill.gathering.fishing.Fishing;
import ethos.runehub.skill.gathering.foraging.Foraging;
import ethos.runehub.skill.gathering.mining.Mining;
import ethos.runehub.skill.gathering.woodcutting.Woodcutting;
import ethos.runehub.skill.node.impl.RenewableNode;
import ethos.runehub.skill.node.impl.gatherable.GatheringNode;
import ethos.runehub.skill.node.io.FishingNodeLoader;
import ethos.runehub.skill.node.io.MiningNodeLoader;
import ethos.runehub.skill.node.io.RenewableNodeLoader;
import ethos.runehub.skill.node.io.WoodcuttingNodeLoader;
import org.runehub.api.io.load.LazyLoader;

public class SkillController {

    public GatheringSkill getGatheringSkill(int skillId) {
        switch (skillId) {
            case 10:
                return fishing;
            case 8:
                return woodcutting;
            case 14:
                return mining;
            case 19:
                return foraging;
            default: throw new NullPointerException("No Gathering Skill with ID: " + skillId);
        }
    }

    public Skill getSkill(int skillId) {
        switch (skillId) {
            case 10:
                return fishing;
            case 7:
                return cooking;
            case 8:
                return woodcutting;
            case 13:
                return smithing;
            case 14:
                return mining;
            case 15:
                return herblore;
            case 19:
                return foraging;
            case 20:
                return runecraft;
            default: throw new NullPointerException("No Skill with ID: " + skillId);
        }
    }



    public RenewableNode getRenewableNode(int nodeId) {
        return RenewableNodeLoader.getInstance().read(nodeId);
    }

    public int getLevel(int skillId) {
        return player.getPA().getLevelForXP(player.playerXP[skillId]);
    }

    public Mining getMining() {
        return mining;
    }

    public Woodcutting getWoodcutting() {
        return woodcutting;
    }

    public Fishing getFishing() {
        return fishing;
    }

    public Foraging getForaging() {
        return foraging;
    }

    public Cooking getCooking() {
        return cooking;
    }

    public Herblore getHerblore() {
        return herblore;
    }

    public Runecraft getRunecraft() {
        return runecraft;
    }

    public Smithing getSmithing() {
        return smithing;
    }

    public Player getPlayer() {
        return player;
    }

    public SkillController(Player player) {
        this.player = player;
        this.woodcutting = new Woodcutting(player);
        this.mining = new Mining(player);
        this.fishing = new Fishing(player);
        this.foraging = new Foraging(player);
        this.cooking = new Cooking(player);
        this.herblore = new Herblore(player);
        this.runecraft = new Runecraft(player);
        this.smithing = new Smithing(player);
    }

    private final Player player;
    private final Woodcutting woodcutting;
    private final Mining mining;
    private final Fishing fishing;
    private final Foraging foraging;
    private final Cooking cooking;
    private final Herblore herblore;
    private final Runecraft runecraft;
    private final Smithing smithing;
}
