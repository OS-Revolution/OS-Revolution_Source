package ethos.runehub.entity.player;

import ethos.model.players.skills.Skill;
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

//    public int getShipSlotOne() {
//        return shipSlotOne;
//    }
//
//    public int getShipSlotOneIsland() {
//        return shipSlotOneIsland;
//    }
//
//    public int getShipSlotOneRegion() {
//        return shipSlotOneRegion;
//    }
//
//    public int getShipSlotOneVoyage() {
//        return shipSlotOneVoyage;
//    }

//    public int getShipSlotThree() {
//        return shipSlotThree;
//    }
//
//    public int getShipSlotThreeIsland() {
//        return shipSlotThreeIsland;
//    }
//
//    public int getShipSlotThreeRegion() {
//        return shipSlotThreeRegion;
//    }
//
//    public int getShipSlotThreeVoyage() {
//        return shipSlotThreeVoyage;
//    }
//
//    public int getShipSlotTwo() {
//        return shipSlotTwo;
//    }
//
//    public int getShipSlotTwoIsland() {
//        return shipSlotTwoIsland;
//    }
//
//    public int getShipSlotTwoRegion() {
//        return shipSlotTwoRegion;
//    }
//
//    public int getShipSlotTwoVoyage() {
//        return shipSlotTwoVoyage;
//    }

    public List<Integer> getIslandsVisited() {
        return islandsVisited;
    }

    public List<Integer> getRegionsVisited() {
        return regionsVisited;
    }

    public long getLeaguesTravelled() {
        return leaguesTravelled;
    }

//    public long getShipSlotOneEndTimestamp() {
//        return shipSlotOneEndTimestamp;
//    }
//
//    public long getShipSlotOneStartTimestamp() {
//        return shipSlotOneStartTimestamp;
//    }

    public void setRegionsVisited(List<Integer> regionsVisited) {
        this.regionsVisited = regionsVisited;
    }

    public void setIslandsVisited(List<Integer> islandsVisited) {
        this.islandsVisited = islandsVisited;
    }

    public void setLeaguesTravelled(long leaguesTravelled) {
        this.leaguesTravelled = leaguesTravelled;
    }
//
//    public void setShipSlotOne(int shipSlotOne) {
//        this.shipSlotOne = shipSlotOne;
//    }
//
//    public void setShipSlotOneEndTimestamp(long shipSlotOneEndTimestamp) {
//        this.shipSlotOneEndTimestamp = shipSlotOneEndTimestamp;
//    }
//
//    public void setShipSlotOneIsland(int shipSlotOneIsland) {
//        this.shipSlotOneIsland = shipSlotOneIsland;
//    }
//
//    public void setShipSlotOneRegion(int shipSlotOneRegion) {
//        this.shipSlotOneRegion = shipSlotOneRegion;
//    }
//
//    public void setShipSlotOneStartTimestamp(long shipSlotOneStartTimestamp) {
//        this.shipSlotOneStartTimestamp = shipSlotOneStartTimestamp;
//    }
//
//    public void setShipSlotOneVoyage(int shipSlotOneVoyage) {
//        this.shipSlotOneVoyage = shipSlotOneVoyage;
//    }
//
//    public void setShipSlotThree(int shipSlotThree) {
//        this.shipSlotThree = shipSlotThree;
//    }
//
//    public void setShipSlotThreeIsland(int shipSlotThreeIsland) {
//        this.shipSlotThreeIsland = shipSlotThreeIsland;
//    }
//
//    public void setShipSlotThreeRegion(int shipSlotThreeRegion) {
//        this.shipSlotThreeRegion = shipSlotThreeRegion;
//    }
//
//    public void setShipSlotThreeVoyage(int shipSlotThreeVoyage) {
//        this.shipSlotThreeVoyage = shipSlotThreeVoyage;
//    }
//
//    public void setShipSlotTwo(int shipSlotTwo) {
//        this.shipSlotTwo = shipSlotTwo;
//    }
//
//    public void setShipSlotTwoIsland(int shipSlotTwoIsland) {
//        this.shipSlotTwoIsland = shipSlotTwoIsland;
//    }
//
//    public void setShipSlotTwoRegion(int shipSlotTwoRegion) {
//        this.shipSlotTwoRegion = shipSlotTwoRegion;
//    }
//
//    public void setShipSlotTwoVoyage(int shipSlotTwoVoyage) {
//        this.shipSlotTwoVoyage = shipSlotTwoVoyage;
//    }

    public void setVoyagesRerolled(int voyagesRerolled) {
        this.voyagesRerolled = voyagesRerolled;
    }

    public void setVoyageRerolls(int voyageRerolls) {
        this.voyageRerolls = voyageRerolls;
    }

//    public long getShipSlotThreeEndTimestamp() {
//        return shipSlotThreeEndTimestamp;
//    }
//
//    public long getShipSlotThreeStartTimestamp() {
//        return shipSlotThreeStartTimestamp;
//    }
//
//    public long getShipSlotTwoEndTimestamp() {
//        return shipSlotTwoEndTimestamp;
//    }
//
//    public long getShipSlotTwoStartTimestamp() {
//        return shipSlotTwoStartTimestamp;
//    }
//
//    public void setShipSlotThreeEndTimestamp(long shipSlotThreeEndTimestamp) {
//        this.shipSlotThreeEndTimestamp = shipSlotThreeEndTimestamp;
//    }
//
//    public void setShipSlotThreeStartTimestamp(long shipSlotThreeStartTimestamp) {
//        this.shipSlotThreeStartTimestamp = shipSlotThreeStartTimestamp;
//    }
//
//    public void setShipSlotTwoEndTimestamp(long shipSlotTwoEndTimestamp) {
//        this.shipSlotTwoEndTimestamp = shipSlotTwoEndTimestamp;
//    }
//
//    public void setShipSlotTwoStartTimestamp(long shipSlotTwoStartTimestamp) {
//        this.shipSlotTwoStartTimestamp = shipSlotTwoStartTimestamp;
//    }


    public int[][] getShipSlot() {
        if(shipSlot == null)
            shipSlot = new int[3][4];
        return shipSlot;
    }

    public void setShipSlot(int[][] shipSlot) {
        this.shipSlot = shipSlot;
    }

    public long[][] getVoyageTimestamp() {
        if(voyageTimestamp == null)
            voyageTimestamp = new long[3][2];
        return voyageTimestamp;
    }

    public void setVoyageTimestamp(long[][] voyageTimestamp) {
        this.voyageTimestamp = voyageTimestamp;
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
        this.shipSlot = new int[3][4];
        this.voyageTimestamp = new long[3][2];
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
//    private int shipSlotOne, shipSlotTwo, shipSlotThree,
//            shipSlotOneVoyage, shipSlotTwoVoyage, shipSlotThreeVoyage,
//            shipSlotOneRegion, shipSlotTwoRegion, shipSlotThreeRegion,
//            shipSlotOneIsland, shipSlotTwoIsland, shipSlotThreeIsland;
    private int[][] shipSlot;
    private long[][] voyageTimestamp;
    private int crewLevel, voyagesCompleted, voyagesFailed, voyageSpeedLevel, voyagesRerolled, voyageRerolls;
    private boolean crewAvailable;
    //    private long shipSlotOneEndTimestamp, shipSlotOneStartTimestamp,
//    shipSlotTwoEndTimestamp, shipSlotTwoStartTimestamp,
//            shipSlotThreeEndTimestamp, shipSlotThreeStartTimestamp;
    private long leaguesTravelled;
    private List<Voyage> availableVoyages;
    private List<Integer> islandsVisited;
    private List<Integer> regionsVisited;

}
