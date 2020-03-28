package ethos.model.content.achievement;

import java.util.EnumSet;
import java.util.Set;
import ethos.model.items.GameItem;
import ethos.model.players.Player;

/**
 * @author Jason http://www.rune-server.org/members/jason
 * @date Mar 26, 2014
 */
public class Achievements {

    public enum Achievement {
        /**
         * Tier 1 Achievement Start
         */
    	//Added achievements:
    	//Cow_Killer, 
        Cow_Killer(0, AchievementTier.TIER_1, AchievementType.SLAY_COWS, null, "Kill 100 Cows", 100, 1, new GameItem(995, 50000), new GameItem(2677)),
        Crab_Killer(1, AchievementTier.TIER_1, AchievementType.SLAY_ROCK_CRABS, null, "Kill 100 Rock Crabs", 100, 1, new GameItem(995, 55000), new GameItem(2677)),
        GIANT_SLAYER(2, AchievementTier.TIER_1, AchievementType.HILL_GIANT_SLAYER, null, "Kill 150 Hill Giants", 150, 1, new GameItem(995, 65000), new GameItem(2677)),
        CHAOS_DRUIDS(3, AchievementTier.TIER_1, AchievementType.SLAY_CHAOS_DRUIDS, null, "Kill 200 Chaos Druids", 200, 1, new GameItem(995, 70000), new GameItem(2677)),
        MASTER_ELF(4, AchievementTier.TIER_1, AchievementType.MASTER_ELF, null, "Kill 200 Elf Warriors", 200, 1, new GameItem(995, 80000), new GameItem(989, 1)),
        Bby_Dragon_Hunter_I(5, AchievementTier.TIER_1, AchievementType.SLAY_BABY_DRAGONS, null, "Kill 50 Baby Dragons", 50, 1, new GameItem(995, 100000), new GameItem(535, 100), new GameItem(2677)),
        DRAGON_SLAYER_I(7, AchievementTier.TIER_1, AchievementType.SLAY_DRAGONS, null, "Kill 25 Dragons", 25, 1, new GameItem(995, 50000), new GameItem(537, 50), new GameItem(2677)),
        PvMer_I(8, AchievementTier.TIER_1, AchievementType.SLAY_ANY_NPCS, null, "Kill 1000 Npcs", 1000, 1, new GameItem(995, 50000), new GameItem(2677)),
        Boss_Hunter_I(9, AchievementTier.TIER_1, AchievementType.SLAY_BOSSES, null, "Kill 100 Bosses", 100, 1, new GameItem(995, 50000), new GameItem(11937, 50), new GameItem(2677)),
        Fishing_Task_I(10, AchievementTier.TIER_1, AchievementType.FISH, null, "Catch 1000 Fish", 1000, 1, new GameItem(995, 50000)),
        Cooking_Task_I(11, AchievementTier.TIER_1, AchievementType.COOK, null, "Cook 1000 Fish", 1000, 1, new GameItem(995, 50000), new GameItem(3145, 50)),
        Mining_Task_I(12, AchievementTier.TIER_1, AchievementType.MINE, null, "Mine 1000 Rocks", 1000, 1, new GameItem(995, 50000), new GameItem(12013), new GameItem(12016)),
        Smithing_Task_I(13, AchievementTier.TIER_1, AchievementType.SMITH, null, "Smith 1000 Bars", 1000, 1, new GameItem(995, 50000), new GameItem(2360, 300)),
        Farming_Task_I(14, AchievementTier.TIER_1, AchievementType.FARM, null, "Pick Herbs 100 Times", 100, 1, new GameItem(995, 50000), new GameItem(208, 150), new GameItem(13646), new GameItem(13644)),
        Herblore_Task_I(15, AchievementTier.TIER_1, AchievementType.HERB, null, "Mix 500 Potions", 500, 1, new GameItem(995, 50000), new GameItem(100, 50)),
        Woodcutting_Task_I(16, AchievementTier.TIER_1, AchievementType.WOODCUT, null, "Cut 500 Trees", 500, 1, new GameItem(995, 50000), new GameItem(10933), new GameItem(10941)),
        Fletching_Task_I(17, AchievementTier.TIER_1, AchievementType.FLETCH, null, "Fletch 1000 Logs", 1000, 1, new GameItem(995, 50000), new GameItem(63, 300)),
        Firemaking_Task_I(18, AchievementTier.TIER_1, AchievementType.FIRE, null, "Light 500 Logs", 500, 1, new GameItem(995, 50000), new GameItem(20710)),
        Theiving_Task_I(19, AchievementTier.TIER_1, AchievementType.THIEV, null, "Steal 500 Times", 500, 1, new GameItem(995, 50000), new GameItem(5557), new GameItem(5556)),
        Agility_Task_I(20, AchievementTier.TIER_1, AchievementType.AGIL, null, "Complete 100 Course Laps", 100, 1, new GameItem(995, 50000), new GameItem(11849, 15)),
        Slayer_Task_I(21, AchievementTier.TIER_1, AchievementType.SLAY, null, "Complete 50 Slayer Tasks", 50, 1, new GameItem(995, 50000), new GameItem(2677)),
        CKey_Task(22, AchievementTier.TIER_1, AchievementType.LOOT_CRYSTAL_CHEST, null, "Loot Crystal Chest 50 Times", 50, 1, new GameItem(995, 50000), new GameItem(990, 5)),
        Pc_Task(23, AchievementTier.TIER_1, AchievementType.PEST_CONTROL_ROUNDS, null, "Complete Pest Control 50 Times", 50, 1, new GameItem(995, 100000), new GameItem(2677), new GameItem(8841)),
        Jad_Task(24, AchievementTier.TIER_1, AchievementType.FIGHT_CAVES_ROUNDS, null, "Complete Fightcaves 3 Times", 3, 1, new GameItem(995, 50000), new GameItem(2677), new GameItem(6570)),
        Barrows_Task_I(25, AchievementTier.TIER_1, AchievementType.BARROWS_RUNS, null, "Loot 100 Barrows Chests", 100, 1, new GameItem(995, 50000), new GameItem(2677)),
        ClueScroll_Task(26, AchievementTier.TIER_1, AchievementType.CLUES, null, "Loot 50 Clue Caskets", 50, 1, new GameItem(995, 50000)),
        /**
         * Tier 2 Achievement Start
         */
        Bby_Dragon_Hunter_II(0, AchievementTier.TIER_2, AchievementType.SLAY_BABY_DRAGONS, null, "Kill 400 Baby Dragons", 400, 2, new GameItem(995, 250000), new GameItem(535, 250), new GameItem(2801)),
        Dragon_Hunter_II(1, AchievementTier.TIER_2, AchievementType.SLAY_DRAGONS, null, "Kill 350 Dragons", 350, 2, new GameItem(995, 250000), new GameItem(537, 200), new GameItem(2801)),
        KBD_DESTROYER(1, AchievementTier.TIER_2, AchievementType.SLAY_BOSSES, null, "Kill the QBD 50 Times", 50, 2, new GameItem(995, 300000), new GameItem(537, 350), new GameItem(1215), new GameItem(13510, 2), new GameItem(7980, 2)),
        PvMer_II(2, AchievementTier.TIER_2, AchievementType.SLAY_ANY_NPCS, null, "Kill 3000 Npcs", 3000, 2, new GameItem(995, 250000), new GameItem(2801)),
        Boss_Hunter_II(3, AchievementTier.TIER_2, AchievementType.SLAY_BOSSES, null, "Kill 700 Bosses", 700, 2, new GameItem(995, 250000), new GameItem(2801)),
        INTERMEDIATE_FISHER(4, AchievementTier.TIER_2, AchievementType.FISH, null, "Catch 2500 Fish", 2500, 2, new GameItem(995, 250000)),
        INTERMEDIATE_CHEF(5, AchievementTier.TIER_2, AchievementType.COOK, null, "Cook 2500 Fish", 2500, 2, new GameItem(995, 250000), new GameItem(11937, 200)),
        INTERMEDIATE_MINER(6, AchievementTier.TIER_2, AchievementType.MINE, null, "Mine 2500 Rocks", 2500, 2, new GameItem(995, 250000), new GameItem(12015)),
        INTERMEDIATE_SMITH(7, AchievementTier.TIER_2, AchievementType.SMITH, null, "Smelt/Smith 2500 Bars", 2500, 2, new GameItem(995, 250000), new GameItem(2362, 300)),
        INTERMEDIATE_FARMER(8, AchievementTier.TIER_2, AchievementType.FARM, null, "Pick Herbs 300 Times", 300, 2, new GameItem(995, 250000), new GameItem(2486, 300), new GameItem(13640)),
        INTERMEDIATE_MIXER(9, AchievementTier.TIER_2, AchievementType.HERB, null, "Mix 1000 Potions", 1000, 2, new GameItem(995, 250000), new GameItem(3005, 50)),
        INTERMEDIATE_CHOPPER(10, AchievementTier.TIER_2, AchievementType.WOODCUT, null, "Cut 1000 Trees", 1000, 2, new GameItem(995, 250000), new GameItem(10940)),
        INTERMEDIATE_FLETCHER(11, AchievementTier.TIER_2, AchievementType.FLETCH, null, "Fletch 2500 Logs", 2500, 2, new GameItem(995, 250000), new GameItem(67, 300)),
        INTERMEDIATE_PYRO(12, AchievementTier.TIER_2, AchievementType.FIRE, null, "Light 1000 Logs", 1000, 2, new GameItem(995, 250000), new GameItem(20706), new GameItem(20704)),
        INTERMEDIATE_THIEF(13, AchievementTier.TIER_2, AchievementType.THIEV, null, "Steal 1000 Times", 1000, 2, new GameItem(995, 250000), new GameItem(5555), new GameItem(5553)),
        INTERMEDIATE_RUNNER(14, AchievementTier.TIER_2, AchievementType.AGIL, null, "Complete 250 Course Laps", 250, 2, new GameItem(995, 250000), new GameItem(11849, 50)),
        SLAYER_DESTROYER(15, AchievementTier.TIER_2, AchievementType.SLAY, null, "Complete 80 Slayer Tasks", 80, 2, new GameItem(995, 250000), new GameItem(2801)),
        CHEST_LOOTER(16, AchievementTier.TIER_2, AchievementType.LOOT_CRYSTAL_CHEST, null, "Loot Crystal Chest 100 Times", 100, 2, new GameItem(995, 250000), new GameItem(990, 15)),
        MR_PORTAL(17, AchievementTier.TIER_2, AchievementType.PEST_CONTROL_ROUNDS, null, "Complete Pest Control 120 Times", 120, 2, new GameItem(995, 250000), new GameItem(2801, 2)),
        RED_OF_FURY(18, AchievementTier.TIER_2, AchievementType.FIGHT_CAVES_ROUNDS, null, "Complete Fightcaves 5 Times", 5, 2, new GameItem(995, 250000), new GameItem(2801), new GameItem(6570, 1)),
        DHAROKER(19, AchievementTier.TIER_2, AchievementType.BARROWS_RUNS, null, "Loot 300 Barrows Chests", 300, 2, new GameItem(995, 250000), new GameItem(2801)),
        CLUE_SCROLLER(20, AchievementTier.TIER_2, AchievementType.CLUES, null, "Loot 120 Clue Caskets", 120, 2, new GameItem(995, 250000)),

