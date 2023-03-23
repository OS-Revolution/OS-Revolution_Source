package ethos.runehub.entity.item.equipment;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class ProjectileDAO extends BetaAbstractDataAcessObject<Projectile> {

    private static ProjectileDAO instance = null;

    public static ProjectileDAO getInstance() {
        if (instance == null)
            instance = new ProjectileDAO();
        return instance;
    }

    private ProjectileDAO() {
        super(RunehubConstants.EQUIPMENT, Projectile.class);
    }
}
