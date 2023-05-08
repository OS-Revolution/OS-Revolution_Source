package ethos.runehub.world;

import org.runehub.api.util.math.geometry.Point;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Chunk {

    // The size of the chunk in tiles
    private static final int SIZE = 64;

    public void addProp(Prop prop) {
        int x = this.getStartingPoint().getX() + prop.getX();
        int y = this.getStartingPoint().getY() + prop.getY();
        System.out.println("Adding Prop: " + prop.getId()  + " X: " + x + " Y: " + y);
        props[prop.getZ()][prop.getX()][prop.getY()] = prop;
    }

    public void removeProp(int x, int y, int z) {
        props[z][x][y] = null;
    }

    public Prop getProp(int x, int y, int z) {
        return props[z][x][y];
    }

    public List<Prop> getAllProps() {
        List<Prop> result = new ArrayList<>();
        for (int z = 0; z < SIZE; z++) {
            for (int x = 0; x < SIZE; x++) {
                for (int y = 0; y < SIZE; y++) {
                    if (props[z][x][y] != null) {
                        result.add(props[z][x][y]);
                    }
                }
            }
        }
        return result;
    }

    public List<Prop> getPropsOfType(int type) {
        List<Prop> result = new ArrayList<>();
        for (int z = 0; z < SIZE; z++) {
            for (int x = 0; x < SIZE; x++) {
                for (int y = 0; y < SIZE; y++) {
                    if (props[z][x][y] != null && props[z][x][y].getType() == type) {
                        result.add(props[z][x][y]);
                    }
                }
            }
        }
        return result;
    }

    // Set the clipping state of a tile
    public void setTileClipped(int x, int y, int z, boolean clipped) {
        int index = (z * SIZE * SIZE) + ((y) * SIZE) + (x);
        clippedTiles.set(index, clipped);
    }

    // Check if a tile is clipped
    public boolean isTileClipped(int x, int y, int z) {
        Point point = this.getCoordinatesAsLocal(x,y);
        int index = (z * SIZE * SIZE) + (point.getY() * SIZE) + point.getX();
        return clippedTiles.get(index);
    }

    public Point getStartingPoint() {
        int absX = (id >> 8) * 64;
        int absY = (id & 0xff) * 64;
        return new Point(absX, absY);
    }

    public Point getCoordinatesAsLocal(int x, int y) {
        int localX = (x >> 6 & 0x3f);
        int localY = (y & 0x3f);
        return new Point(localX, localY);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Chunk: " + id
                + "\nX: " + this.getStartingPoint().getX()
                + "\nY: " + this.getStartingPoint().getY()
                + "\nTotal Props: " + this.getAllProps().size();
    }

    public Chunk(int id) {
        this.id = id;
    }

    private final int id;
    // The bitset representing the clipped tiles
    private BitSet clippedTiles = new BitSet(SIZE * SIZE * 4); // 4 is the maximum height
    private Prop[][][] props = new Prop[SIZE][SIZE][SIZE];
}
