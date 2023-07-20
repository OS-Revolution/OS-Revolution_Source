package ethos.runehub.content.upgrading;

public class UpgradeRule {

    public int getSourceItemId() {
        return sourceItemId;
    }

    public int getSourceItemAmount() {
        return sourceItemAmount;
    }

    public int getUpgradedItemId() {
        return upgradedItemId;
    }

    public int getUpgradedItemAmount() {
        return upgradedItemAmount;
    }

    public int getUpgradePointCost() {
        return upgradePointCost;
    }

    public boolean isMembers() {
        return members;
    }

    public float getBaseSuccessChance() {
        return baseSuccessChance;
    }

    public float getBaseConsumeChance() {
        return baseConsumeChance;
    }

    public float getBaseUpgradeChanceIncrease() {
        return baseUpgradeChanceIncrease;
    }

    public float getBaseUpgradeCostIncrease() {
        return baseUpgradeCostIncrease;
    }

    public UpgradeRule(int sourceItemId, int sourceItemAmount, int upgradedItemId, int upgradedItemAmount, int upgradePointCost, boolean members, float baseSuccessChance, float baseConsumeChance, float baseUpgradeChanceIncrease, float baseUpgradeCostIncrease) {
        this.sourceItemId = sourceItemId;
        this.sourceItemAmount = sourceItemAmount;
        this.upgradedItemId = upgradedItemId;
        this.upgradedItemAmount = upgradedItemAmount;
        this.upgradePointCost = upgradePointCost;
        this.members = members;
        this.baseSuccessChance = baseSuccessChance;
        this.baseConsumeChance = baseConsumeChance;
        this.baseUpgradeChanceIncrease = baseUpgradeChanceIncrease;
        this.baseUpgradeCostIncrease = baseUpgradeCostIncrease;
    }

    //The itemId being upgraded
    private final int sourceItemId;
    //the minimum amount of the sourceId required to do an upgrade
    private final int sourceItemAmount;
    //The upgraded itemId
    private final int upgradedItemId;
    //The amount of upgradedItemId received
    private final int upgradedItemAmount;
    //The amount of points it costs per upgrade attempt
    private final int upgradePointCost;
    //True if the upgrade is members only
    private final boolean members;
    //The base chance of successfully upgrading
    private final float baseSuccessChance;
    //The base chance of failing to upgrade and the sourceId being consumed
    private final float baseConsumeChance;
    //The base amount to increase the success chance by per failed attempt
    private final float baseUpgradeChanceIncrease;
    //The base amount to increase the upgradePointCost by per failed attempt
    private final float baseUpgradeCostIncrease;

}
