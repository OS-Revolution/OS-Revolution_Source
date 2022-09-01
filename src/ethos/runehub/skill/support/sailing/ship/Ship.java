package ethos.runehub.skill.support.sailing.ship;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "ships")
public class Ship {

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCombat() {
        return combat;
    }

    public int getMorale() {
        return morale;
    }

    public int getSeafaring() {
        return seafaring;
    }

    public int getSpeed() {
        return speed;
    }

    public Ship(int id, String name, int seafaring, int morale, int combat, int speed) {
        this.id = id;
        this.name = name;
        this.seafaring = seafaring;
        this.morale = morale;
        this.combat = combat;
        this.speed = speed;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int id;
    @StoredValue(type = SqlDataType.TEXT)
    private final String name;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int seafaring;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int morale;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int combat;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int speed;
}
