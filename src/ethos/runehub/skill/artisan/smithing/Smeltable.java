package ethos.runehub.skill.artisan.smithing;

public enum Smeltable {

    BRONZE(436,438,1,1,2349,1,7,1000,1000,1),
    BLURITE(668,-1,1,0,9467,8,8,1000,1000,1),
    IRON(440,-1,1,0,2351,15,12,50,100,1),
    SILVER(442,-1,1,0,2355,20,14,1000,1000,1),
    ELEMENTAL(2892,453,1,4,2893,20,8,1000,1000,1),
    STEEL(440,453,1,2,2353,30,18,1000,1000,1),
    GOLD(444,-1,1,0,2357,40,23,1000,1000,1),
    LOVAKITE(13356,453,1,2,13354,45,20,1000,1000,1),
    MITHRIL(447,453,1,4,2359,50,30,1000,1000,1),
    ADAMANTITE(449,453,1,6,2361,70,38,1000,1000,1),
    RUNITE(451,453,1,8,2363,85,50,1000,1000,1);

    Smeltable(int primaryOre, int secondaryOre, int primaryOreAmount, int secondaryOreAmount, int productId, int levelRequired, int xpPerAction, int lowRoll, int highRoll, int productAmount) {
        this.primaryOre = primaryOre;
        this.secondaryOre = secondaryOre;
        this.primaryOreAmount = primaryOreAmount;
        this.secondaryOreAmount = secondaryOreAmount;
        this.productId = productId;
        this.levelRequired = levelRequired;
        this.xpPerAction = xpPerAction;
        this.lowRoll = lowRoll;
        this.highRoll = highRoll;
        this.productAmount = productAmount;
    }

    public int getLevelRequired() {
        return levelRequired;
    }

    public int getHighRoll() {
        return highRoll;
    }

    public int getLowRoll() {
        return lowRoll;
    }

    public int getPrimaryOre() {
        return primaryOre;
    }

    public int getPrimaryOreAmount() {
        return primaryOreAmount;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public int getProductId() {
        return productId;
    }

    public int getSecondaryOre() {
        return secondaryOre;
    }

    public int getSecondaryOreAmount() {
        return secondaryOreAmount;
    }

    public int getXpPerAction() {
        return xpPerAction;
    }

    private final int primaryOre, secondaryOre, primaryOreAmount, secondaryOreAmount, productId,
            levelRequired, xpPerAction, lowRoll, highRoll, productAmount;
}
