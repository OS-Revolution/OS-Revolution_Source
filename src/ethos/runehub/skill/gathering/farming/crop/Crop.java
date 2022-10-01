package ethos.runehub.skill.gathering.farming.crop;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "crop")
public class Crop {

    public int getId() {
        return id;
    }

    public int getGrowthCycles() {
        return growthCycles;
    }

    public int getCropId() {
        return cropId;
    }

    public int getHarvestXp() {
        return harvestXp;
    }

    public int getPlantXp() {
        return plantXp;
    }

    public int getSeedId() {
        return id;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }


    public int getBaseHarvestAmount() {
        return baseHarvestAmount;
    }

    public int getSeedAmount() {
        return seedAmount;
    }

    public int getMinDiseaseRoll() {
        return minDiseaseRoll;
    }

    public boolean isRegrow() {
        return regrow;
    }

    public CropPayment getPayment() {
        return payment;
    }

    public int getChildIndex() {
        return childIndex;
    }

    public int getPatchTypeId() {
        return patchTypeId;
    }

    public Crop(int id, int growthCycles, int cropId,
                int plantXp, int harvestXp, int levelRequirement,
                int seedAmount, int baseHarvestAmount,int minDiseaseRoll,
                boolean regrow, CropPayment payment, int patchTypeId, int childIndex) {
        this.id = id;
        this.growthCycles = growthCycles;
        this.cropId = cropId;
        this.plantXp = plantXp;
        this.harvestXp = harvestXp;
        this.levelRequirement = levelRequirement;
        this.seedAmount = seedAmount;
        this.baseHarvestAmount = baseHarvestAmount;
        this.minDiseaseRoll = minDiseaseRoll;
        this.regrow = regrow;
        this.payment = payment;
        this.patchTypeId = patchTypeId;
        this.childIndex = childIndex;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int id;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int growthCycles;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int cropId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int plantXp;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int harvestXp;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int levelRequirement;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int seedAmount;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int baseHarvestAmount;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int minDiseaseRoll;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean regrow;
    @StoredValue(type = SqlDataType.JSON)
    private final CropPayment payment;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int patchTypeId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int childIndex;
}
