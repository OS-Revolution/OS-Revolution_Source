package ethos.runehub.content.instance.impl;

import ethos.runehub.RunehubConstants;
import ethos.runehub.content.rift.RiftFloor;
import ethos.runehub.entity.item.GameItem;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

public class InstanceBossDAO extends BetaAbstractDataAcessObject<InstanceBoss> {

    private static InstanceBossDAO instance = null;

    public static InstanceBossDAO getInstance() {
        if (instance == null)
            instance = new InstanceBossDAO();
        return instance;
    }

    protected InstanceBossDAO() {
        super(RunehubConstants.RIFT_DB, InstanceBoss.class);
        create(new InstanceBoss(
                2215,60,280,250,255,20,false,new GameItem[]{new GameItem(995,10000)}
        ));
        create(new InstanceBoss(
                5126,30,160,220,240,20,false,new GameItem[]{new GameItem(995,10000)}
        ));
    }
}
