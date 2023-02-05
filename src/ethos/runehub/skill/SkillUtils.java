package ethos.runehub.skill;

import ethos.model.players.Player;
import ethos.runehub.TimeUtils;
import ethos.runehub.event.FixedScheduleEvent;
import ethos.runehub.event.FixedScheduledEventController;
import ethos.runehub.event.dnd.PlayPassStartSeasonEvent;
import ethos.runehub.event.dnd.SkillOfTheHourFixedScheduleEvent;
import ethos.runehub.event.holiday.EndChristmasFixedScheduleEvent;
import ethos.runehub.event.holiday.StartChristmasFixedScheduleEvent;
import ethos.util.Misc;
import org.runehub.api.util.SkillDictionary;

import java.time.*;
import java.util.Arrays;

public class SkillUtils {

    public static int getRuneSource(Player player, int runeId) {
        return -1;
    }

    public static int hasRune(Player player, int runeId, int amount) {
        boolean[] waterChecks = {player.getItems().isWearingItem(1383) //water staff
                , player.getItems().isWearingItem(1395) //water bstaff
                , player.getItems().isWearingItem(1403) //mystic water staff
                , player.getItems().isWearingItem(11787) //steam bstaff
                , player.getItems().isWearingItem(11789) //steam mstaff
                , player.getItems().isWearingItem(12795) //steam bstaff or
                , player.getItems().isWearingItem(12796) //steam mstaff or
                , player.getItems().isWearingItem(6562) //mud bstaff
                , player.getItems().isWearingItem(6563) //mud mstaff
                , player.getItems().isWearingItem(6726) //mud bstaff
                , player.getItems().isWearingItem(6728) //mud mstaff
                , player.getItems().isWearingItem(20730) //mist bstaff
                , player.getItems().isWearingItem(20733) //mist mstaff
                , player.getItems().playerHasItem(4694, amount) //steam rune
                , player.getItems().playerHasItem(4698, amount) //mud rune
                , player.getItems().playerHasItem(4695, amount) //mist rune
                , player.getItems().playerHasItem(555, amount) //steam rune
        };
        switch (runeId) {
            case 555:
                for (int i = 0; i < waterChecks.length; i++) {
                    if (waterChecks[i]) {
                        switch (i) {
                            case 0:
                                return 1383;
                            case 1:
                                return 1395;
                            case 2:
                                return 1403;
                            case 3:
                                return 11787;
                        }
                    }
                }
//                        return (
//                                player.getItems().isWearingItem(1383) //water staff
//                                        || player.getItems().isWearingItem(1395) //water bstaff
//                                        || player.getItems().isWearingItem(1403) //mystic water staff
//                                        || player.getItems().isWearingItem(11787) //steam bstaff
//                                        || player.getItems().isWearingItem(11789) //steam mstaff
//                                        || player.getItems().isWearingItem(12795) //steam bstaff or
//                                        || player.getItems().isWearingItem(12796) //steam mstaff or
//                                        || player.getItems().isWearingItem(6562) //mud bstaff
//                                        || player.getItems().isWearingItem(6563) //mud mstaff
//                                        || player.getItems().isWearingItem(6726) //mud bstaff
//                                        || player.getItems().isWearingItem(6728) //mud mstaff
//                                        || player.getItems().isWearingItem(20730) //mist bstaff
//                                        || player.getItems().isWearingItem(20733) //mist mstaff
//                                        || player.getItems().playerHasItem(4694, amount) //steam rune
//                                        || player.getItems().playerHasItem(4698, amount) //mud rune
//                                        || player.getItems().playerHasItem(4695, amount) //mist rune
//                                        || player.getItems().playerHasItem(555, amount) //steam rune
//                        );
        }
        return -1;
    }

