package ethos.runehub.skill.gathering.farming.crops;

import java.io.Serializable;

public class Crop implements Serializable {

    private int objectId;
    private final int x;
    private final int y;

    public Crop(int objectId, int x, int y) {
        this.objectId = objectId;
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }
}
