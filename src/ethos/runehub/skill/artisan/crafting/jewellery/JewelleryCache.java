package ethos.runehub.skill.artisan.crafting.jewellery;

import org.runehub.api.io.load.LazyLoader;

public class JewelleryCache extends LazyLoader<Integer, Jewellery> {

    private static JewelleryCache instance = null;

    public static JewelleryCache getInstance() {
        if (instance == null)
            instance = new JewelleryCache();
        return instance;
    }

    private JewelleryCache() {
        super(JewelleryDAO.getInstance());
    }
}
