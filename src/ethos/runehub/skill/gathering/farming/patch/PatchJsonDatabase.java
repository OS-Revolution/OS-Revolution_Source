package ethos.runehub.skill.gathering.farming.patch;

import org.runehub.api.io.load.JsonSerializer;
import org.runehub.api.model.skill.farming.patch.Patch;

public class PatchJsonDatabase extends JsonSerializer<org.runehub.api.model.skill.farming.patch.Patch> {
    public PatchJsonDatabase(Class<Patch> type) {
        super(type);
    }
}
