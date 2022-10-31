package ethos.runehub.skill.support.sailing.voyage;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "voyages")
public class Voyage {

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

    public int getDistance() {
        return distance;
    }

    public int getIsland() {
        return island;
    }

    public int getRegion() {
        return region;
    }

    public boolean isAdventure() {
        return adventure;
    }

    public boolean isStory() {
        return story;
    }

    public VoyageContext getContext() {
        return context;
    }

    public Voyage(int id, String name, int seafaring, int morale, int combat, int distance, int region, int island, boolean story, boolean adventure, VoyageContext context) {
        this.id = id;
        this.name = name;
        this.seafaring = seafaring;
        this.morale = morale;
        this.combat = combat;
        this.distance = distance;
        this.island = island;
        this.region = region;
        this.story = story;
        this.adventure = adventure;
        this.context = context;
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
    private final int distance;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int region;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int island;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean story;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean adventure;
    @StoredValue(type = SqlDataType.JSON)
    private final VoyageContext context;
}