package ethos.runehub;

import org.rhd.api.io.json.JsonSerializer;

public class WorldSettingsSerializer extends JsonSerializer<WorldSettings> {

    public WorldSettingsSerializer() {
        super(WorldSettings.class);
    }
}
