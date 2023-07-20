package ethos.runehub.skill.support.sailing.ship;

import ethos.runehub.entity.item.GameItem;

public interface Shipwright {

    static final int PLAYER_STAT_CAP = 1500;
    static final int PLAYER_WEIGHT_CAP = 5000;
    static final double BASE_UPGRADE = 0.10;
    static final double BASE_UPGRADE_COST_MODIFIER = 0.35;

    void upgrade(int shipSlot);
    int getRegionTier(Ship  ship);
    GameItem[] getMaterials(int stat,int tier);

    default int getSeafaringTier(Ship ship) {
        int seafaring = ship.getSeafaring();
        int initialSeafaring = 5; // Assuming initial seafaring value is 5
        int increments = (int) Math.ceil((PLAYER_STAT_CAP - initialSeafaring) / (BASE_UPGRADE * initialSeafaring));
        return Math.min(increments, Math.max(1, (seafaring - initialSeafaring) / initialSeafaring + 1));
    }

    default int getCombatTier(Ship ship) {
        int value = ship.getCombat();
        int initialValue = 5; // Assuming initial seafaring value is 5
        int increments = (int) Math.ceil((PLAYER_STAT_CAP - initialValue) / (BASE_UPGRADE * initialValue));
        return Math.min(increments, Math.max(1, (value - initialValue) / initialValue + 1));
    }

    default int getMoraleTier(Ship ship) {
        int value = ship.getMorale();
        int initialValue = 5; // Assuming initial seafaring value is 5
        int increments = (int) Math.ceil((PLAYER_STAT_CAP - initialValue) / (BASE_UPGRADE * initialValue));
        return Math.min(increments, Math.max(1, (value - initialValue) / initialValue + 1));
    }

    default int getSpeedTier(Ship ship) {
        int value = ship.getSpeed();
        int initialValue = 3; // Assuming initial seafaring value is 5
        int increments = (int) Math.ceil((PLAYER_STAT_CAP - initialValue) / (BASE_UPGRADE * initialValue));
        return Math.min(increments, Math.max(1, (value - initialValue) / initialValue + 1));
    }

    default int getWeightTier(Ship ship) {
        int value = ship.getWeightCapacity();
        int initialValue = 50; // Assuming initial seafaring value is 5
        int increments = (int) Math.ceil((PLAYER_WEIGHT_CAP - initialValue) / (BASE_UPGRADE * initialValue));
        return Math.min(increments, Math.max(1, (value - initialValue) / initialValue + 1));
    }



}
