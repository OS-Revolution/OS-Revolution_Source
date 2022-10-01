package ethos.runehub.skill.support.sailing;

import org.runehub.api.model.math.impl.IntegerRange;

import java.text.DecimalFormat;

public class SailingUtils {

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
