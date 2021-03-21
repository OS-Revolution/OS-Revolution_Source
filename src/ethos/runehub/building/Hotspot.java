package ethos.runehub.building;

public class Hotspot {

    private final int x,y,height;
    private int id;

    public Hotspot(int id,int x, int y, int height) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
