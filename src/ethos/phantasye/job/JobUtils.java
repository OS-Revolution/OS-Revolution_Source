package ethos.phantasye.job;


import ethos.model.items.ItemDefinition;
import ethos.model.players.skills.Skill;

public class JobUtils {

    public static String skillVerb(Skill skill) {
        switch (skill) {
            case WOODCUTTING:
                return "chop";
            case MINING:
                return "mine";
            case PRAYER:
                return "bury";
            case RUNECRAFTING:
            case CRAFTING:
                return "craft";
            case SMITHING:
                return "forge";
            case HUNTER:
            case FISHING:
                return "catch";
            case FARMING:
                return "harvest";
            case FIREMAKING:
                return "light";
            case FLETCHING:
                return "fletch";
            case HERBLORE:
                return "make";
            case COOKING:
                return "cook";
            default:
                return "complete";
        }
    }

    public static String jobItemName(int id) {
        return ItemDefinition.forId(id).getName();
    }
}
