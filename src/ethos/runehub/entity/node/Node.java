package ethos.runehub.entity.node;

public class Node {

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

    public int getFace() {
        return face;
    }

    public Node(int id, int x, int y, int z, int face, int type) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.face = face;
        this.type = type;
    }

    private final int id, x, y,z,face,type;
}
