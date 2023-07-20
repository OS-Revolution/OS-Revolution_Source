package ethos.runehub.skill.support.sailing;

import ethos.model.players.Player;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.skill.support.sailing.ship.Ship;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
import org.runehub.api.model.math.impl.IntegerRange;

import java.text.DecimalFormat;
import java.util.*;

public class SailingUtils {

    private static final Map<Integer, GameItem[]> MATERIALS_FOR_REGION_TIER;
    public static final int SHIPWRIGHT_BASE_FEE = 20000;

    static {
        MATERIALS_FOR_REGION_TIER = new HashMap<>();
        MATERIALS_FOR_REGION_TIER.put(0, new GameItem[]{new GameItem(8162, 100)}); // Bamboo
        MATERIALS_FOR_REGION_TIER.put(1, new GameItem[]{new GameItem(7108, 100)}); // Gunpowder
        MATERIALS_FOR_REGION_TIER.put(2, new GameItem[]{new GameItem(7941, 100)}); // Black slate
        MATERIALS_FOR_REGION_TIER.put(3, new GameItem[]{new GameItem(13355, 100)}); // Cherrywood
        MATERIALS_FOR_REGION_TIER.put(4, new GameItem[]{new GameItem(3565, 100)}); // Jade
        MATERIALS_FOR_REGION_TIER.put(5, new GameItem[]{new GameItem(10873, 100)}); // Stainless Steel
        MATERIALS_FOR_REGION_TIER.put(6, new GameItem[]{new GameItem(20798, 100)}); // Terracotta
        MATERIALS_FOR_REGION_TIER.put(7, new GameItem[]{new GameItem(2795, 100)}); // Azure
        MATERIALS_FOR_REGION_TIER.put(8, new GameItem[]{new GameItem(995, 100000)}); // TBD uncharted waters
    }

    public static GameItem[] getMaterialsForRegionTier(int regionTier) {
        return MATERIALS_FOR_REGION_TIER.getOrDefault(regionTier, new GameItem[]{new GameItem(995, 50000)});
    }

    public static String getSuccessRateString(Voyage voyage, Ship ship) {
        final float percentOfTotalDistance = ship.getVoyageSuccessRate(voyage);
        if (percentOfTotalDistance >= 0.8) {
            return "@gre@" + ship.getSuccessRatePrint(percentOfTotalDistance);
        } else if (percentOfTotalDistance < 0.8 && percentOfTotalDistance >= 0.5) {
            return "@yel@" + ship.getSuccessRatePrint(percentOfTotalDistance);
        }
        return "@red@" + ship.getSuccessRatePrint(percentOfTotalDistance);
    }

    public static String getStatString(int a, int b, float percent) {
        if (percent >= 0.8) {
            return "@gre@" + a + "/" + b;
        } else if (percent < 0.8 && percent >= 0.5) {
            return "@yel@" + a + "/" + b;
        }
        return "@red@" + a + "/" + b;
    }

    public static String getSeafaringStatString(Voyage voyage, Ship ship) {
        return getStatString(
                ship.getSeafaring(),
                voyage.getSeafaring(),
                ship.getSeafaringSuccessChance(voyage.getSeafaring())
        );
    }

    public static String getMoraleStatString(Voyage voyage, Ship ship) {
        return getStatString(
                ship.getMorale(),
                voyage.getMorale(),
                ship.getMoraleSuccessChance(voyage.getMorale())
        );
    }

    public static String getCombatStatString(Voyage voyage, Ship ship) {
        return getStatString(
                ship.getCombat(),
                voyage.getCombat(),
                ship.getCombatSuccessChance(voyage.getCombat())
        );
    }

    public static int getLootTableContainerIdForRegion(int region) {
        switch (region) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return 50000 + region;
        }
        return -1;
    }

    public static int getLootTableContainerIdForIsland(int island) {
        switch (island) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return 50000 + island;
        }
        return -1;
    }

    public static String getVoyageSuccessRatePrint(float successRate) {
        final DecimalFormat decimalFormat = new DecimalFormat("###.##");
        return decimalFormat.format(successRate * 100) + '%';
    }

    public static int getStatRangeBasedOnRegion(int region) {
        switch (region) {
            case 0:
                return new IntegerRange(5, 15).getRandomValue();
            case 1:
                return new IntegerRange(15, 50).getRandomValue();
            case 2:
                return new IntegerRange(50, 100).getRandomValue();
            case 3:
                return new IntegerRange(100, 200).getRandomValue();
            case 4:
                return new IntegerRange(200, 300).getRandomValue();
            case 5:
                return new IntegerRange(400, 500).getRandomValue();
            case 6:
                return new IntegerRange(600, 700).getRandomValue();
            case 7:
                return new IntegerRange(800, 900).getRandomValue();
            case 8:
                return new IntegerRange(1000, 1500).getRandomValue();
        }
        return new IntegerRange(1000, 1500).getRandomValue();
    }

    public static List<Integer> findInactiveVoyages(long[] activeVoyages, long[] remainingVoyages) {
        List<Integer> replace = new ArrayList<>();
        for (int i = 0; i < remainingVoyages.length; i++) {
            long rv = remainingVoyages[i];
            boolean found = false;
            for (long av : activeVoyages) {
                if (rv == av && av != 0) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                replace.add(i);
            }
        }
        return replace;
    }

    public static boolean hasCargo(Player player, int itemId) {
        return Arrays.stream(player.getSailingSaveData().getCargo()).anyMatch(value -> GameItem.decodeGameItem(value).getId() == itemId);
    }

    public static int getCargoAmount(Player player, int itemId) {
        return Arrays.stream(player.getSailingSaveData().getCargo())
                .mapToObj(GameItem::decodeGameItem)
                .filter(item -> item.getId() == itemId)
                .findFirst()
                .map(GameItem::getAmount)
                .orElse(0);
    }


    public static int getIslandFromRegion(int region) {
        switch (region) {
            case 0:
                int island = new IntegerRange(0, 7).getRandomValue();
                return island == 0 ? getIslandFromRegion(region) : island;
            case 1:
                return new IntegerRange(7, 12).getRandomValue();
            case 2:
                return new IntegerRange(13, 21).getRandomValue();
            case 3:
                return new IntegerRange(22, 27).getRandomValue();
            case 4:
                return new IntegerRange(28, 34).getRandomValue();
            case 5:
                return new IntegerRange(35, 41).getRandomValue();
            case 6:
                return new IntegerRange(42, 45).getRandomValue();
            case 7:
                return new IntegerRange(46, 51).getRandomValue();
            case 8:
                return new IntegerRange(52, 100).getRandomValue();
        }
        return new IntegerRange(0, 6).getRandomValue();
    }

    public static int getDistanceFromRegion(int region) {
        switch (region) {
            case 0:
                return new IntegerRange(5, 50).getRandomValue();
            case 1:
                return new IntegerRange(50, 100).getRandomValue();
            case 2:
                return new IntegerRange(100, 250).getRandomValue();
            case 3:
                return new IntegerRange(250, 400).getRandomValue();
            case 4:
                return new IntegerRange(400, 550).getRandomValue();
            case 5:
                return new IntegerRange(550, 700).getRandomValue();
            case 6:
                return new IntegerRange(700, 850).getRandomValue();
            case 7:
                return new IntegerRange(850, 1000).getRandomValue();
            case 8:
                return new IntegerRange(1000, 1228).getRandomValue();
        }
        return new IntegerRange(0, 6).getRandomValue();
    }
}
