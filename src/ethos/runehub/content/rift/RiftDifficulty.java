package ethos.runehub.content.rift;

import ethos.runehub.entity.item.GameItem;
import ethos.util.Misc;

public enum RiftDifficulty {

    NORMAL(new GameItem[]{new GameItem(995,1000)},0.0D,false),
    HARD(new GameItem[]{new GameItem(995,1000)},50.0D,false),
    MASTER(new GameItem[]{new GameItem(995,1000)},100.0D,false),
    TORMENT(new GameItem[]{new GameItem(995,1000)},150.0D,false),
    TORMENT_II(new GameItem[]{new GameItem(995,1000)},200.0D,false),
    TORMENT_III(new GameItem[]{new GameItem(995,1000)},250.0D,false),
    TORMENT_IV(new GameItem[]{new GameItem(995,1000)},300.0D,false),
    TORMENT_V(new GameItem[]{new GameItem(995,1000)},350.0D,false);

    public GameItem[] getEntryFee() {
        return entryFee;
    }

    public double getRewardBonus() {
        return rewardBonus;
    }

    public boolean isMembers() {
        return members;
    }

    RiftDifficulty(GameItem[] entryFee, double rewardBonus, boolean members) {
        this.entryFee = entryFee;
        this.rewardBonus = rewardBonus;
        this.members = members;
    }

    private final GameItem[] entryFee;
    private final double rewardBonus;
    private final boolean members;

    @Override
    public String toString() {
        return Misc.capitalize(name().split("_")[0].toLowerCase()) + " " + (name().split("_").length > 1 ? name().split("_")[1].replaceAll("_"," ") : "");
    }
}
