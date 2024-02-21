package ethos.runehub.entity.item;


import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class ArtisanSkillItemReactionDAO extends BetaAbstractDataAcessObject<ArtisanSkillItemReaction> {

    private static ArtisanSkillItemReactionDAO instance = null;

    public static ArtisanSkillItemReactionDAO getInstance() {
        if (instance == null)
            instance = new ArtisanSkillItemReactionDAO();
        return instance;
    }

    private ArtisanSkillItemReactionDAO() {
        super("C:\\Users\\quint\\Desktop\\Work\\Projects\\Games\\rsps\\OS-Revolution\\Server\\Data\\runehub\\db\\item-interactions.db", ArtisanSkillItemReaction.class);
    }
}
