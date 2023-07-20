package ethos.runehub.skill.support.sailing.io;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

import java.util.ArrayList;

public class PlayerSailingSaveDAO extends BetaAbstractDataAcessObject<SailingSaveData> {

    private static PlayerSailingSaveDAO instance = null;

    public static PlayerSailingSaveDAO getInstance() {
        if (instance == null)
            instance = new PlayerSailingSaveDAO();
        return instance;
    }

    @Override
    public SailingSaveData read(long id) {
        try {
            SailingSaveData save = super.read(id);
            if (save != null) {
                return save;
            }
            save = new SailingSaveData(id, new long[3][5], new long[3][5], new long[3], new long[5], new long[3],
                    0, -1, new long[3], new long[50],new long[5][10],new long[5][10],0);
            create(save);
            return save;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private PlayerSailingSaveDAO() {
        super(RunehubConstants.PLAYER_DB, SailingSaveData.class);
    }
}
