package ethos.runehub.entity.item.impl;

import ethos.runehub.skill.combat.magic.MagicTablet;
import ethos.runehub.skill.combat.magic.MagicTabletDAO;
import org.runehub.api.io.load.LazyLoader;

public class TeleportTabletCache extends LazyLoader<Integer, TeleportTablet> {

    private static TeleportTabletCache instance = null;

    public static TeleportTabletCache getInstance() {
        if (instance == null)
            instance = new TeleportTabletCache();
        return instance;
    }

    private TeleportTabletCache() {
        super(TeleportTabletDAO.getInstance());
    }
}
