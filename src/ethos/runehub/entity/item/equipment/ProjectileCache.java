package ethos.runehub.entity.item.equipment;

import org.runehub.api.io.load.LazyLoader;

public class ProjectileCache extends LazyLoader<Integer, Projectile> {

    private static ProjectileCache instance = null;

    public static ProjectileCache getInstance() {
        if (instance == null)
            instance = new ProjectileCache();
        return instance;
    }

    private ProjectileCache() {
        super(ProjectileDAO.getInstance());
    }
}
