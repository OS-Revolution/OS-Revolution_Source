package ethos.runehub.entity.player;

import ethos.runehub.WorldSettings;
import org.rhd.api.io.json.JsonSerializer;

public class PlayerSaveDataSerializer extends JsonSerializer<PlayerSaveData> {

    public PlayerSaveDataSerializer() {
        super(PlayerSaveData.class);
    }
}
