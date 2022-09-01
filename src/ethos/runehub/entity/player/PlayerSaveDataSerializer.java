package ethos.runehub.entity.player;


import org.runehub.api.io.load.JsonSerializer;

public class PlayerSaveDataSerializer extends JsonSerializer<PlayerSaveData> {

    public PlayerSaveDataSerializer() {
        super(PlayerSaveData.class);
    }
}