    public static String getSkillName(int skillId) {
        if (Arrays.stream(SkillDictionary.Skill.values()).anyMatch(skill -> skill.getId() == skillId)) {
            if (skillId == SkillDictionary.Skill.FARMING.getId()) {
                return "Foraging";
            }
            return Misc.capitalize(SkillDictionary.getSkillNameFromId(skillId).toLowerCase());
        } else if (skillId == 23) {
            return "Sailing";
        }
        return "N/A";
    }

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
        FixedScheduleEvent hourlyEvent = new SkillOfTheHourFixedScheduleEvent();
        FixedScheduleEvent seasonEvent = new PlayPassStartSeasonEvent();
        FixedScheduleEvent xmas = new StartChristmasFixedScheduleEvent();
        FixedScheduleEvent xmasEnd = new EndChristmasFixedScheduleEvent();
        ZonedDateTime d1 = ZonedDateTime.now(ZoneId.of("UTC")).withMonth(12).withDayOfMonth(25).withHour(0).withMinute(0).withSecond(0);
        ZonedDateTime d2 = ZonedDateTime.of(LocalDate.of(ZonedDateTime.now(ZoneId.of("UTC")).getYear(), 12, 25), LocalTime.of(0, 0), ZoneId.of("UTC"));
        long msUntilXmas = TimeUtils.getMSUntilDate(ZonedDateTime.now(ZoneId.of("UTC")).withMonth(12).withDayOfMonth(25).withHour(0).withMinute(0).withSecond(0));
        Duration duration1 = TimeUtils.getDurationBetween(ZonedDateTime.now(ZoneId.of("UTC")).withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0), d1);
        Duration duration2 = TimeUtils.getDurationBetween(ZonedDateTime.now(ZoneId.of("UTC")).withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0), ZonedDateTime.now(ZoneId.of("UTC")));
        System.out.println("XMAS: " + TimeUtils.getZDTString(ZonedDateTime.ofInstant(Instant.ofEpochMilli(msUntilXmas), ZoneId.of("UTC"))));
        System.out.println(TimeUtils.getDurationString(msUntilXmas - ZonedDateTime.now(ZoneId.of("UTC")).toInstant().toEpochMilli()));
        System.out.println("Duration: " + TimeUtils.getDurationString(duration1.minus(duration2)));
        System.out.println("First: " + TimeUtils.getZDTString(FixedScheduledEventController.getInstance().getFirstRun(hourlyEvent)));
        System.out.println("Next: " + TimeUtils.getZDTString(FixedScheduledEventController.getInstance().getNextCycle(hourlyEvent)));
        System.out.println(" ");
        System.out.println("First: " + TimeUtils.getZDTString(FixedScheduledEventController.getInstance().getFirstRun(seasonEvent)));
        System.out.println("Next: " + TimeUtils.getZDTString(FixedScheduledEventController.getInstance().getNextCycle(seasonEvent)));
        System.out.println(" ");
        System.out.println("First: " + TimeUtils.getZDTString(FixedScheduledEventController.getInstance().getFirstRun(xmas)));
        System.out.println("Next: " + TimeUtils.getZDTString(FixedScheduledEventController.getInstance().getNextCycle(xmas)));
        System.out.println(" ");
        System.out.println("First: " + TimeUtils.getZDTString(FixedScheduledEventController.getInstance().getFirstRun(xmasEnd)));
        System.out.println("Next: " + TimeUtils.getZDTString(FixedScheduledEventController.getInstance().getNextCycle(xmasEnd)));
