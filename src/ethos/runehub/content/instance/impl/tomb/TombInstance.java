package ethos.runehub.content.instance.impl.tomb;

import ethos.model.players.Player;
import ethos.runehub.content.instance.impl.TimedInstance;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

public class TombInstance extends TimedInstance {

    public static final Rectangle AREA =   new Rectangle(
            new Point(3200, 9280),
            new Point(3262, 9335)
    );

    public String getPassword() {
        return password;
    }

    public boolean isPasswordFound() {
        return passwordFound;
    }

    public void setPasswordFound(boolean passwordFound) {
        this.passwordFound = passwordFound;
    }

    public TombInstance(int id, Player owner, long durationMS, long instanceStartTimestamp, int floorId, String password) {
        super(id, owner, AREA, durationMS, instanceStartTimestamp, floorId);
        this.password = password;
    }

    private boolean passwordFound;
    private final String password;
}
