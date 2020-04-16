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
    	//NEW TIER 1 ACHIEVEMENTS
        COW_TIPPER(0, AchievementTier.TIER_1, AchievementType.SLAY_COWS, null, "Kill 50 Cows", 50, 1, new GameItem(995, 25000), new GameItem(2677), new GameItem(1739, 50)),
        CRAB_CAKES(1, AchievementTier.TIER_1, AchievementType.SLAY_ROCK_CRABS, null, "Kill 80 Rock Crabs", 80, 1, new GameItem(995, 30000), new GameItem(2677), new GameItem(1624, 25)),
        GIANT_HUNTER(2, AchievementTier.TIER_1, AchievementType.HILL_GIANT_SLAYER, null, "Kill 150 Hill Giants", 150, 1, new GameItem(995, 35000), new GameItem(2677), new GameItem(989), new GameItem(533, 25), new GameItem(555, 150), new GameItem(554, 150)),
        EVIL_DRUID(3, AchievementTier.TIER_1, AchievementType.SLAY_CHAOS_DRUIDS, null, "Kill 250 Chaos Druids", 250, 1, new GameItem(995, 40000), new GameItem(2677), new GameItem(989), new GameItem(556, 250), new GameItem(830, 50)),
        ELF_SLAYER(4, AchievementTier.TIER_1, AchievementType.MASTER_ELF, null, "Kill 300 Elf Warriors", 300, 1, new GameItem(995, 45000), new GameItem(2677), new GameItem(989), new GameItem(561, 250)),
        ANGRY_MONKEY(5, AchievementTier.TIER_1, AchievementType.SLAY_DEMONIC_GORILLA, null, "Kill 100 Demonic Gorillas", 100, 50000, new GameItem(995, 250000), new GameItem(2677), new GameItem(989), new GameItem(533, 50), new GameItem(9144, 150)),
        PESKY_DRAGON(6, AchievementTier.TIER_1, AchievementType.SLAY_DRAGONS, null, "Kill 25 Mithril Dragons", 25, 1, new GameItem(995, 60000), new GameItem(2677), new GameItem(989), new GameItem(537, 50), new GameItem(565, 50)),
        WACKA_MOLE(7, AchievementTier.TIER_1, AchievementType.SLAY_GIANT_MOLE, null, "Kill 20 Giant Moles", 20, 1, new GameItem(995, 100000), new GameItem(2677), new GameItem(989), new GameItem(533, 50), new GameItem(3457)),
        BARRELS_CHPI(8, AchievementTier.TIER_1, AchievementType.SLAY_BARREL_CHEST, null, "Kill 100 BarrelChests", 100, 1, new GameItem(995, 200000), new GameItem(2677), new GameItem(989), new GameItem(1319), new GameItem(1632, 5)),
        KBD_I(9, AchievementTier.TIER_1, AchievementType.SLAY_BOSSES, null, "Kill the legendary King Black Dragon 100 times", 100, 1, new GameItem(995, 300000), new GameItem(2677), new GameItem(989), new GameItem(1303), new GameItem(1748, 50)),
        PVM_I(10, AchievementTier.TIER_1, AchievementType.SLAY_ANY_NPCS, null, "Kill 500 Npcs", 500, 1, new GameItem(995, 150000), new GameItem(2677), new GameItem(989), new GameItem(1333)),
        FISHER_I(11, AchievementTier.TIER_1, AchievementType.FISH, null, "Catch 1000 Fish", 1000, 1, new GameItem(995, 150000), new GameItem(2677), new GameItem(374, 1500)),
        CHEF_I(12, AchievementTier.TIER_1, AchievementType.COOK, null, "Cook 1000 Fish", 1000, 1, new GameItem(995, 150000), new GameItem(2677), new GameItem(374, 1500)),
        MINER_I(13, AchievementTier.TIER_1, AchievementType.MINE, null, "Mine 1000 Rocks", 1000, 1, new GameItem(995, 150000), new GameItem(2677), new GameItem(989), new GameItem(448, 500)),
        SMITH_I(14, AchievementTier.TIER_1, AchievementType.SMITH, null, "Smith 1000 Bars", 1000, 1, new GameItem(995, 150000), new GameItem(2677), new GameItem(989), new GameItem(2362, 250)),
        FARMER_I(15, AchievementTier.TIER_1, AchievementType.FARM, null, "Pick Herbs 100 Times", 100, 1, new GameItem(995, 150000), new GameItem(2677), new GameItem(989), new GameItem(5295, 50)),
        MIXER_I(16, AchievementTier.TIER_1, AchievementType.HERB, null, "Mix 500 Potions", 500, 1, new GameItem(995, 150000), new GameItem(2677), new GameItem(989), new GameItem(228, 250)),
        WOODCUTTER_I(17, AchievementTier.TIER_1, AchievementType.WOODCUT, null, "Cut 500 Trees", 500, 1, new GameItem(995, 150000), new GameItem(2677), new GameItem(989), new GameItem(1359), new GameItem(1516, 200)),
        FLETCHER_I(18, AchievementTier.TIER_1, AchievementType.FLETCH, null, "Fletch 1000 Logs", 1000, 1, new GameItem(995, 150000), new GameItem(2677), new GameItem(989), new GameItem(822, 150), new GameItem(314, 100)),
        PYRO_I(19, AchievementTier.TIER_1, AchievementType.FIRE, null, "Light 500 Logs", 500, 1, new GameItem(995, 150000), new GameItem(2677), new GameItem(989), new GameItem(1516, 250), new GameItem(2528)),
        THIEF_I(20, AchievementTier.TIER_1, AchievementType.THIEV, null, "Steal 500 Times", 500, 1, new GameItem(995, 150000), new GameItem(2677), new GameItem(989), new GameItem(2528), new GameItem(20731, 50)),
        RUNNER_I(21, AchievementTier.TIER_1, AchievementType.AGIL, null, "Complete 100 Course Laps", 100, 1, new GameItem(995, 300000), new GameItem(2677), new GameItem(989), new GameItem(2528)),
        BBY_DRAG_I(22, AchievementTier.TIER_1, AchievementType.SLAY_BABY_DRAGONS, null, "Kill 50 Baby Dragons", 50, 1, new GameItem(995, 250000), new GameItem(2677), new GameItem(535, 25)),
        BOSS_HUNTER_I(23, AchievementTier.TIER_1, AchievementType.SLAY_BOSSES, null, "Kill 100 Bosses", 100, 1, new GameItem(995, 150000), new GameItem(2677), new GameItem(989)),
        SLAY_MASTER_I(24, AchievementTier.TIER_1, AchievementType.SLAY, null, "Complete 50 Slayer Tasks", 50, 1, new GameItem(995, 200000), new GameItem(2677), new GameItem(989), new GameItem(2528), new GameItem(4170)),
        LOOTER_I(25, AchievementTier.TIER_1, AchievementType.LOOT_CRYSTAL_CHEST, null, "Loot Crystal Chest 50 Times", 50, 1, new GameItem(995, 150000), new GameItem(2677), new GameItem(989, 10)),
        PC_RUNNER(26, AchievementTier.TIER_1, AchievementType.PEST_CONTROL_ROUNDS, null, "Complete Pest Control 50 Times", 50, 1, new GameItem(995, 400000), new GameItem(2677), new GameItem(989)),
        JAD_SLAYER(27, AchievementTier.TIER_1, AchievementType.FIGHT_CAVES_ROUNDS, null, "Complete Fightcaves 3 Times", 3, 1, new GameItem(995, 180000), new GameItem(2677), new GameItem(989), new GameItem(6570)),
        BARROW_RUN_I(28, AchievementTier.TIER_1, AchievementType.BARROWS_RUNS, null, "Loot 20 Barrows Chests", 20, 1, new GameItem(995, 500000), new GameItem(2677), new GameItem(989), new GameItem(19629, 50)),
        CLUE_MASTER_I(29, AchievementTier.TIER_1, AchievementType.CLUES, null, "Loot 50 Clue Caskets", 50, 1, new GameItem(995, 150000), new GameItem(2677), new GameItem(989)),
        
        
        
        /*
         * Tier 2 Achievement Start
         */
        
        
        LIZARDMAN_SLAYER(0, AchievementTier.TIER_2, AchievementType.LIZARD_SHAMAN, null, "Kill 250 Lizardman bosses", 250, 2, new GameItem(995, 500000), new GameItem(2801), new GameItem(5300,25), new GameItem(5294, 25)),
        PESKY_DRAGON_II(1, AchievementTier.TIER_2, AchievementType.MITHRIL_DRAGON, null, "Kill 50 Mithril Dragons", 50, 2, new GameItem(995, 550000), new GameItem(2801), new GameItem(811, 500), new GameItem(9185), new GameItem(3467), new GameItem(989)),
        WACKAMOLE_II(2, AchievementTier.TIER_2, AchievementType.SLAY_GIANT_MOLE, null, "Kill 300 Giant Moles", 300, 2, new GameItem(995, 600000), new GameItem(2801), new GameItem(989), new GameItem(4153), new GameItem(565, 350), new GameItem(1147)),
        BARRELS_CHPII(3, AchievementTier.TIER_2, AchievementType.SLAY_BARREL_CHEST, null, "Kill 1000 BarrelChests", 1000, 2, new GameItem(995, 650000), new GameItem(2801), new GameItem(989), new GameItem(1373), new GameItem(560, 250), new GameItem(454, 150)),
        EVIL_SCIENTIST(4, AchievementTier.TIER_2, AchievementType.MAD_SCIENTIST, null, "Manage to kill 50 Crazy Archaeologist's!", 50, 2, new GameItem(995, 700000), new GameItem(2801), new GameItem(989), new GameItem(2435, 50), new GameItem(1750, 50)),
        CHAOTIC_WIZARD(5, AchievementTier.TIER_2, AchievementType.CHAOS_FANATIC, null, "Destroy 50 Chaos Fanatic's, If you can!", 50, 2, new GameItem(995, 800000), new GameItem(2801), new GameItem(989), new GameItem(7937, 150), new GameItem(964)),
        KBD_II(6, AchievementTier.TIER_2, AchievementType.SLAY_BOSSES, null, "Kill the legendary King Black Dragon 500 times", 500, 2, new GameItem(995, 1000000), new GameItem(2801), new GameItem(989), new GameItem(1748, 250), new GameItem(4587), new GameItem(11237, 250)),
        OCTO(7, AchievementTier.TIER_2, AchievementType.KRAKEN, null, "Kill the dangerous Cave Kraken 30 times!", 30, 2, new GameItem(995, 800000), new GameItem(2801), new GameItem(989), new GameItem(1147), new GameItem(1392, 25), new GameItem(565, 500)),
        SCALES(8, AchievementTier.TIER_2, AchievementType.ZULRAH, null, "Slay Zulrah 30 times, If you can!?", 30, 2, new GameItem(995, 1000000), new GameItem(2801), new GameItem(989), new GameItem(12934, 500), new GameItem(11230, 250)),
        PVM_II(9, AchievementTier.TIER_2, AchievementType.SLAY_ANY_NPCS, null, "Kill 1000 Npcs", 1000, 2, new GameItem(995, 750000), new GameItem(2801), new GameItem(989)),
        FISHER_II(10, AchievementTier.TIER_2, AchievementType.FISH, null, "Catch 2500 Fish", 2500, 2, new GameItem(995, 500000), new GameItem(386, 500), new GameItem(374, 500), new GameItem(2801), new GameItem(2528), new GameItem(989)),
        CHEF_II(11, AchievementTier.TIER_2, AchievementType.COOK, null, "Cook 2500 Fish", 2500, 2, new GameItem(995, 500000), new GameItem(2801), new GameItem(2528), new GameItem(989)),
        MINER_II(12, AchievementTier.TIER_2, AchievementType.MINE, null, "Mine 2500 Rocks", 2500, 2, new GameItem(995, 500000), new GameItem(2801), new GameItem(2528), new GameItem(448, 500), new GameItem(450, 300), new GameItem(989)),
        SMITH_II(13, AchievementTier.TIER_2, AchievementType.SMITH, null, "Smelt/Smith 2500 Bars", 2500, 2, new GameItem(995, 500000), new GameItem(2801), new GameItem(2528), new GameItem(2360, 250), new GameItem(2362, 250), new GameItem(1275), new GameItem(989)),
        FARMER_II(14, AchievementTier.TIER_2, AchievementType.FARM, null, "Pick Herbs 300 Times", 300, 2, new GameItem(995, 500000), new GameItem(2801), new GameItem(2528), new GameItem(256, 100), new GameItem(256, 50), new GameItem(3001, 50), new GameItem(989)),
        MIXER_II(15, AchievementTier.TIER_2, AchievementType.HERB, null, "Mix 1000 Potions", 1000, 2, new GameItem(995, 500000), new GameItem(2801), new GameItem(2528), new GameItem(2453, 150), new GameItem(2445, 150), new GameItem(12626, 150), new GameItem(6686, 150), new GameItem(989)),
        WOODCUTTER_II(16, AchievementTier.TIER_2, AchievementType.WOODCUT, null, "Cut 1000 Trees", 1000, 2, new GameItem(995, 500000), new GameItem(2801), new GameItem(2528), new GameItem(1516, 200), new GameItem(1514, 100), new GameItem(6739), new GameItem(989)),
        FLETCHER_II(17, AchievementTier.TIER_2, AchievementType.FLETCH, null, "Fletch 2500 Logs", 2500, 2, new GameItem(995, 500000), new GameItem(2801), new GameItem(2528), new GameItem(810, 250), new GameItem(989)),
        PYRO_II(18, AchievementTier.TIER_2, AchievementType.FIRE, null, "Light 1000 Logs", 1000, 2, new GameItem(995, 500000), new GameItem(2801), new GameItem(2528), new GameItem(1514, 250), new GameItem(989)),
        THIEF_II(19, AchievementTier.TIER_2, AchievementType.THIEV, null, "Steal 1000 Times", 1000, 2, new GameItem(995, 500000), new GameItem(2801), new GameItem(2528), new GameItem(989), new GameItem(2528), new GameItem(1392, 50)),
        RUNNER_II(20, AchievementTier.TIER_2, AchievementType.AGIL, null, "Complete 250 Course Laps", 250, 2, new GameItem(995, 800000), new GameItem(2801), new GameItem(2528), new GameItem(6199), new GameItem(989)),
        Bby_DRAG_II(21, AchievementTier.TIER_2, AchievementType.SLAY_BABY_DRAGONS, null, "Kill 400 Baby Dragons", 400, 2, new GameItem(995, 800000), new GameItem(2801)),
        Dragon_Hunter_II(22, AchievementTier.TIER_2, AchievementType.SLAY_DRAGONS, null, "Kill 350 Dragons", 350, 2, new GameItem(995, 500000), new GameItem(2801)),
        BOSS_HUNTER_II(23, AchievementTier.TIER_2, AchievementType.SLAY_BOSSES, null, "Kill 700 Bosses", 700, 2, new GameItem(995, 1000000), new GameItem(2801)),
        SLAYER_MASTER_II(24, AchievementTier.TIER_2, AchievementType.SLAY, null, "Complete 80 Slayer Tasks", 80, 2, new GameItem(995, 800000), new GameItem(2801)),
        LOOTER_II(25, AchievementTier.TIER_2, AchievementType.LOOT_CRYSTAL_CHEST, null, "Loot Crystal Chest 100 Times", 100, 2, new GameItem(995, 500000), new GameItem(2801)),
        PC_RUNNER_II(26, AchievementTier.TIER_2, AchievementType.PEST_CONTROL_ROUNDS, null, "Complete Pest Control 120 Times", 120, 2, new GameItem(995, 1000000), new GameItem(2801)),
        JAD_SLAYER_II(27, AchievementTier.TIER_2, AchievementType.FIGHT_CAVES_ROUNDS, null, "Complete Fightcaves 5 Times", 5, 2, new GameItem(995, 900000), new GameItem(2801)),
        BARROW_RUN_II(28, AchievementTier.TIER_2, AchievementType.BARROWS_RUNS, null, "Loot 50 Barrows Chests", 50, 2, new GameItem(995, 700000), new GameItem(2801)),
        CLUE_MASTER_II(29, AchievementTier.TIER_2, AchievementType.CLUES, null, "Loot 120 Clue Caskets", 120, 2, new GameItem(995, 600000), new GameItem(2801)),
   /**
         * Tier 3 Achievement Start
         */
        
        PVM_III(0, AchievementTier.TIER_3, AchievementType.SLAY_ANY_NPCS, null, "Kill 5000 Npcs", 5000, 3, new GameItem(995, 1), new GameItem(19835)),
        SMOKE_DEVILS(1, AchievementTier.TIER_3, AchievementType.SLAY_SMOKE_DEVILS, null, "Kill 1500 Smoke Devils" , 1500, 3, new GameItem(995, 1), new GameItem(19835)),
        PESKY_DRAGONIII(2, AchievementTier.TIER_3, AchievementType.SLAY_DRAGONS, null, "Kill 125 Mithril Dragons", 125, 3, new GameItem(995, 1), new GameItem(19835)),
        BIG_SPOODER(3, AchievementTier.TIER_3, AchievementType.VENENATIS, null, "Kill 100 Venenatis Spooders!", 100, 3, new GameItem(995, 1), new GameItem(19835)),
        NINJAS_IN_PYJAMAS(4, AchievementTier.TIER_3, AchievementType.VETION, null, "Kill 100 Vet'ion's!", 100, 3, new GameItem(995, 1), new GameItem(19835)),
        KBD_III(5, AchievementTier.TIER_3, AchievementType.SLAY_BOSSES, null, "Kill the legendary King Black Dragon 1500 times", 1500, 3, new GameItem(995, 1), new GameItem(19835)),
    	WACKAMOLE_III(6, AchievementTier.TIER_3, AchievementType.SLAY_GIANT_MOLE, null, "Kill 2500 Giant Moles", 2500, 3, new GameItem(995, 1), new GameItem(19835)),
    	BARRELS_CHPIII(7, AchievementTier.TIER_3, AchievementType.SLAY_BARREL_CHEST, null, "Kill 5000 BarrelChests", 5000, 3, new GameItem(995, 1), new GameItem(19835)),
    	BBY_DRAG_III(8, AchievementTier.TIER_3, AchievementType.SLAY_BABY_DRAGONS, null, "Kill 1000 Baby Dragons", 1000, 3, new GameItem(995, 1), new GameItem(19835)),
        DRAGON_HUNTER_III(9, AchievementTier.TIER_3, AchievementType.SLAY_DRAGONS, null, "Kill 950 Dragons", 950, 3, new GameItem(995, 1), new GameItem(19835)),
        SLAUGHTER(10, AchievementTier.TIER_3, AchievementType.SLAY_ANY_NPCS, null, "Kill 10000 Npcs", 10000, 3, new GameItem(995, 1), new GameItem(19835)),
        BOSS_HUNTER_III(11, AchievementTier.TIER_3, AchievementType.SLAY_BOSSES, null, "Kill 1500 Bosses", 1500, 3, new GameItem(995, 1), new GameItem(19835)),
        FISHER_III(12, AchievementTier.TIER_3, AchievementType.FISH, null, "Catch 5000 Fish", 5000, 3, new GameItem(995, 1), new GameItem(19835)),
        CHEF_III(13, AchievementTier.TIER_3, AchievementType.COOK, null, "Cook 5000 Fish", 5000, 3, new GameItem(995, 1), new GameItem(19835)),
        MINER_III(14, AchievementTier.TIER_3, AchievementType.MINE, null, "Mine 5000 Rocks", 5000, 3, new GameItem(995, 1), new GameItem(19835)),
        SMITH_III(15, AchievementTier.TIER_3, AchievementType.SMITH, null, "Smelt/Smith 5000 Bars", 5000, 3, new GameItem(995, 1), new GameItem(19835)),
        FARMER_III(16, AchievementTier.TIER_3, AchievementType.FARM, null, "Pick Herbs 600 Times", 600, 3, new GameItem(995, 1), new GameItem(19835)),
        MIXER_III(17, AchievementTier.TIER_3, AchievementType.HERB, null, "Mix 2500 Potions", 2500, 3, new GameItem(995, 1), new GameItem(19835)),
        WOODCUTTER_III(18, AchievementTier.TIER_3, AchievementType.WOODCUT, null, "Cut 2500 Trees", 2500, 3, new GameItem(995, 1), new GameItem(19835)),
        FLETCHER_III(19, AchievementTier.TIER_3, AchievementType.FLETCH, null, "Fletch 5000 Logs", 5000, 3, new GameItem(995, 1), new GameItem(19835)),
        PYRO_III(20, AchievementTier.TIER_3, AchievementType.FIRE, null, "Light 2500 Logs", 2500, 3, new GameItem(995, 1), new GameItem(19835)),
        THIEF_III(21, AchievementTier.TIER_3, AchievementType.THIEV, null, "Steal 3000 Times", 3000, 3, new GameItem(995, 1), new GameItem(19835)),
        RUNNER_III(22, AchievementTier.TIER_3, AchievementType.ROOFTOP, null, "Complete 300 Rooftop Course Laps", 300, 3, new GameItem(995, 1), new GameItem(19835)),
        SLAYER_MASTER_III(23, AchievementTier.TIER_3, AchievementType.SLAY, null, "Complete 150 Slayer Tasks", 150, 3, new GameItem(995, 1), new GameItem(19835)),
        DIG_FOR_GOLD(24, AchievementTier.TIER_3, AchievementType.LOOT_CRYSTAL_CHEST, null, "Loot Crystal Chest 200 Times", 200, 3, new GameItem(995, 1), new GameItem(19835)),
        KNIGHT(25, AchievementTier.TIER_3, AchievementType.PEST_CONTROL_ROUNDS, null, "Complete Pest Control 200 Times", 200, 3, new GameItem(995, 1), new GameItem(19835)),
        TZHAAR(26, AchievementTier.TIER_3, AchievementType.FIGHT_CAVES_ROUNDS, null, "Complete Fightcaves 10 Times", 10, 3, new GameItem(995, 1), new GameItem(19835)),
        LOOTER(27, AchievementTier.TIER_3, AchievementType.BARROWS_RUNS, null, "Loot 100 Barrows Chests", 100, 3, new GameItem(995, 1), new GameItem(19835)),
        CLUE_MASTER_III(28, AchievementTier.TIER_3, AchievementType.CLUES, null, "Loot 180 Clue Caskets", 180, 3, new GameItem(995, 1), new GameItem(19835));
        
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
                            player.sendMessage("@blu@Achievement completed on tier " + (tier + 1) + ": '" + achievement.name().toLowerCase().replaceAll("_", " ") + "' and receive " + achievement.getPoints() + " point(s).", 255);
                            //add reward inventory if spots free
                            if (achievement.getRewards() != null) {
                                player.sendMessage("@blu@Your achievement reward(s) has been added to your account.");
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
