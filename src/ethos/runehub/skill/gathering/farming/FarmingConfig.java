package ethos.runehub.skill.gathering.farming;

import ethos.runehub.skill.gathering.farming.crop.CropCache;
import ethos.runehub.skill.gathering.farming.patch.PatchType;

public class FarmingConfig {

    public int varbit() {
        if (crop != 0)
            return (CropCache.getInstance().read(crop).getChildIndex() + stage + (isWatered() << 6 | isDiseased() << 7) << patch);
        return (stage + (isWatered() << 6 | isDiseased() << 7) << patch);
    }

    public int varbit(int crop) {
        if (crop != 0)
            return (crop + stage + (isWatered() << 6 | isDiseased() << 7) << patch);
        return (stage + (isWatered() << 6 | isDiseased() << 7) << patch);
    }

    public int getPatch() {
        return patch;
    }

    public int isDiseased() {
        return !diseased ? 0 : 1;
    }

    public int isWatered() {
        return !watered ? 0 : 1;
    }

    public int getStage() {
        return stage;
    }

//    public int getStartIndex() {
//        return startIndex;
//    }

    public int getType() {
        return type;
    }

    public int getCrop() {
        return crop;
    }

    public boolean watered() {
        return watered;
    }

    public boolean diseased() {
        return diseased;
    }

    public void setDiseased(boolean diseased) {
        this.diseased = diseased;
    }

    public void incrementCycle() {
        this.stage++;
    }

    public void setWatered(boolean watered) {
        this.watered = watered;
    }

    public void setPatch(int patch) {
        this.patch = patch;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public void setCrop(int crop) {
        this.crop = crop;
    }

//    public void setStartIndex(int startIndex) {
//        this.startIndex = startIndex;
//    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCompost() {
        return compost;
    }

    public void setCompost(int compost) {
        this.compost = compost;
    }

    public FarmingConfig(int stage, int patch, boolean watered, boolean diseased, int type, int crop, int compost) {
//        this.startIndex = startIndex;
        this.stage = stage;
        this.patch = patch;
        this.watered = watered;
        this.diseased = diseased;
        this.type = type;
        this.crop = crop;
        this.compost = compost;
//        System.out.println("Varbit [" + varbit() + "]");
    }

    //    private int startIndex;
    private int stage, patch, type, crop, compost;
    private boolean watered, diseased;
}
