package ethos.runehub.entity.player;

import org.runehub.api.io.load.LazyLoader;

public class IdleGatheringContextLoader extends LazyLoader<Long, PlayerIdleGatheringContext> {

    private static IdleGatheringContextLoader instance = null;

    public static IdleGatheringContextLoader getInstance() {
        if (instance == null)
            instance = new IdleGatheringContextLoader();
        return instance;
    }

    @Override
    protected PlayerIdleGatheringContext load(Long key) {
        try {
            return super.load(key);
        } catch (NullPointerException e) {
            return new PlayerIdleGatheringContext(key);
        }
    }

    private IdleGatheringContextLoader() {
        super(PlayerIdleGatheringDAO.getInstance());
    }
}