        /**
         * Tier 3 Achievement Start
         */
        Devils_Assistant(1, AchievementTier.TIER_3, AchievementType.SLAY_SMOKE_DEVILS, null, "Kill 1500 Smoke Devils" , 1500, 1, new GameItem(995, 55000), new GameItem(2677)),
        EXPERT_BABY_DRAGON_SLAYER(0, AchievementTier.TIER_3, AchievementType.SLAY_BABY_DRAGONS, null, "Kill 1000 Baby Dragons", 1000, 3, new GameItem(995, 500000), new GameItem(19835), new GameItem(535, 1100)),
        EXPERT_DRAGON_SLAYER(1, AchievementTier.TIER_3, AchievementType.SLAY_DRAGONS, null, "Kill 950 Dragons", 950, 3, new GameItem(995, 1000000), new GameItem(19835), new GameItem(537, 1000)),
        SLAUGHTERER(2, AchievementTier.TIER_3, AchievementType.SLAY_ANY_NPCS, null, "Kill 10000 Npcs", 10000, 3, new GameItem(995, 1000000), new GameItem(19835, 3)),
        BOSS_SLAUGHTERER(3, AchievementTier.TIER_3, AchievementType.SLAY_BOSSES, null, "Kill 1500 Bosses", 1500, 3, new GameItem(995, 5000000), new GameItem(19835, 4)),
        EXPERT_FISHER(4, AchievementTier.TIER_3, AchievementType.FISH, null, "Catch 5000 Fish", 5000, 3, new GameItem(995, 1000000)),
        EXPERT_CHEF(5, AchievementTier.TIER_3, AchievementType.COOK, null, "Cook 5000 Fish", 5000, 3, new GameItem(995, 1000000), new GameItem(11937, 500)),
        EXPERT_MINER(6, AchievementTier.TIER_3, AchievementType.MINE, null, "Mine 5000 Rocks", 5000, 3, new GameItem(995, 1000000), new GameItem(12014)),
        EXPERT_SMITH(7, AchievementTier.TIER_3, AchievementType.SMITH, null, "Smelt/Smith 5000 Bars", 5000, 3, new GameItem(995, 1000000), new GameItem(2364, 300)),
        EXPERT_FARMER(8, AchievementTier.TIER_3, AchievementType.FARM, null, "Pick Herbs 600 Times", 600, 3, new GameItem(995, 1000000), new GameItem(220, 500), new GameItem(13642)),
        EXPERT_MIXER(9, AchievementTier.TIER_3, AchievementType.HERB, null, "Mix 2500 Potions", 2500, 3, new GameItem(995, 1000000), new GameItem(112, 100)),
        EXPERT_CHOPPER(10, AchievementTier.TIER_3, AchievementType.WOODCUT, null, "Cut 2500 Trees", 2500, 3, new GameItem(995, 1000000), new GameItem(10939)),
        EXPERT_FLETCHER(11, AchievementTier.TIER_3, AchievementType.FLETCH, null, "Fletch 5000 Logs", 5000, 3, new GameItem(995, 1000000), new GameItem(71, 300)),
        EXPERT_PYRO(12, AchievementTier.TIER_3, AchievementType.FIRE, null, "Light 2500 Logs", 2500, 3, new GameItem(995, 1000000), new GameItem(20708), new GameItem(20712)),
        EXPERT_THIEF(13, AchievementTier.TIER_3, AchievementType.THIEV, null, "Steal 3000 Times", 3000, 3, new GameItem(995, 1000000), new GameItem(5554)),
        EXPERT_RUNNER(14, AchievementTier.TIER_3, AchievementType.ROOFTOP, null, "Complete 300 Rooftop Course Laps", 300, 3, new GameItem(995, 1000000), new GameItem(11849, 100)),
        SLAYER_EXPERT(15, AchievementTier.TIER_3, AchievementType.SLAY, null, "Complete 150 Slayer Tasks", 150, 3, new GameItem(995, 5000000), new GameItem(19835, 2)),
        DIG_FOR_GOLD(16, AchievementTier.TIER_3, AchievementType.LOOT_CRYSTAL_CHEST, null, "Loot Crystal Chest 200 Times", 200, 3, new GameItem(995, 1000000), new GameItem(990, 30)),
        KNIGHT(17, AchievementTier.TIER_3, AchievementType.PEST_CONTROL_ROUNDS, null, "Complete Pest Control 200 Times", 200, 3, new GameItem(995, 1000000), new GameItem(19835, 2)),
        TZHAAR(18, AchievementTier.TIER_3, AchievementType.FIGHT_CAVES_ROUNDS, null, "Complete Fightcaves 10 Times", 10, 3, new GameItem(995, 1000000), new GameItem(6570, 2), new GameItem(19835)),
        LOOTER(19, AchievementTier.TIER_3, AchievementType.BARROWS_RUNS, null, "Loot 500 Barrows Chests", 500, 3, new GameItem(995, 10000000), new GameItem(19835, 2)),
        CLUE_CHAMP(20, AchievementTier.TIER_3, AchievementType.CLUES, null, "Loot 180 Clue Caskets", 180, 3, new GameItem(995, 1000000), new GameItem(20164));

