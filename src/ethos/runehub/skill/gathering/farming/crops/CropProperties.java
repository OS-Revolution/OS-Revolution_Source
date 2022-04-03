package ethos.runehub.skill.gathering.farming.crops;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
import org.runehub.api.model.entity.item.GameItem;
import java.util.ArrayList;

@StoredObject(tableName = "crops")
public class CropProperties {

    public int getId() {
        return id;
    }

    public int getFarmingCycles() {
        return farmingCycles;
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
        return seedId;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public ArrayList<GameItem> getFarmerProtection() {
        return farmerProtection;
    }

    public ArrayList<Integer> getFlowerProtection() {
        return flowerProtection;
    }

    public ArrayList<GrowthStage> getGrowthStages() {
        return growthStages;
    }

    public int getBaseHarvestAmount() {
        return baseHarvestAmount;
    }

    public int getSeedAmount() {
        return seedAmount;
    }

    public float getBaseDiseaseChance() {
        return baseDiseaseChance;
    }

    public CropProperties(Integer id, Integer farmingCycles, Integer seedId, Integer cropId,
                          Integer plantXp, Integer harvestXp, Integer levelRequirement,
                          Integer seedAmount, Integer baseHarvestAmount, Float baseDiseaseChance,
                          ArrayList<Integer> flowerProtection, ArrayList<GameItem> farmerProtection,
                          ArrayList<GrowthStage> growthStages) {
        this.id = id;
        this.farmingCycles = farmingCycles;
        this.seedId = seedId;
        this.cropId = cropId;
        this.plantXp = plantXp;
        this.harvestXp = harvestXp;
        this.levelRequirement = levelRequirement;
        this.flowerProtection = flowerProtection;
        this.farmerProtection = farmerProtection;
        this.seedAmount = seedAmount;
        this.growthStages = growthStages;
        this.baseHarvestAmount = baseHarvestAmount;
        this.baseDiseaseChance = baseDiseaseChance;
    }

    public CropProperties(Integer id, Integer farmingCycles, Integer seedId, Integer cropId,
                          Integer plantXp, Integer harvestXp, Integer levelRequirement,
                          Integer seedAmount, Integer baseHarvestAmount, Float baseDiseaseChance,
                          ArrayList<GrowthStage> growthStages) {
        this(id,farmingCycles,seedId,cropId,plantXp,harvestXp,levelRequirement,
                seedAmount,baseHarvestAmount,baseDiseaseChance,new ArrayList<>(),new ArrayList<>(),growthStages);
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int id;
    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.NOT_NULL)
    private final int farmingCycles;
    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.NOT_NULL)
    private final int seedId;
    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.NOT_NULL)
    private final int cropId;
    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.NOT_NULL)
    private final int plantXp;
    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.NOT_NULL)
    private final int harvestXp;
    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.NOT_NULL)
    private final int levelRequirement;
    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.NOT_NULL)
    private final int seedAmount;
    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.NOT_NULL)
    private final int baseHarvestAmount;
    @StoredValue(type = SqlDataType.FLOAT, parameter = QueryParameter.NOT_NULL)
    private final float baseDiseaseChance;
    @StoredValue(type = SqlDataType.BLOB)
    private final ArrayList<Integer> flowerProtection;
    @StoredValue(type = SqlDataType.BLOB)
    private final ArrayList<GameItem> farmerProtection;
    @StoredValue(type = SqlDataType.BLOB)
    private final ArrayList<GrowthStage> growthStages;
}
