package ethos.runehub.entity.mob.hostile;

import ethos.runehub.entity.mob.MobContextJsonSerializer;
import org.runehub.api.io.load.LazyLoader;
import org.runehub.api.osrsb.OSRSBoxClient;
import org.runehub.api.rest.GETRequest;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class HostileMobIdContextLoader extends LazyLoader<Integer, HostileMobContext> {

    private static HostileMobIdContextLoader instance = null;

    public static HostileMobIdContextLoader getInstance() {
        if (instance == null)
            instance = new HostileMobIdContextLoader();
        return instance;
    }

    private HostileMobIdContextLoader() {
        super(HostileMobContextDAO.getInstance());
    }

    @Override
    public HostileMobContext read(Integer key) {
        HostileMobContext value = this.getMap().get(key);
        if (value == null) {
            value = this.load(key);
        }
        if (value != null) {
            this.getMap().put(key, value);
        }
        return value;
    }

    @Override
    public HostileMobContext load(Integer key) {
        if (key != -1) {
            HostileMobContext context = HostileMobContextDAO.getInstance().read(key);
            if (context == null) {
                Logger.getGlobal().severe("No Value Found: " + key);
            }
            return context;
        }
        return null;
    }
}
