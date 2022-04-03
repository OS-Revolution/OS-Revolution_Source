package ethos.runehub.skill.gathering.tool;

import org.runehub.api.io.load.LazyLoader;

public class GatheringToolLoader extends LazyLoader<Integer,GatheringTool> {

    private static GatheringToolLoader instance = null;

    public static GatheringToolLoader getInstance() {
        if (instance == null)
            instance = new GatheringToolLoader();
        return instance;
    }

    private GatheringToolLoader() {
        super(GatheringToolDAO.getInstance());
    }
}
