package ethos.runehub.entity.player;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.support.sailing.SailingSaveData;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class PlayerSailingSaveDAO extends BetaAbstractDataAcessObject<SailingSaveData> {

    private static PlayerSailingSaveDAO instance = null;

    public static PlayerSailingSaveDAO getInstance() {
        if (instance == null)
            instance = new PlayerSailingSaveDAO();
        return instance;
    }

    @Override
    public SailingSaveData read(long id) {
        SailingSaveData save = super.read(id);
        if (save != null) {
            return save;
        }
        save = new SailingSaveData(id,new long[3],new int[5],new long[3]);
        create(save);
        return save;
    }

    private PlayerSailingSaveDAO() {
        super(RunehubConstants.PLAYER_DB, SailingSaveData.class);
    }
}
