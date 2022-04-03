package ethos.runehub.skill.gathering.farming.patch;

import ethos.runehub.skill.gathering.farming.crops.CropProperties;
import ethos.runehub.skill.gathering.farming.crops.CropPropertiesDatabase;
import ethos.runehub.skill.gathering.farming.crops.GrowthStage;

import java.io.Serializable;
import java.util.Random;

public class PlayerPatchData implements Serializable {

    private final int patchId;
    private final long ownerId;
    private long plantTime;
    private int objectId;
    private int state;
    private int seedId;
    private int treatedLevel;
    private boolean watered;

    public PlayerPatchData(Integer patchId,Long ownerId, Long plantTime, Integer objectId,
                 Integer state, Integer seedId, Integer treatedLevel, Boolean watered) {
        this.ownerId = ownerId;
        this.patchId = patchId;
        this.plantTime = plantTime;
        this.objectId = objectId;
        this.state = state;
        this.seedId = seedId;
        this.treatedLevel = treatedLevel;
        this.watered = watered;
    }

    public PlayerPatchData(Long ownerId, Integer patchId) {
        this(patchId,ownerId, 0L, 0, 0, -1, 0, false);
    }

    private float getTreatedLevelDiseaseResistance() {
        switch (treatedLevel) {
            case 0:
                return 0;
            case 1:
                return 0.5f;
            case 2:
                return 0.8f;
            case 3:
                return 0.9f;
            default:
                return 1.0f;
        }
    }

    private boolean catchDisease(CropProperties cropProperties) {
        float diseaseRoll = new Random().nextFloat();
        float diseaseResistance = ((cropProperties.getBaseDiseaseChance() - this.getTreatedLevelDiseaseResistance()) - (watered ? 0.2f : 0));
        System.out.println("Disease Roll: " + diseaseRoll);
        System.out.println("Disease Res: " + diseaseResistance);
        System.out.println("Base: " + cropProperties.getBaseDiseaseChance());
        System.out.println("Treated: " + this.getTreatedLevelDiseaseResistance());
        return (diseaseRoll <= diseaseResistance);
    }

    private void updateState(CropProperties cropProperties) {
        if (state == State.HEALTHY.ordinal() || state == State.WATERED.ordinal()) {
            if (catchDisease(cropProperties)) {
                System.out.println("Diseased");
                this.setState(State.DISEASED);
            }
        } else if (state == State.DISEASED.ordinal()) {
            System.out.println("Dead");
            this.setState(State.DEAD);
        }
    }

    private void updateGrowthStage() {
        CropProperties cropProperties = null;
        System.out.println("Seed ID: " + seedId);
        System.out.println("Empty: " + isEmpty());
        if (!isEmpty()) {
            cropProperties = CropPropertiesDatabase.getInstance().read(seedId);
        } else {
            cropProperties = CropPropertiesDatabase.getInstance().read(patchId); //TODO MAKE WEED ID
        }
        System.out.println("OBJ ID: " + objectId);
        GrowthStage currentStage = cropProperties.getGrowthStages().stream()
                .filter(growthStage -> growthStage.getHealthyId() == objectId ||
                        growthStage.getWateredId() == objectId ||
                        growthStage.getDiseasedId() == objectId)
                .findAny().orElse(null);
        int growthStageIndex = currentStage != null ? cropProperties.getGrowthStages().indexOf(currentStage) : -1;
        if (currentStage != null && growthStageIndex != 0) {
            this.updateState(cropProperties);
        }
        if (currentStage != null) {
            System.out.println("Current State: " + State.values()[state]);
            System.out.println("Current Growth Index: " + growthStageIndex);
            System.out.println("Total Growth Stages: " + cropProperties.getGrowthStages().size());
            if (state == State.HEALTHY.ordinal()) {
                this.setObjectId(cropProperties.getGrowthStages().get(growthStageIndex + 1).getHealthyId());
            } else if (state == State.DISEASED.ordinal()) {
                this.setObjectId(cropProperties.getGrowthStages().get(growthStageIndex).getDiseasedId());
            } else if (state == State.DEAD.ordinal()) {
                this.setObjectId(cropProperties.getGrowthStages().get(growthStageIndex).getDeadId());
            }
            System.out.println("Updated: " + objectId);
            this.setWatered(false);
        }
    }

    public void update() {
        this.updateGrowthStage();
    }

    public void setWatered(boolean watered) {
        this.watered = watered;
    }

    public void setTreatedLevel(int treatedLevel) {
        this.treatedLevel = treatedLevel;
    }

    public boolean isWatered() {
        return watered;
    }

    public int getTreatedLevel() {
        return treatedLevel;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setPlantTime(long plantTime) {
        this.plantTime = plantTime;
    }

    public int getSeedId() {
        return seedId;
    }

    public int getState() {
        return state;
    }

    public int getObjectId() {
        return objectId;
    }

    public long getPlantTime() {
        return plantTime;
    }

    public boolean isEmpty() {
        return seedId == -1;
    }

    public void setState(State state) {
//        propertyChangeSupport.firePropertyChange("state",State.values()[this.state],state);
        this.state = state.ordinal();

    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public void setSeedId(int seedId) {
        this.seedId = seedId;
    }

    public enum State {
        HEALTHY, WATERED, DISEASED, DEAD
    }
}
