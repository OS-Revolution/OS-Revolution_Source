package ethos.runehub.skill;

import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.runehub.event.dnd.SkillOfTheHourFixedScheduleEvent;
import ethos.runehub.skill.artisan.cooking.Cooking;
import ethos.runehub.skill.artisan.crafting.Crafting;
import ethos.runehub.skill.artisan.fletching.Fletching;
import ethos.runehub.skill.artisan.herblore.Herblore;
import ethos.runehub.skill.artisan.runecraft.Runecraft;
import ethos.runehub.skill.artisan.smithing.Smithing;
import ethos.runehub.skill.combat.magic.Magic;
import ethos.runehub.skill.combat.prayer.Prayer;
import ethos.runehub.skill.gathering.GatheringSkill;
import ethos.runehub.skill.gathering.farming.Farming;
import ethos.runehub.skill.gathering.farming.foraging.Foraging;
import ethos.runehub.skill.gathering.fishing.Fishing;
import ethos.runehub.skill.gathering.mining.Mining;
import ethos.runehub.skill.gathering.woodcutting.Woodcutting;
import ethos.runehub.skill.node.impl.RenewableNode;
import ethos.runehub.skill.node.io.RenewableNodeLoader;
import ethos.runehub.skill.support.SupportSkill;
import ethos.runehub.skill.support.firemaking.Firemaking;
import ethos.runehub.skill.support.sailing.Sailing;
import ethos.runehub.skill.support.slayer.Slayer;
import ethos.runehub.skill.support.thieving.Thieving;
import ethos.runehub.world.WorldSettingsController;
import ethos.util.Misc;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;
import java.util.logging.Logger;

public class SkillController {

    public int getTotalLevel() {
        return Arrays.stream(this.getPlayer().playerXP).map(player::getLevelForXP).sum();
    }

    public void addXP(int skillId, int baseAmount) {
        final int currentXP = player.playerXP[skillId];
        final int startingLevel = player.getLevelForXP(player.playerXP[skillId]);
        final int maxXpDifference = 200000000 - currentXP;
        if (baseAmount > 0) {
            final int amount = this.getXPWithModifiers(skillId, baseAmount);
            final int quotient = maxXpDifference / amount;
            final int remainder = maxXpDifference % amount;
            player.getAttributes().getPlayPassController().addPlayPassXPFromSkillXP(baseAmount);
            if (currentXP < 200000000) {
                if (quotient >= 1) {
                    Logger.getGlobal().fine("Adding Skill XP: " + skillId + " | " + amount);
                    player.playerXP[skillId] += amount;
                    player.getPA().sendExperienceDrop(true, amount, skillId);
                } else {
                    Logger.getGlobal().fine("Adding Skill XP: " + skillId + " | " + remainder);
                    player.playerXP[skillId] += remainder;
                    player.getPA().sendExperienceDrop(true, remainder, skillId);
                    PlayerHandler.executeGlobalMessage(
                            "^News $" + Misc.capitalize(player.getAttributes().getName()) + " has reached $200M XP in $" + Misc.capitalize(SkillDictionary.Skill.values()[skillId].name().toLowerCase()) + "!"
                    );

                }
            }
        }

        if (startingLevel < player.getLevelForXP(player.playerXP[skillId])) {
            this.onLevelup(skillId);
        }

        player.getPA().setSkillLevel(skillId, player.playerLevel[skillId], player.playerXP[skillId]);
        player.getPA().refreshSkill(skillId);
    }

    public void addImmutableXP(int skillId, int baseAmount) {
        final int currentXP = player.playerXP[skillId];
        final int startingLevel = player.getLevelForXP(player.playerXP[skillId]);
        final int maxXpDifference = 200000000 - currentXP;
        if (baseAmount > 0) {
            final int amount = baseAmount;
            final int quotient = maxXpDifference / amount;
            final int remainder = maxXpDifference % amount;
            player.getAttributes().getPlayPassController().addPlayPassXPFromSkillXP(baseAmount);
            if (currentXP < 200000000) {
                if (quotient >= 1) {
                    Logger.getGlobal().fine("Adding Skill XP: " + skillId + " | " + amount);
                    player.playerXP[skillId] += amount;
                    player.getPA().sendExperienceDrop(true, amount, skillId);
                } else {
                    Logger.getGlobal().fine("Adding Skill XP: " + skillId + " | " + remainder);
                    player.playerXP[skillId] += remainder;
                    player.getPA().sendExperienceDrop(true, remainder, skillId);
                    PlayerHandler.executeGlobalMessage(
                            "^News $" + Misc.capitalize(player.getAttributes().getName()) + " has reached $200M XP in $" + Misc.capitalize(SkillDictionary.Skill.values()[skillId].name().toLowerCase()) + "!"
                    );

                }
            }
        }

        if (startingLevel < player.getLevelForXP(player.playerXP[skillId])) {
            this.onLevelup(skillId);
        }

        player.getPA().setSkillLevel(skillId, player.playerLevel[skillId], player.playerXP[skillId]);
        player.getPA().refreshSkill(skillId);
    }

