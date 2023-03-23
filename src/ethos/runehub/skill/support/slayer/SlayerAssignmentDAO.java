package ethos.runehub.skill.support.slayer;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class SlayerAssignmentDAO extends BetaAbstractDataAcessObject<SlayerAssignment> {

    private static SlayerAssignmentDAO instance = null;

    public static SlayerAssignmentDAO getInstance() {
        if (instance == null)
            instance = new SlayerAssignmentDAO();
        return instance;
    }

    private SlayerAssignmentDAO() {
        super(RunehubConstants.SKILL_DB, SlayerAssignment.class);
    }
}
