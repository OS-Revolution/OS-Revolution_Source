package ethos.runehub.content.rift;

import ethos.util.Misc;

public enum RiftDifficulty {

    NORMAL,HARD,MASTER,TORMENT,TORMENT_II,TORMENT_III,TORMENT_IV,TORMENT_V;

    @Override
    public String toString() {
        return Misc.capitalize(name().split("_")[0].toLowerCase()) + " " + (name().split("_").length > 1 ? name().split("_")[1].replaceAll("_"," ") : "");
    }
}