//            int startLevel = 1;
//            int endLevel = 99;
//
//            double startChance = 0.5;
//            double endChance = 1.0;
//
////        System.out.println("Low: " + getLow(startLevel, startChance));
////        System.out.println("High: " + getHigh(endLevel, endChance, getLow(startLevel, startChance)));
//
//            int increment = 16;
//            String commandFirstHalf = "fill ~ ~ ~ ~" + increment + " ~" + increment + " ~" + increment + " air replace";
//            String firstCommand = "fill ~ ~ ~ ~15 ~15 ~15 air replace";
////        String command = "fill ~" + increment + " ~" + increment + " ~" + increment + " ~15 ~15 ~15 air replace";
//
//            int perimeterLength = 21;
//            int perimeterWidth = 21;
//            int worldHeightChunks = 20;
//            int relativeX = 1;
//            int relativeY = 1;
//            int relativeZ = 1;
//            System.out.println("tp ~ -64 ~");
//            z:
//            for (int z = 0; z < perimeterWidth; z++) {
//                x:
//                for (int x = 0; x < perimeterLength; x++) {
//                    y:
//                    for (int y = 0; y < worldHeightChunks; y++) {
//                        String command = "fill ~ ~ ~ " + "~" + (16 * relativeX) + " ~" + (16 * relativeY) + " ~" + (16 * relativeZ) + " air replace";
//                        System.out.println(command);
//                        relativeY++;
////                    if(y % 2 == 0 && y != 0) {
////                        System.out.println("tp ~ ~" + (16 * y) + " ~");
////                        relativeY = 1;
////                    }
//
//                    }
//                    relativeY = 1;
//                    relativeX++;
////                System.out.println("tp ~16 -63 ~");
//                }
//                relativeZ++;
//                relativeX = 1;
////            System.out.println("tp ~-336 ~ ~16");
//            }
//            System.out.println("tp ~ 100 ~");
////        for (int i = 0; i < perimeterSize; i++) {
////            for (int verticalChunk = 0; verticalChunk < worldHeightChunks; verticalChunk++) {
////                String command = "fill ~" + 0 + " ~" + 0 + " ~" + 0 + " ~" + (relativeX + 16) + " ~" + (relativeY + 16) + " ~" + (relativeZ + 16) + " air replace";
////                relativeX = 0;
////                System.out.println(command);
////                System.out.println("tp ~" + relativeX + " ~" + 16 + " ~" + relativeZ);
////            }
////            relativeX = increment;
////            System.out.println("tp ~" + 16 + " -64" + " ~" + relativeZ);
////        }
////            System.out.println("tp ~" + relativeX + " -64 ~" + relativeZ);
////            for (int xChunk = 0; xChunk < perimeterSize; xChunk++) {
////                relativeY = 0;
////                relativeZ = 0;
//////                System.out.println("tp ~" + relativeX + " -64 ~");
////                for (int verticalChunk = 0; verticalChunk < worldHeightChunks; verticalChunk++) {
////                    String command = "fill ~" + 0 + " ~" + 0 + " ~" + 0 + " ~" + (relativeX + 16) + " ~" + (relativeY + 16) + " ~" + (relativeZ + 16) + " air replace";
////                    System.out.println(command);
////                    System.out.println("tp ~" + relativeX + " ~" + relativeY + " ~" + relativeZ);
////                }
////                relativeX = increment;
////            }
////            relativeX = -21 * 16;
////            relativeZ = increment;
////            System.out.println("\n");
////        }
//
////        System.out.println(firstCommand);
////        for (int horizontalChunk = 0; horizontalChunk < perimeterSize; horizontalChunk++) {
////            for (int verticalChunk = 0; verticalChunk < worldHeightChunks; verticalChunk++) {
////                if(verticalChunk % 4 == 0) {
////                    System.out.println("tp ~" + relativeX + " ~" + relativeY + " ~" + relativeZ);
////                    relativeY = 0;
////                }
////                String command = "fill ~" + relativeX + " ~" + relativeY + " ~" + relativeZ + " ~" + (relativeX + 16) + " ~" + (relativeY + 16) + " ~" + (relativeZ+16) + " air replace";
////                relativeY += increment;
////                System.out.println(command);
////            }
////            if(horizontalChunk % 4 == 0) {
//////                System.out.println("#HORIZONTAL");
////                relativeX = 0;
//////                relativeZ = 0;
////            }
////            relativeX += increment;
////            relativeZ += increment;
////            relativeY = -64;
////            System.out.println("\n");
////        }

    }
}
