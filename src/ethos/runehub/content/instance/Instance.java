package ethos.runehub.content.instance;

import ethos.model.players.Player;
import org.runehub.api.util.math.geometry.impl.Rectangle;

public class Instance {

    public int getId() {
        return id;
    }

    public Player getOwner() {
        return owner;
    }

    public Rectangle getArea() {
        return area;
    }

    public int getFloodId() {
        return floodId;
    }

    public Instance(int id, Player owner, Rectangle area, int floodId) {
        this.id = id;
        this.owner = owner;
        this.area = area;
        this.floodId = floodId;
    }

    private final int id;
    private final Player owner;
    private final Rectangle area;
    private final int floodId;
}
