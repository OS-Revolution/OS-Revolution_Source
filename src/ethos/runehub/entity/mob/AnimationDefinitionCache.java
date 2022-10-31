package ethos.runehub.entity.mob;

import org.runehub.api.io.load.LazyLoader;

public class AnimationDefinitionCache extends LazyLoader<Integer, AnimationDefinition> {

    private static AnimationDefinitionCache instance = null;

    public static AnimationDefinitionCache getInstance() {
        if (instance == null)
            instance = new AnimationDefinitionCache();
        return instance;
    }

    private AnimationDefinitionCache() {
        super(AnimationDefinitionDAO.getInstance());
    }
}
