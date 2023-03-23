package ethos.runehub.skill.support.thieving;

import ethos.model.players.Player;
import ethos.runehub.skill.support.SupportSkill;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;

public class Thieving extends SupportSkill {

    public static final int[] MARKET_GUARDS = {2120};
    public static final int CAUGHT_DELAY_SECONDS = 2;

    public static int isMarketGuard(int npcId) {
        return Arrays.stream(MARKET_GUARDS).filter(value -> value == npcId).findAny().orElse(-1);
    }

    @Override
    public double getPowerBonus() {
        double modifier = Arrays.stream(this.getPlayer().playerEquipment).mapToDouble(equippedItem ->{
            switch (equippedItem) {
                case 10075:
                    return 0.05;
                case 9777:
                case 9778:
                case 13280:
                case 13329:
                case 13331:
                case 13333:
                case 13335:
                case 13337:
                case 13342:
                case 20760:
                case 21185:
                case 21285:
                    return 0.1;
                case 13123:
                    return 0.08;
                case 773:
                    return 1.0;
            }
            return 0D;
        }).sum();
        return modifier + super.getPowerBonus();
    }

    @Override
    public double getEfficiencyBonus() {
        double modifier = Arrays.stream(this.getPlayer().playerEquipment).mapToDouble(equippedItem ->{
            switch (equippedItem) {

            }
            return 0D;
        }).sum();
        return modifier + super.getPowerBonus();
    }

    @Override
    public int getId() {
        return SkillDictionary.Skill.THIEVING.getId();
    }

    public Thieving(Player player) {
        super(player);
    }
}
