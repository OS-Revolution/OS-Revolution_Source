package ethos.runehub.entity.player;

import ethos.runehub.entity.item.GameItem;
import ethos.runehub.skill.gathering.farming.FarmingConfig;
import ethos.runehub.skill.gathering.farming.patch.PatchType;
import org.runehub.api.model.math.impl.AdjustableDouble;
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.util.SkillDictionary;

import java.util.*;

public class PlayerSaveData {

    public long getPlayerId() {
        return playerId;
    }

    public long getJoinTimestamp() {
        return joinTimestamp;
    }

    public long getLoginTimestamp() {
        return loginTimestamp;
    }

    public long getLogoutTimestamp() {
        return logoutTimestamp;
    }

    public void setJoinTimestamp(long joinTimestamp) {
        this.joinTimestamp = joinTimestamp;
    }

    public void setLoginTimestamp(long loginTimestamp) {
        this.loginTimestamp = loginTimestamp;
    }

    public void setLogoutTimestamp(long logoutTimestamp) {
        this.logoutTimestamp = logoutTimestamp;
    }

    public AdjustableDouble getMagicFind() {
        return magicFind;
    }

    public Map<Integer, Integer> getSkillAnimationOverrideMap() {
        return skillAnimationOverrideMap;
    }

    public void setLastHomeTeleportTimestamp(long lastHomeTeleportTimestamp) {
        this.lastHomeTeleportTimestamp = lastHomeTeleportTimestamp;
    }

    public long getLastHomeTeleportTimestamp() {
        return lastHomeTeleportTimestamp;
    }

    public AdjustableInteger getInstantTeleportCharges() {
        return instantTeleportCharges;
    }

    public boolean isHomeUnlocked() {
        return homeUnlocked;
    }

    public int getAltarSpace() {
        return altarSpace;
    }

    public int getCrystalBallSpace() {
        return crystalBallSpace;
    }

    public int getPoolSpace() {
        return poolSpace;
    }

    public int getHeraldrySpace() {
        return heraldrySpace;
    }

    public int getJewelleryBoxSpace() {
        return jewelleryBoxSpace;
    }

    public int getLecternSpace() {
        return lecternSpace;
    }

    public int getPortalSpace() {
        return portalSpace;
    }

    public int getSpellSpace() {
        return spellSpace;
    }

    public int getTelescopeSpace() {
        return telescopeSpace;
    }

    public void setAltarSpace(int altarSpace) {
        this.altarSpace = altarSpace;
    }

    public void setCrystalBallSpace(int crystalBallSpace) {
        this.crystalBallSpace = crystalBallSpace;
    }

    public void setHeraldrySpace(int heraldrySpace) {
        this.heraldrySpace = heraldrySpace;
    }

    public void setHomeUnlocked(boolean homeUnlocked) {
        this.homeUnlocked = homeUnlocked;
    }

    public void setJewelleryBoxSpace(int jewelleryBoxSpace) {
        this.jewelleryBoxSpace = jewelleryBoxSpace;
    }

    public void setLecternSpace(int lecternSpace) {
        this.lecternSpace = lecternSpace;
    }

    public void setPoolSpace(int poolSpace) {
        this.poolSpace = poolSpace;
    }

    public void setPortalSpace(int portalSpace) {
        this.portalSpace = portalSpace;
    }

    public void setSpellSpace(int spellSpace) {
        this.spellSpace = spellSpace;
    }

    public void setTelescopeSpace(int telescopeSpace) {
        this.telescopeSpace = telescopeSpace;
    }

    public int getExchangeSlots() {
        return exchangeSlots;
    }

    public void setExchangeSlots(int exchangeSlots) {
        this.exchangeSlots = exchangeSlots;
    }

    public boolean isInitiativePackageClaimed() {
        return initiativePackageClaimed;
    }

    public void setInitiativePackageClaimed(boolean initiativePackageClaimed) {
        this.initiativePackageClaimed = initiativePackageClaimed;
    }

    public List<Long> getClaimedRewardCodes() {
        return claimedRewardCodes;
    }

