package ethos.runehub.building;

import org.rhd.api.io.json.JsonSerializer;

public class HotspotSerializer extends JsonSerializer<Hotspot> {

    public HotspotSerializer() {
        super(Hotspot.class);
    }
}
