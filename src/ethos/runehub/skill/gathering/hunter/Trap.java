package ethos.runehub.skill.gathering.hunter;

public class Trap {

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

    public Trap(int id, int x, int y, int z) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private final int id;
    private final int x;
    private final int y;
    private final int z;
}
