package ethos.runehub.world;


import org.runehub.api.io.load.JsonSerializer;

public class WorldSettingsSerializer extends JsonSerializer<WorldSettings> {

    public WorldSettingsSerializer() {
        super(WorldSettings.class);
    }
}
