package ethos.runehub.skill;

import ethos.runehub.skill.artisan.herblore.potion.Potion;
import ethos.runehub.skill.artisan.herblore.potion.PotionDAO;
import org.runehub.api.io.load.LazyLoader;

public class PotionLoader extends LazyLoader<Integer, Potion> {

    private static PotionLoader instance = null;

    public static PotionLoader getInstance() {
        if (instance == null)
            instance = new PotionLoader();
        return instance;
    }

    private PotionLoader() {
        super(PotionDAO.getInstance());
    }
}