    private int getXPWithModifiers(int skill, int baseAmount) {
        int amount = baseAmount;
        if (player.getContext().getPlayerSaveData().getBonusXp().containsKey(skill)) {
            if (player.getContext().getPlayerSaveData().getBonusXp().get(skill).value() > baseAmount) {
                player.getContext().getPlayerSaveData().getBonusXp().get(skill).subtract(baseAmount);
                player.getPA().sendBonusXp(skill, player.getContext().getPlayerSaveData().getBonusXp().get(skill).value());
                amount *= 2;
            } else if (player.getContext().getPlayerSaveData().getBonusXp().get(skill).value() > 0) {
                amount += player.getContext().getPlayerSaveData().getBonusXp().get(skill).value();
                player.getContext().getPlayerSaveData().getBonusXp().get(skill).setValue(0);
                player.sendMessage("You've used up all your bonus XP in $" + SkillDictionary.getSkillNameFromId(skill));
                player.getPA().sendBonusXp(skill, player.getContext().getPlayerSaveData().getBonusXp().get(skill).value());
            }
        }
        if (WorldSettingsController.getInstance().getWorldSettings().getWeekendEventId() == 1) {
            amount *= 2.0;
        }
        if (WorldSettingsController.getInstance().getWorldSettings().getBonusXpTimer().value() > 0) {
            amount *= 2.0;
        }

        if (WorldSettingsController.getInstance().getSkillOfTheHourEffect(skill) == SkillOfTheHourFixedScheduleEvent.XP) {
            amount *= 1.5;
        }
        if (skill == SkillDictionary.Skill.CRAFTING.getId() && player.getAttributes().getSkillStationId() == 6799) {
            amount += (baseAmount * 0.1) < 1 ? 1 : (baseAmount * 0.1);
        } else if(skill == SkillDictionary.Skill.COOKING.getId() && player.getAttributes().getSkillStationId() == 13542) {
            amount += (baseAmount * 0.21) < 1 ? 1 : (baseAmount * 0.21);
        } else if(skill == SkillDictionary.Skill.HERBLORE.getId() && player.getAttributes().getSkillStationId() == 878) {
            amount += (baseAmount * 0.1) < 1 ? 1 : (baseAmount * 0.1);
        } else if(skill == SkillDictionary.Skill.FIREMAKING.getId() && player.getAttributes().getSkillStationId() == 11017) {
            amount += (baseAmount * 0.1) < 1 ? 1 : (baseAmount * 0.1);
        }

        if (this.getSkill(skill) != null)
            amount *= this.getSkill(skill).getGainsBonus() + this.getSkill(skill).getEquipmentBonuses();
        return amount;
    }

    private void onLevelup(int skillId) {
        final SkillDictionary.Skill skill = SkillDictionary.Skill.values()[skillId];

        if (skill == SkillDictionary.Skill.ATTACK || skill == SkillDictionary.Skill.DEFENCE || skill == SkillDictionary.Skill.STRENGTH ||
                skill == SkillDictionary.Skill.HITPOINTS || skill == SkillDictionary.Skill.PRAYER || skill == SkillDictionary.Skill.RANGED ||
                skill == SkillDictionary.Skill.MAGIC && player.combatLevel < 126) {
            player.calculateCombatLevel();
            player.getPA().sendFrame126("Combat Level: " + player.combatLevel, 3983);
        }

        if (player.getLevelForXP(player.playerXP[skillId]) == 99) {
            PlayerHandler.executeGlobalMessage(
                    "^News $" + Misc.capitalize(player.getAttributes().getName()) + " has reached level $99 in $" + Misc.capitalize(SkillDictionary.Skill.values()[skillId].name().toLowerCase()) + "!"
            );
        }

        player.gfx100(199);
        player.getPA().requestUpdates();
        player.getPA().levelUp(skillId);

    }

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
            default:
                throw new NullPointerException("No Gathering Skill with ID: " + skillId);
        }
    }

    public SupportSkill getSupportSkill(int skillId) {
        switch (skillId) {
            case 17:
                return thieving;
            default:
                throw new NullPointerException("No Gathering Skill with ID: " + skillId);
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
            case 9:
                return fletching;
            case 11:
                return firemaking;
            case 12:
                return crafting;
            case 13:
                return smithing;
            case 14:
                return mining;
            case 15:
                return herblore;
            case 17:
                return thieving;
            case 19:
                return foraging;
            case 20:
                return runecraft;
            case 23:
                return sailing;
            default:
                return null;
//                throw new NullPointerException("No Skill with ID: " + skillId);
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

    public Thieving getThieving() {
        return thieving;
    }

    public Player getPlayer() {
        return player;
    }

    public Sailing getSailing() {
        return sailing;
    }

    public Farming getFarming() {
        return farming;
    }

    public Magic getMagic() {
        return magic;
    }

    public Crafting getCrafting() {
        return crafting;
    }

    public Slayer getSlayer() {
        return slayer;
    }

    public Prayer getPrayer() {
        return prayer;
    }

    public Firemaking getFiremaking() {
        return firemaking;
    }

    public Fletching getFletching() {
        return fletching;
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
        this.thieving = new Thieving(player);
        this.sailing = new Sailing(player);
        this.farming = new Farming(player);
        this.magic = new Magic(player);
        this.crafting = new Crafting(player);
        this.slayer = new Slayer(player);
        this.prayer = new Prayer(player);
        this.firemaking = new Firemaking(player);
        this.fletching = new Fletching(player);
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
    private final Thieving thieving;
    private final Sailing sailing;
    private final Farming farming;
    private final Magic magic;
    private final Crafting crafting;
    private final Slayer slayer;
    private final Prayer prayer;
    private final Firemaking firemaking;
    private final Fletching fletching;

}
