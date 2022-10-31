package ethos.runehub.entity.player;

import ethos.runehub.skill.gathering.farming.FarmingConfig;
import ethos.runehub.skill.gathering.farming.patch.PatchType;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
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

    public boolean isCrewAvailable() {
        return crewAvailable;
    }

    public int getCrewLevel() {
        return crewLevel;
    }

    public int getVoyagesCompleted() {
        return voyagesCompleted;
    }

    public int getVoyagesFailed() {
        return voyagesFailed;
    }

    public void setCrewAvailable(boolean crewAvailable) {
        this.crewAvailable = crewAvailable;
    }

    public void setCrewLevel(int crewLevel) {
        this.crewLevel = crewLevel;
    }

    public void setVoyagesCompleted(int voyagesCompleted) {
        this.voyagesCompleted = voyagesCompleted;
    }

    public void setVoyagesFailed(int voyagesFailed) {
        this.voyagesFailed = voyagesFailed;
    }

    public int getVoyageSpeedLevel() {
        return voyageSpeedLevel;
    }

    public void setVoyageSpeedLevel(int voyageSpeedLevel) {
        this.voyageSpeedLevel = voyageSpeedLevel;
    }


    public List<Voyage> getAvailableVoyages() {
        if (availableVoyages == null)
            availableVoyages = new ArrayList<>();
        return availableVoyages;
    }

    public void setAvailableVoyages(List<Voyage> availableVoyages) {
        this.availableVoyages = availableVoyages;
    }

    public int getVoyagesRerolled() {
        return voyagesRerolled;
    }

    public int getVoyageRerolls() {
        return voyageRerolls;
    }

    public List<Integer> getIslandsVisited() {
        if (islandsVisited == null)
            islandsVisited = new ArrayList<>();
        return islandsVisited;
    }

    public List<Integer> getRegionsVisited() {
        return regionsVisited;
    }

    public long getLeaguesTravelled() {
        return leaguesTravelled;
    }

    public void setRegionsVisited(List<Integer> regionsVisited) {
        this.regionsVisited = regionsVisited;
    }

    public void setIslandsVisited(List<Integer> islandsVisited) {
        this.islandsVisited = islandsVisited;
    }

    public void setLeaguesTravelled(long leaguesTravelled) {
        this.leaguesTravelled = leaguesTravelled;
    }

    public void setVoyagesRerolled(int voyagesRerolled) {
        this.voyagesRerolled = voyagesRerolled;
    }

    public void setVoyageRerolls(int voyageRerolls) {
        this.voyageRerolls = voyageRerolls;
    }

    public void updateShipSlot(int slot, int index, int value) {
        if(shipSlot == null)
            shipSlot = new int[3][4];
        shipSlot[slot][index] = value;
    }


    public int[][] getShipSlot() {
        if(shipSlot == null)
            shipSlot = new int[3][4];
        return shipSlot;
    }

    public int[][] getVoyageLoot() {
        if(voyageLoot == null)
            voyageLoot = new int[3][3];
        return voyageLoot;
    }

    public void setShipSlot(int[][] shipSlot) {
        this.shipSlot = shipSlot;
    }

    public long[][] getVoyageTimestamp() {
        if(voyageTimestamp == null)
            voyageTimestamp = new long[3][2];
        return voyageTimestamp;
    }

    public List<Integer> getStoryVoyagesCompleted() {
        if (storyVoyagesCompleted == null)
            storyVoyagesCompleted = new ArrayList<>();
        return storyVoyagesCompleted;
    }

    public boolean[] getClaimedPassLevel() {
        if(claimedPassLevel == null)
            claimedPassLevel = new boolean[50];
        return claimedPassLevel;
    }

    public void updateClaimedPassLevel(int index, boolean value) {
        claimedPassLevel[index] = value;
    }

    public Map<Integer,List<FarmingConfig>> farmingConfig() {
        if (farmingConfigMap == null || farmingConfigMap.isEmpty()) {
            farmingConfigMap = new HashMap<>();
            farmingConfigMap.put(14391,configForFarm(14391));
            farmingConfigMap.put(10548,configForFarm(10548));
            farmingConfigMap.put(11062,configForFarm(11062));
        }
        return farmingConfigMap;
    }

    private List<FarmingConfig> configForFarm(int regionId) {
        final List<FarmingConfig> configs = new ArrayList<>();
        switch (regionId) {
            case 14391: //canafis
            case 10548: //ardougne
            case 11062: //catherby
                configs.add(new FarmingConfig(0, 0, false, false, PatchType.ALLOTMENT.ordinal(), 0, 0));
                configs.add(new FarmingConfig(0, 8, false, false, PatchType.ALLOTMENT.ordinal(), 0, 0));
                configs.add(new FarmingConfig(0, 16, false, false, PatchType.FLOWER.ordinal(), 0, 0));
                configs.add(new FarmingConfig(0, 24, false, false, PatchType.HERB.ordinal(), 0, 0));
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

    public int getPreferredRegion() {
        return preferredRegion;
    }

    public void setPreferredRegion(int preferredRegion) {
        this.preferredRegion = preferredRegion;
    }

    public void setVoyageTimestamp(long[][] voyageTimestamp) {
        this.voyageTimestamp = voyageTimestamp;
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

    public void setEquipmentAmount(int slot, int amount) {
        this.equippedAmount[slot] = amount;
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
        this.availableVoyages = new ArrayList<>();
        this.islandsVisited = new ArrayList<>();
        this.regionsVisited = new ArrayList<>();
        this.storyVoyagesCompleted = new ArrayList<>();
        this.shipSlot = new int[3][4];
        this.voyageTimestamp = new long[3][2];
        this.voyageLoot = new int[3][3];
        this.preferredRegion = -1;
        this.claimedPassLevel = new boolean[50];
        this.farmingConfigMap = new HashMap<>();
        this.equipment = new int[10];
        this.equippedAmount = new int[10];
//        farmingConfigMap.put(
//                10548,List.of(
//                        new FarmingConfig(0,0,false,false, PatchType.ALLOTMENT.ordinal(),0,0),
//                        new FarmingConfig(0,8,false,false, PatchType.ALLOTMENT.ordinal(),0,0),
//                        new FarmingConfig(0,16,false,false, PatchType.FLOWER.ordinal(),0,0),
//                        new FarmingConfig(0,24,false,false, PatchType.HERB.ordinal(),0,0)
//                ));
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
     * Sailing Variables
     */
    private int[][] voyageLoot;
    private int[][] shipSlot;
    private long[][] voyageTimestamp;
    private int crewLevel, voyagesCompleted, voyagesFailed, voyageSpeedLevel, voyagesRerolled, voyageRerolls,preferredRegion;
    private boolean crewAvailable;
    private long leaguesTravelled;
    private List<Voyage> availableVoyages;
    private List<Integer> islandsVisited;
    private List<Integer> regionsVisited;
    private List<Integer> storyVoyagesCompleted;

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
}