        private AchievementTier tier;
        private AchievementRequirement requirement;
        private AchievementType type;
        private String description;
        private int amount, identification, points;
        private final GameItem[] rewards;

        Achievement(int identification, AchievementTier tier, AchievementType type, AchievementRequirement requirement, String description, int amount, int points, GameItem... rewards) {
            this.identification = identification;
            this.tier = tier;
            this.type = type;
            this.requirement = requirement;
            this.description = description;
            this.amount = amount;
            this.points = points;
            this.rewards = rewards;

            //format the items
            for (GameItem b : rewards) if (b.getAmount() == 0) b.setAmount(1);

        }

        public int getId() {
            return identification;
        }

        public AchievementTier getTier() {
            return tier;
        }

        public AchievementType getType() {
            return type;
        }

        public AchievementRequirement getRequirement() {
            return requirement;
        }

        public String getDescription() {
            return description;
        }

        public int getAmount() {
            return amount;
        }

        public int getPoints() {
            return points;
        }

        public GameItem[] getRewards() {
            return rewards;
        }

        public static final Set<Achievement> ACHIEVEMENTS = EnumSet.allOf(Achievement.class);

        public static Achievement getAchievement(AchievementTier tier, int ordinal) {
            for (Achievement achievement : ACHIEVEMENTS)
                if (achievement.getTier() == tier && achievement.ordinal() == ordinal)
                    return achievement;
            return null;
        }