    public void setClaimedRewardCodes(List<Long> claimedRewardCodes) {
        this.claimedRewardCodes = claimedRewardCodes;
    }

    public Map<Integer, AdjustableInteger> getBonusXp() {
        if (!bonusXp.containsKey(SkillDictionary.Skill.SAILING.getId()))
            bonusXp.put(SkillDictionary.Skill.SAILING.getId(),new AdjustableInteger(0));
        return bonusXp;
    }

    public void setBonusXp(Map<Integer, AdjustableInteger> bonusXp) {
        this.bonusXp = bonusXp;
    }

    public Map<Integer, Long> getBonusXpTimers() {
        return bonusXpTimers;
    }

    public void setBonusXpTimers(Map<Integer, Long> bonusXpTimers) {
        this.bonusXpTimers = bonusXpTimers;
    }

    public AdjustableInteger getTeleportCharges() {
        if (teleportCharges == null)
            teleportCharges = new AdjustableInteger(0);
        return teleportCharges;
    }

    public void setInstantTeleportCharges(AdjustableInteger instantTeleportCharges) {
        this.instantTeleportCharges = instantTeleportCharges;
    }

    public boolean isDailyAvailable() {
        return dailyAvailable;
    }

    public void setDailiesAvailable(boolean dailyAvailable) {
        this.dailyAvailable = dailyAvailable;
    }

    public boolean[] getClaimedPassLevel() {
        if(claimedPassLevel == null)
            claimedPassLevel = new boolean[50];
        return claimedPassLevel;
    }

    public void updateClaimedPassLevel(int index, boolean value) {
        claimedPassLevel[index] = value;
    }

    public List<Long> getClaimedJourneyStep() {
        if(claimedJourneyStep == null)
            claimedJourneyStep = new ArrayList<>();
        return claimedJourneyStep;
    }

    public List<Integer> getCompetedJourney() {
        if (competedJourney == null)
            competedJourney = new ArrayList<>();
        return competedJourney;
    }

    public Map<Integer,List<FarmingConfig>> farmingConfig() {
        if (farmingConfigMap == null || farmingConfigMap.isEmpty()) {
            farmingConfigMap = new HashMap<>();
            farmingConfigMap.put(14391,configForFarm(14391));
            farmingConfigMap.put(10548,configForFarm(10548));
            farmingConfigMap.put(11062,configForFarm(11062));
            farmingConfigMap.put(12851,configForFarm(12851));
            farmingConfigMap.put(12083,configForFarm(12083));
            farmingConfigMap.put(10551,configForFarm(10551));
            farmingConfigMap.put(10288,configForFarm(10288));
            farmingConfigMap.put(11060,configForFarm(11060));
            farmingConfigMap.put(12596,configForFarm(12596));
            farmingConfigMap.put(11570,configForFarm(11570));
            farmingConfigMap.put(10290,configForFarm(10290));
            farmingConfigMap.put(10300,configForFarm(10300));
            farmingConfigMap.put(12594,configForFarm(12594));
            farmingConfigMap.put(12854,configForFarm(12854));
            farmingConfigMap.put(12084,configForFarm(12084));
            farmingConfigMap.put(11573,configForFarm(11573));
            farmingConfigMap.put(9525,configForFarm(9525));
            farmingConfigMap.put(9782,configForFarm(9782));
            farmingConfigMap.put(11317,configForFarm(11317));
            farmingConfigMap.put(9777,configForFarm(9777));
            farmingConfigMap.put(11058,configForFarm(11058));
            farmingConfigMap.put(9265,configForFarm(9265));
        }
        return farmingConfigMap;
    }

