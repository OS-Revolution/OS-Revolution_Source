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

//        System.out.println("Low: " + getLow(startLevel, startChance));
//        System.out.println("High: " + getHigh(endLevel, endChance, getLow(startLevel, startChance)));

        int increment = 16;
        String commandFirstHalf = "fill ~ ~ ~ ~" + increment + " ~" + increment + " ~" + increment + " air replace";
        String firstCommand = "fill ~ ~ ~ ~15 ~15 ~15 air replace";
//        String command = "fill ~" + increment + " ~" + increment + " ~" + increment + " ~15 ~15 ~15 air replace";

        int perimeterLength = 21;
        int perimeterWidth = 21;
        int worldHeightChunks = 20;
        int relativeX = 1;
        int relativeY = 1;
        int relativeZ = 1;
        System.out.println("tp ~ -64 ~");
        z:for (int z = 0; z < perimeterWidth; z++) {
            x:for (int x = 0; x < perimeterLength; x++) {
                y:for (int y = 0; y < worldHeightChunks; y++) {
                    String command = "fill ~ ~ ~ " + "~" + (16 * relativeX) + " ~" + (16 * relativeY) + " ~" + (16 * relativeZ) + " air replace";
                    System.out.println(command);
                    relativeY++;
//                    if(y % 2 == 0 && y != 0) {
//                        System.out.println("tp ~ ~" + (16 * y) + " ~");
//                        relativeY = 1;
//                    }

                }
                relativeY = 1;
                relativeX ++;
//                System.out.println("tp ~16 -63 ~");
            }
            relativeZ++;
            relativeX = 1;
//            System.out.println("tp ~-336 ~ ~16");
        }
        System.out.println("tp ~ 100 ~");
//        for (int i = 0; i < perimeterSize; i++) {
//            for (int verticalChunk = 0; verticalChunk < worldHeightChunks; verticalChunk++) {
//                String command = "fill ~" + 0 + " ~" + 0 + " ~" + 0 + " ~" + (relativeX + 16) + " ~" + (relativeY + 16) + " ~" + (relativeZ + 16) + " air replace";
//                relativeX = 0;
//                System.out.println(command);
//                System.out.println("tp ~" + relativeX + " ~" + 16 + " ~" + relativeZ);
//            }
//            relativeX = increment;
//            System.out.println("tp ~" + 16 + " -64" + " ~" + relativeZ);
//        }
//            System.out.println("tp ~" + relativeX + " -64 ~" + relativeZ);
//            for (int xChunk = 0; xChunk < perimeterSize; xChunk++) {
//                relativeY = 0;
//                relativeZ = 0;
////                System.out.println("tp ~" + relativeX + " -64 ~");
//                for (int verticalChunk = 0; verticalChunk < worldHeightChunks; verticalChunk++) {
//                    String command = "fill ~" + 0 + " ~" + 0 + " ~" + 0 + " ~" + (relativeX + 16) + " ~" + (relativeY + 16) + " ~" + (relativeZ + 16) + " air replace";
//                    System.out.println(command);
//                    System.out.println("tp ~" + relativeX + " ~" + relativeY + " ~" + relativeZ);
//                }
//                relativeX = increment;
//            }
//            relativeX = -21 * 16;
//            relativeZ = increment;
//            System.out.println("\n");
//        }

//        System.out.println(firstCommand);
//        for (int horizontalChunk = 0; horizontalChunk < perimeterSize; horizontalChunk++) {
//            for (int verticalChunk = 0; verticalChunk < worldHeightChunks; verticalChunk++) {
//                if(verticalChunk % 4 == 0) {
//                    System.out.println("tp ~" + relativeX + " ~" + relativeY + " ~" + relativeZ);
//                    relativeY = 0;
//                }
//                String command = "fill ~" + relativeX + " ~" + relativeY + " ~" + relativeZ + " ~" + (relativeX + 16) + " ~" + (relativeY + 16) + " ~" + (relativeZ+16) + " air replace";
//                relativeY += increment;
//                System.out.println(command);
//            }
//            if(horizontalChunk % 4 == 0) {
////                System.out.println("#HORIZONTAL");
//                relativeX = 0;
////                relativeZ = 0;
//            }
//            relativeX += increment;
//            relativeZ += increment;
//            relativeY = -64;
//            System.out.println("\n");
//        }

    }
}
