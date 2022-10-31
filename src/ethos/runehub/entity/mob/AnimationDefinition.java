package ethos.runehub.entity.mob;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "animations")
public class AnimationDefinition {

    public int getWalkAnimation() {
        return animation[0];
    }

    public int getAttackAnimation() {
        return animation[1];
    }

    public int getBlockAnimation() {
        return animation[2];
    }

    public int getDeathAnimation() {
        return animation[3];
    }

    public int[] getAnimation() {
        return animation;
    }

    public int getMobId() {
        return mobId;
    }

    public AnimationDefinition(int mobId, int[] animation) {
        this.mobId = mobId;
        this.animation = animation;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int mobId;
    @StoredValue(type = SqlDataType.JSON)
    private final int[] animation;
}