    private List<FarmingConfig> configForFarm(int regionId) {
        final List<FarmingConfig> configs = new ArrayList<>();
        switch (regionId) {
            case 14391: //canafis
            case 10548: //ardougne
            case 11062: //catherby
            case 12083:
                configs.add(new FarmingConfig(0, 0, false, false, PatchType.ALLOTMENT.ordinal(), 0, 0));
                configs.add(new FarmingConfig(0, 8, false, false, PatchType.ALLOTMENT.ordinal(), 0, 0));
                configs.add(new FarmingConfig(0, 16, false, false, PatchType.FLOWER.ordinal(), 0, 0));
                configs.add(new FarmingConfig(0, 24, false, false, PatchType.HERB.ordinal(), 0, 0));
                break;
            case 12851:
            case 10551:
            case 10288:
            case 11060:
                configs.add(new FarmingConfig(0, 32, false, false, PatchType.HOPS.ordinal(), 0, 0));
                break;
            case 12596:
            case 11570:
            case 10290:
            case 10300:
                configs.add(new FarmingConfig(0, 32, false, false, PatchType.BUSH.ordinal(), 0, 0));
                break;
            case 12594:
            case 12854:
            case 12084:
            case 11573:
            case 9525:
                configs.add(new FarmingConfig(0, 32, false, false, PatchType.TREE.ordinal(), 0, 0));
                break;
            case 9782:
            case 11317:
            case 9777:
            case 11058:
            case 9265:
                configs.add(new FarmingConfig(0, 32, false, false, PatchType.FRUIT_TREE.ordinal(), 0, 0));
                break;
        }
        return configs;
    }

    public int getBlackjackGamesPlayed() {
        return blackjackGamesPlayed;
    }

    public int getBlackjackGamesWon() {
        return blackjackGamesWon;
    }

    public long getBlackjackTotalWagers() {
        return blackjackTotalWagers;
    }

    public void setBlackjackGamesPlayed(int blackjackGamesPlayed) {
        this.blackjackGamesPlayed = blackjackGamesPlayed;
    }

    public void setBlackjackGamesWon(int blackjackGamesWon) {
        this.blackjackGamesWon = blackjackGamesWon;
    }

    public void setBlackjackTotalWagers(long blackjackTotalWagers) {
        this.blackjackTotalWagers = blackjackTotalWagers;
    }

    public int getPortableBankUses() {
        return portableBankUses;
    }

    public int getPortableBankUsesAvailable() {
        return portableBankUsesAvailable;
    }

    public void setPortableBankUses(int portableBankUses) {
        this.portableBankUses = portableBankUses;
    }

    public void setPortableBankUsesAvailable(int portableBankUsesAvailable) {
        this.portableBankUsesAvailable = portableBankUsesAvailable;
    }

    public int getBottomlessCompostBucketCharges() {
        return bottomlessCompostBucketCharges;
    }

    public int getBottomlessCompostBucketType() {
        return bottomlessCompostBucketType;
    }

    public boolean isPermanentCompost() {
        return permanentCompost;
    }

    public void setBottomlessCompostBucketCharges(int bottomlessCompostBucketCharges) {
        this.bottomlessCompostBucketCharges = bottomlessCompostBucketCharges;
    }

    public void setBottomlessCompostBucketType(int bottomlessCompostBucketType) {
        this.bottomlessCompostBucketType = bottomlessCompostBucketType;
    }

    public void setPermanentCompost(boolean permanentCompost) {
        this.permanentCompost = permanentCompost;
    }

    public long getMembershipDurationMS() {
        return membershipDurationMS;
    }

    public long getMembershipStartDateMS() {
        return membershipStartDateMS;
    }

    public void setMembershipDurationMS(long membershipDurationMS) {
        this.membershipDurationMS = membershipDurationMS;
    }

    public void setMembershipStartDateMS(long membershipStartDateMS) {
        this.membershipStartDateMS = membershipStartDateMS;
    }

    public int getPlayPassXp() {
        return playPassXp;
    }

    public void setPlayPassXp(int playPassXp) {
        this.playPassXp = playPassXp;
    }

    public int[] getEquipment() {
        if (equipment == null || equipment.length != 10)
            equipment = new int[10];
        return equipment;
    }

    public void setEquipment(int slot, int itemId) {
        this.equipment[slot] = itemId;
    }

