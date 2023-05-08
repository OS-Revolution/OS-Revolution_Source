package ethos.runehub.skill.artisan.cooking.food;

import org.runehub.api.io.data.StoredObject;

@StoredObject(tableName = "cookable")
public class Cookable {

    public int getItemId() {
        return itemId;
    }

    public int getXp() {
        return xp;
    }

    public int getLevel() {
        return level;
    }

    public int getLow() {
        return low;
    }

    public int getHigh() {
        return high;
    }

    public boolean isMembers() {
        return members;
    }

    public int getBurntItemId() {
        return burntItemId;
    }

    public int getCookedItemId() {
        return cookedItemId;
    }

    public int getTicks() {
        return ticks;
    }

    public Cookable(int itemId, int cookedItemId, int burntItemId, int xp, int level, int low, int high, int ticks, boolean members) {
        this.itemId = itemId;
        this.cookedItemId = cookedItemId;
        this.burntItemId = burntItemId;
        this.xp = xp;
        this.level = level;
        this.low = low;
        this.high = high;
        this.ticks = ticks;
        this.members = members;
    }

    private final int itemId;
    private final int cookedItemId;
    private final int burntItemId;
    private final int xp;
    private final int level;
    private final int low;
    private final int high;
    private final int ticks;
    private final boolean members;
}
