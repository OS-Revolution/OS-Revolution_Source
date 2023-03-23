package ethos.runehub.entity.player;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.support.slayer.SlayerTask;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

import java.util.logging.Logger;

public class PlayerSlayerSaveDAO extends BetaAbstractDataAcessObject<PlayerSlayerSave> {

    private static PlayerSlayerSaveDAO instance = null;

    public static PlayerSlayerSaveDAO getInstance() {
        if (instance == null)
            instance = new PlayerSlayerSaveDAO();
        return instance;
    }

    @Override
    public PlayerSlayerSave read(long id) {
        PlayerSlayerSave slayerSave = super.read(id);
        if (slayerSave != null) {
            return slayerSave;
        }
        slayerSave = new PlayerSlayerSave(id,-1L,0,new long[8], new long[8],0,-1,0,0,0,
                new boolean[50]);
        create(slayerSave);
        return slayerSave;
    }

    private PlayerSlayerSaveDAO() {
        super(RunehubConstants.PLAYER_DB, PlayerSlayerSave.class);
    }
}