    public int[] getEquippedAmount() {
        if (equippedAmount == null || equippedAmount.length != 10)
            equippedAmount = new int[10];
        return equippedAmount;
    }

    public int getLecternHotspot() {
        return lecternHotspot;
    }


    public void setLecternHotspot(int lecternHotspot) {
        this.lecternHotspot = lecternHotspot;
    }

    public void setEquipmentAmount(int slot, int amount) {
        this.equippedAmount[slot] = amount;
    }

    public List<GameItem> getReclaimableItems() {
        if (reclaimableItems == null)
            reclaimableItems = new ArrayList<>();
        return reclaimableItems;
    }

    public List<Long> getCompletedAchievements() {
        if (completedAchievements == null)
            completedAchievements = new ArrayList<>();
        return completedAchievements;
    }

    public boolean isBurnLogs() {
        return burnLogs;
    }

    public int getDailyWoodcuttingTeleports() {
        return dailyWoodcuttingTeleports;
    }

    public void setBurnLogs(boolean burnLogs) {
        this.burnLogs = burnLogs;
    }

    public void setDailyWoodcuttingTeleports(int dailyWoodcuttingTeleports) {
        this.dailyWoodcuttingTeleports = dailyWoodcuttingTeleports;
    }

    public int getDailyEliteWoodcuttingRingTeleports() {
        return dailyEliteWoodcuttingRingTeleports;
    }

    public void setDailyEliteWoodcuttingRingTeleports(int dailyEliteWoodcuttingRingTeleports) {
        this.dailyEliteWoodcuttingRingTeleports = dailyEliteWoodcuttingRingTeleports;
    }

    public long getAdvancedGoldAccumulatorAccumulated() {
        return advancedGoldAccumulatorAccumulated;
    }

    public int getGoldAccumulatorAccumulated() {
        return goldAccumulatorAccumulated;
    }

    public void setAdvancedGoldAccumulatorAccumulated(long advancedGoldAccumulatorAccumulated) {
        this.advancedGoldAccumulatorAccumulated = advancedGoldAccumulatorAccumulated;
    }

    public void setGoldAccumulatorAccumulated(int goldAccumulatorAccumulated) {
        this.goldAccumulatorAccumulated = goldAccumulatorAccumulated;
    }

    public boolean isAdvancedGoldAccumulatorOwned() {
        return advancedGoldAccumulatorOwned;
    }

    public void setAdvancedGoldAccumulatorOwned(boolean advancedGoldAccumulatorOwned) {
        this.advancedGoldAccumulatorOwned = advancedGoldAccumulatorOwned;
    }

    public boolean isAdvancedGoldAccumulatorActive() {
        return advancedGoldAccumulatorActive;
    }

    public boolean isGoldAccumulatorActive() {
        return goldAccumulatorActive;
    }

    public void setAdvancedGoldAccumulatorActive(boolean advancedGoldAccumulatorActive) {
        this.advancedGoldAccumulatorActive = advancedGoldAccumulatorActive;
    }

    public void setGoldAccumulatorActive(boolean goldAccumulatorActive) {
        this.goldAccumulatorActive = goldAccumulatorActive;
    }

    public boolean isMasterGoldAccumulatorActive() {
        return masterGoldAccumulatorActive;
    }

    public long getMasterGoldAccumulatorAccumulated() {
        return masterGoldAccumulatorAccumulated;
    }

    public void setMasterGoldAccumulatorAccumulated(long masterGoldAccumulatorAccumulated) {
        this.masterGoldAccumulatorAccumulated = masterGoldAccumulatorAccumulated;
    }

    public void setMasterGoldAccumulatorActive(boolean masterGoldAccumulatorActive) {
        this.masterGoldAccumulatorActive = masterGoldAccumulatorActive;
    }

    public Map<Integer, Map<Integer, AdjustableInteger>> getHarvestMap() {
        if (harvestMap == null)
            harvestMap = new HashMap<>();
        return harvestMap;
    }

    public int getJourneyId() {
        return journeyId;
    }


