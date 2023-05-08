package ethos.runehub.world;

public class Prop {

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getType() {
        return type;
    }

    public int getDirection() {
        return direction;
    }

    public Prop(int id, int x, int y, int z, int type, int direction) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
        this.direction = direction;
    }

    private final int id;
    private final int x;
    private final int y;
    private final int z;
    private final int type;
    private final int direction;
}