        public static boolean hasRequirement(Player player, AchievementTier tier, int ordinal) {
            for (Achievement achievement : ACHIEVEMENTS) {
                if (achievement.getTier() == tier && achievement.ordinal() == ordinal) {
                    if (achievement.getRequirement() == null)
                        return true;
                    if (achievement.getRequirement().isAble(player))
                        return true;
                }
            }
            return false;
        }
    }

    public static void increase(Player player, AchievementType type, int amount) {
        for (Achievement achievement : Achievement.ACHIEVEMENTS) {
            if (achievement.getType() == type) {
                if (achievement.getRequirement() == null || achievement.getRequirement().isAble(player)) {
                    int currentAmount = player.getAchievements().getAmountRemaining(achievement.getTier().ordinal(), achievement.getId());
                    int tier = achievement.getTier().ordinal();
                    if (currentAmount < achievement.getAmount() && !player.getAchievements().isComplete(achievement.getTier().ordinal(), achievement.getId())) {
                        player.getAchievements().setAmountRemaining(tier, achievement.getId(), currentAmount + amount);
                        if ((currentAmount + amount) >= achievement.getAmount()) {
                            player.getAchievements().setComplete(tier, achievement.getId(), true);
                            player.getAchievements().setPoints(achievement.getPoints() + player.getAchievements().getPoints());
                            player.sendMessage("@red@Achievement completed on tier " + (tier + 1) + ": '" + achievement.name().toLowerCase().replaceAll("_", " ") + "' and receive " + achievement.getPoints() + " point(s).", 255);
                            //add reward inventory if spots free
                            if (achievement.getRewards() != null) {
                                player.sendMessage("Your achievement reward(s) has been added to your account.");
								//player.sendMessage("Your achievement point(s) have been added to your account.");
                                for (GameItem b : achievement.getRewards())
                                    //player.getBank().getBankTab()[0].add(b.setId(b.getId()+ 1));
									player.getItems().addItemUnderAnyCircumstance(b.getId(), b.getAmount());
                                	int tier1 = achievement.getTier().ordinal();
                                	if (tier1 == 0) {
                                		player.relicUpgradeEasy();
                                	} else if (tier == 1) {
                                		player.relicUpgradeMedium();
                                	} else if (tier == 2 ) {
                                		player.relicUpgradeHard();
                                	} else {
                                		return;
                                	}
                            }
                        }
                    }
                }
            }
        }
    }