    public void setJourneyId(int journeyId) {
        this.journeyId = journeyId;
    }

    public int getJourneyStep() {
        return journeyStep;
    }

    public void setJourneyStep(int journeyStep) {
        this.journeyStep = journeyStep;
    }

    public int getCurrentJourneyStepProgress() {
        return currentJourneyStepProgress;
    }

    public void setCurrentJourneyStepProgress(int currentJourneyStepProgress) {
        this.currentJourneyStepProgress = currentJourneyStepProgress;
    }

    public List<Integer> getCompletedPath() {
        if (completedPath == null)
            completedPath = new ArrayList<>();
        return completedPath;
    }

    public List<Integer> getUnlockedPath() {
        if (unlockedPath == null)
            unlockedPath = new ArrayList<>();
        return unlockedPath;
    }

    public List<GameItem> getIdleBrewingStation() {
        if (idleBrewingStation == null)
            idleBrewingStation = new ArrayList<>();
        return idleBrewingStation;
    }

    public int[] getPoints() {
        if (points == null)
            points = new int[10];
        return points;
    }

    public int[] getIdleBrewingStationUpgrade() {
        if (idleBrewingStationUpgrade == null)
            idleBrewingStationUpgrade = new int[5];
        return idleBrewingStationUpgrade;
    }

    public int[][] getJobTypeCompleted() {
        if (jobTypeCompleted == null)
            jobTypeCompleted = new int[SkillDictionary.Skill.values().length][4];
        return jobTypeCompleted;
    }

    public void addCompletedJobType(int skillId, int difficulty) {

        jobTypeCompleted[skillId][difficulty] = jobTypeCompleted[skillId][difficulty] + 1;
    }

    public int getJobsAbandoned() {
        return jobsAbandoned;
    }

    public int getJobsCompleted() {
        return jobsCompleted;
    }

    public int getJobStreak() {
        return jobStreak;
    }

    public long getActiveJob() {
        return activeJob;
    }

    public void setActiveJob(long activeJob) {
        this.activeJob = activeJob;
    }

    public float getJobScore() {
        return jobScore;
    }

    public void setJobsAbandoned(int jobsAbandoned) {
        this.jobsAbandoned = jobsAbandoned;
    }

    public void setJobsCompleted(int jobsCompleted) {
        this.jobsCompleted = jobsCompleted;
    }

    public void setJobScore(float jobScore) {
        this.jobScore = jobScore;
    }

    public void setJobStreak(int jobStreak) {
        this.jobStreak = jobStreak;
    }

    public int getIdleBrewId() {
        return idleBrewId;
    }

    public void setIdleBrewId(int idleBrewId) {
        this.idleBrewId = idleBrewId;
    }

    public int getIdleBrewedXp() {
        return idleBrewedXp;
    }

    public void setIdleBrewedXp(int idleBrewedXp) {
        this.idleBrewedXp = idleBrewedXp;
    }

    @Override
    public String toString() {
        return new PlayerSaveDataSerializer().serialize(this);
    }

    public PlayerSaveData(long playerId) {
        this.playerId = playerId;
        this.magicFind = new AdjustableDouble(0.0D);
        this.skillAnimationOverrideMap = new HashMap<>();
        this.instantTeleportCharges = new AdjustableInteger(3);
        this.heraldrySpace = 15450;
        this.crystalBallSpace = 15422;
        this.jewelleryBoxSpace = 29142;
        this.poolSpace = 29122;
        this.telescopeSpace = 15424;
        this.lecternSpace = 15420;
        this.altarSpace = 15270;
        this.portalSpace = 15406;
        this.spellSpace = 29140;
        this.exchangeSlots = 3;
        this.claimedRewardCodes = new ArrayList<>();
        this.bonusXp = new HashMap<>();
        this.bonusXpTimers = new HashMap<>();
        this.teleportCharges = new AdjustableInteger(0);
        this.claimedPassLevel = new boolean[50];
        this.farmingConfigMap = new HashMap<>();
        this.equipment = new int[10];
        this.equippedAmount = new int[10];
        this.reclaimableItems = new ArrayList<>();
        this.harvestMap = new HashMap<>();
        this.completedAchievements = new ArrayList<>();
        this.competedJourney = new ArrayList<>();
        this.claimedJourneyStep = new ArrayList<>();
        this.completedPath = new ArrayList<>();
        this.unlockedPath = new ArrayList<>();
        this.journeyId = 1;
        this.points = new int[10];
        this.jobTypeCompleted = new int[SkillDictionary.Skill.values().length][4];
        this.idleBrewingStation = new ArrayList<>();
        this.idleBrewingStationUpgrade = new int[5];
        Arrays.stream(SkillDictionary.Skill.values()).forEach(skill -> bonusXp.put(skill.getId(), new AdjustableInteger(0)));
        Arrays.stream(SkillDictionary.Skill.values()).forEach(skill -> bonusXpTimers.put(skill.getId(), 0L));
    }

