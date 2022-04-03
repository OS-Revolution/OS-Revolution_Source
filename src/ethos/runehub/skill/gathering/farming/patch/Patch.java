package ethos.runehub.skill.gathering.farming.patch;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
import org.runehub.api.io.load.Serializer;

import java.io.Serializable;
import java.util.ArrayList;

@StoredObject(tableName = "patches")
public class Patch implements Serializable {


    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int patchId;
    @StoredValue(type = SqlDataType.LONGTEXT, serializer = Serializer.JSON)
    private final ArrayList<PlayerPatchData> patchData;

//    private final PropertyChangeSupport propertyChangeSupport;

    public Patch(Integer patchId, ArrayList<PlayerPatchData> patchData) {
        this.patchId = patchId;
        this.patchData = patchData;
    }

    public void update() {
        patchData.forEach(playerPatchData -> {
            System.out.println("Updating Patch Data for: " + playerPatchData.getOwnerId());
            playerPatchData.update();
        });
//        PatchDatabase.getInstance().update(this);
    }

    public int getPatchId() {
        return patchId;
    }

    public PlayerPatchData getPlayerPatchData(long playerId) {
        return patchData.stream()
                .filter(playerPatchData -> playerPatchData.getOwnerId() == playerId)
                .findAny()
                .orElse(null);
    }

    public ArrayList<PlayerPatchData> getPatchData() {
        return patchData;
    }
}
