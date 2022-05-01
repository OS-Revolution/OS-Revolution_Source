package ethos.runehub.entity.mob;

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
    public HostileMobContext load(Integer key) {
        if (key != -1) {
            HostileMobContext context = HostileMobContextDAO.getInstance().read(key);
            if (context == null) {
                try {
                    context = new MobContextJsonSerializer().deserialize(
                            Objects.requireNonNull(OSRSBoxClient.getItems(new GETRequest.GETRequestBuilder(OSRSBoxClient.MOB_ENDPOINT)
                                    .setSearchTerm(String.valueOf(key))
                                    .build())).toString()
                    ).getContexts().get(0);
                } catch (IOException e) {
                    Logger.getGlobal().severe("No Item Found: " + key);
                }
            }
            return context;
        }
        return null;
    }
}
