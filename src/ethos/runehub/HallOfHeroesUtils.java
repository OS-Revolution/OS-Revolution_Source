package ethos.runehub;

import ethos.model.players.Player;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;

public class HallOfHeroesUtils {

    public static final int GUILD_LEADER_ID = 3953;
    public static final int STANDARD_CAPE_COST = 99000;

    public static int getTrimmed(Player player) {
        return (int) Arrays.stream(player.playerXP).filter(value -> player.getPA().getLevelForXP(value) >= 99).count() > 1 ? 1 : 0;
    }

    public static int getCapeId(int nodeId, Player player) {
        int capeId = -1;
        switch (nodeId) {
            case 29183:
                capeId = 9747;
                break;
            case 29191:
                capeId = 9753;
                break;
            case 29207:
                capeId = 9763;
                break;
            case 29211:
                capeId = 9759;
                break;
            case 29213:
                capeId = 9756;
                break;
            case 29221:
                capeId = 9750;
                break;
            case 29203:
                capeId = 9768;
                break;
            case 29193:
                capeId = 9810;
                break;
            case 29199:
                capeId = 9783;
                break;
            case 29197:
                capeId = 9798;
                break;
            case 29195:
                capeId = 9804;
                break;
            case 29189:
                capeId = 9780;
                break;
            case 29219:
                capeId = 9795;
                break;
            case 29209:
                capeId = 9792;
                break;
            case 29201:
                capeId = 9774;
                break;
            case 29181:
                capeId = 9771;
                break;
            case 29223:
                capeId = 9777;
                break;
            case 29217:
                capeId = 9786;
                break;
            case 29215:
                capeId = 9765;
                break;
            case 29185:
                capeId = 9789;
                break;
            case 29187:
                capeId = 9801;
                break;
            case 29225:
                capeId = 9807;
                break;
        }
        return capeId + getTrimmed(player);
    }

    public static int getHoodId(int nodeId, Player player) {
        switch (nodeId) {
            default:
                return getCapeId(nodeId, player) + (getTrimmed(player) == 0 ? 2 : 1);
        }
    }

    public static int getSkillId(int nodeId) {
        switch (nodeId) {
            case 29183:
                return 0;
            case 29191:
                return 1;
            case 29207:
                return 6;
            case 29211:
                return 5;
            case 29213:
                return 4;
            case 29221:
                return 2;
            case 29203:
                return 3;
            case 29193:
                return SkillDictionary.Skill.FARMING.getId();
            case 29199:
                return SkillDictionary.Skill.FLETCHING.getId();
            case 29197:
                return SkillDictionary.Skill.FISHING.getId();
            case 29195:
                return SkillDictionary.Skill.FIREMAKING.getId();
            case 29189:
                return SkillDictionary.Skill.CRAFTING.getId();
            case 29219:
                return SkillDictionary.Skill.SMITHING.getId();
            case 29209:
                return SkillDictionary.Skill.MINING.getId();
            case 29201:
                return SkillDictionary.Skill.HERBLORE.getId();
            case 29181:
                return SkillDictionary.Skill.AGILITY.getId();
            case 29223:
                return SkillDictionary.Skill.THIEVING.getId();
            case 29217:
                return SkillDictionary.Skill.SLAYER.getId();
            case 29215:
                return SkillDictionary.Skill.RUNECRAFTING.getId();
            case 29185:
                return SkillDictionary.Skill.CONSTRUCTION.getId();
            case 29187:
                return SkillDictionary.Skill.COOKING.getId();
            case 29225:
                return SkillDictionary.Skill.WOODCUTTING.getId();
            default:
                return -1;
        }
    }
}