    private final long playerId;
    private final AdjustableDouble magicFind;
    private AdjustableInteger instantTeleportCharges, teleportCharges;
    private Map<Integer, Integer> skillAnimationOverrideMap;
    private Map<Integer, AdjustableInteger> bonusXp;
    private Map<Integer, Long> bonusXpTimers;
    private long joinTimestamp;
    private long logoutTimestamp;
    private long loginTimestamp;
    private long lastHomeTeleportTimestamp;
    private boolean homeUnlocked, initiativePackageClaimed, dailyAvailable;
    private int heraldrySpace, crystalBallSpace, jewelleryBoxSpace, poolSpace, telescopeSpace, lecternSpace, altarSpace, portalSpace, spellSpace, exchangeSlots;
    private List<Long> claimedRewardCodes;

    /**
     * Portable Bank Chest
     */
    private int portableBankUsesAvailable;
    private int portableBankUses;

    /**
     * blackjack
     */
    private int blackjackGamesPlayed;
    private long blackjackTotalWagers;
    private int blackjackGamesWon;

    /**
     * farming
     */
    private Map<Integer,List<FarmingConfig>> farmingConfigMap;
    private int bottomlessCompostBucketCharges;
    private int bottomlessCompostBucketType;
    private boolean permanentCompost;
    private Map<Integer,Map<Integer,AdjustableInteger>> harvestMap;

    /**
     * membership
     */
    private long membershipStartDateMS;
    private long membershipDurationMS;

    /**
     * play pass
     */
    private int playPassXp;
    private boolean[] claimedPassLevel;

    /**
     * equipment
     */
    private int[] equipment;
    private int[] equippedAmount;

    /**
     * hotspots
     */
    private int lecternHotspot;

    private List<GameItem> reclaimableItems;


    /**
     * skill rings
     */
    private boolean burnLogs;
    private int dailyWoodcuttingTeleports,dailyEliteWoodcuttingRingTeleports;

    /**
     * items
     */
    private int goldAccumulatorAccumulated;
    private long advancedGoldAccumulatorAccumulated,masterGoldAccumulatorAccumulated;
    private boolean advancedGoldAccumulatorOwned, goldAccumulatorActive,advancedGoldAccumulatorActive, masterGoldAccumulatorActive;


    /**
     * achievements
     */

    private List<Long> completedAchievements;

    /**
     * Journeys
     */

    private int journeyId;
    private int journeyStep;
    private int currentJourneyStepProgress;
    private List<Long> claimedJourneyStep;
    private List<Integer> competedJourney,completedPath,unlockedPath;


    /**
     * points
     */

    private int[] points;


    /**
     * jobs
     */
    private long activeJob;
    private int jobsCompleted;
    private int jobsAbandoned;
    private int jobStreak;
    private float jobScore;
    private int[][] jobTypeCompleted;

    /**
     * idle stations
     */
    private List<GameItem> idleBrewingStation;
    private int idleBrewId;
    private int[] idleBrewingStationUpgrade;
    private int idleBrewedXp;

    private double amountDonatedUSD;
    private int timesDonated;
}
