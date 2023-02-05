package ethos.runehub.entity.mob;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class AnimationDefinitionDAO extends BetaAbstractDataAcessObject<AnimationDefinition> {

    private static AnimationDefinitionDAO instance = null;

    public static AnimationDefinitionDAO getInstance() {
        if (instance == null)
            instance = new AnimationDefinitionDAO();
        return instance;
    }

    private AnimationDefinitionDAO() {
        super(RunehubConstants.OS_DEFINTIONS_DB, AnimationDefinition.class);

        create(new AnimationDefinition(5126,new int[] {6510,6513,6515,6512}));
    }
}
