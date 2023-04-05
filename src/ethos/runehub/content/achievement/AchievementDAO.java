package ethos.runehub.content.achievement;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.artisan.fletching.Fletchable;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class AchievementDAO extends BetaAbstractDataAcessObject<Achievement> {

    private static AchievementDAO instance = null;

    public static AchievementDAO getInstance() {
        if (instance == null)
            instance = new AchievementDAO();
        return instance;
    }

    private AchievementDAO() {
        super(RunehubConstants.REGION_DB, Achievement.class);
    }
}