    public static void reset(Player player, AchievementType type) {
        for (Achievement achievement : Achievement.ACHIEVEMENTS) {
            if (achievement.getType() == type) {
                if (achievement.getRequirement() == null || achievement.getRequirement().isAble(player)) {
                    if (!player.getAchievements().isComplete(achievement.getTier().ordinal(), achievement.getId())) {
                        player.getAchievements().setAmountRemaining(achievement.getTier().ordinal(), achievement.getId(),
                                0);
                    }
                }
            }
        }
    }

    public static void complete(Player player, AchievementType type) {
        for (Achievement achievement : Achievement.ACHIEVEMENTS) {
            if (achievement.getType() == type) {
                if (achievement.getRequirement() != null && achievement.getRequirement().isAble(player)
                        && !player.getAchievements().isComplete(achievement.getTier().ordinal(), achievement.getId())) {
                    int tier = achievement.getTier().ordinal();
                    //String name = achievement.name().replaceAll("_", " ");
                    player.getAchievements().setAmountRemaining(tier, achievement.getId(), achievement.getAmount());
                    player.getAchievements().setComplete(tier, achievement.getId(), true);
                    player.getAchievements().setPoints(achievement.getPoints() + player.getAchievements().getPoints());
                    player.sendMessage("Achievement completed on tier " + (tier + 1) + ": '" + achievement.name().toLowerCase().replaceAll("_", " ") + "' and receive " + achievement.getPoints() + " point(s).", 255);
                }
            }
        }
    }

    public static void checkIfFinished(Player player) {
        //complete(player, AchievementType.LEARNING_THE_ROPES);
    }

    static int getMaximumAchievements() {
        return Achievement.ACHIEVEMENTS.size();
    }
}
