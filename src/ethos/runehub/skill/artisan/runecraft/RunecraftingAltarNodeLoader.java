package ethos.runehub.skill.artisan.runecraft;

import ethos.runehub.skill.node.impl.artisan.RunecraftingAltarNode;
import org.runehub.api.io.load.LazyLoader;

public class RunecraftingAltarNodeLoader extends LazyLoader<Integer, RunecraftingAltarNode> {

    private static RunecraftingAltarNodeLoader instance = null;

    public static RunecraftingAltarNodeLoader getInstance() {
        if (instance == null)
            instance = new RunecraftingAltarNodeLoader();
        return instance;
    }

    private RunecraftingAltarNodeLoader() {
        super(RunecraftingAltarNodeDAO.getInstance());
    }
}
