package ethos.runehub.entity.item.impl;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.combat.magic.MagicTablet;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class TeleportTabletDAO extends BetaAbstractDataAcessObject<TeleportTablet> {

    private static TeleportTabletDAO instance = null;

    public static TeleportTabletDAO getInstance() {
        if (instance == null)
            instance = new TeleportTabletDAO();
        return instance;
    }

    private TeleportTabletDAO() {
        super(RunehubConstants.ITEM_INTERACTION_DB, TeleportTablet.class);
    }
}
