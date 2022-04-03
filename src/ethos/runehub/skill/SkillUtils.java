package ethos.runehub.skill;

public class SkillUtils {

    private static int getLow(int startingLevel, double startingChance) {
        for (int low = 0; low < 256; low++) {
            double chance = Math.floor(low * (99 - startingLevel) / 98.0) / 256;
            if (chance == startingChance) {
                return low;
            }
        }
        return 1;
    }

    private static int getHigh(int startingLevel, double startingChance, int low) {
        double brOne = Math.floor(low * (99 - startingLevel) / 98.0);
        for (int high = 0; high < 1000; high++) {
            double brTwo = Math.floor(high * (startingLevel - 1) / 98.0);
            double top = 1 + brOne + brTwo;
            double value = top / 256;
            if (value == startingChance) {
                return high;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        int startLevel = 1;
        int endLevel = 99;

        double startChance = 0.5;
        double endChance = 1.0;

        System.out.println("Low: " + getLow(startLevel,startChance));
        System.out.println("High: " + getHigh(endLevel,endChance,getLow(startLevel,startChance)));

    }
}
