package ethos.runehub.entity.player;

import ethos.runehub.skill.gathering.farming.FarmingConfig;
import ethos.runehub.skill.gathering.farming.patch.PatchType;
import org.runehub.api.io.load.LazyLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerFarmingSaveLoader extends LazyLoader<Long, PlayerFarmingSave> {

    private static PlayerFarmingSaveLoader instance = null;

    public static PlayerFarmingSaveLoader getInstance() {
        if (instance == null)
            instance = new PlayerFarmingSaveLoader();
        return instance;
    }

//    @Override
//    protected PlayerFarmingSave load(Long key) {
//        try {
//            if (PlayerFarmingSaveDAO.getInstance().read(key) != null)
//            return PlayerFarmingSaveDAO.getInstance().read(key) : new PlayerFarmingSave(key,new HashMap<>());
//            throw new NullPointerException();
//        } catch (Exception e) {
//            final PlayerFarmingSave save = new PlayerFarmingSave(key,new HashMap<>());
//            save.getConfigMap().put(14391,configForFarm(14391));
//            save.getConfigMap().put(10548,configForFarm(10548));
//            save.getConfigMap().put(11062,configForFarm(11062));
//            PlayerFarmingSaveDAO.getInstance().create(save);
//            return save;
//        }
//    }

    private List<FarmingConfig> configForFarm(int regionId) {
        final List<FarmingConfig> configs = new ArrayList<>();
        switch (regionId) {
            case 14391: //canafis
            case 10548: //ardougne
            case 11062: //catherby
                configs.add(new FarmingConfig(0,0,false,false, PatchType.ALLOTMENT.ordinal(),0,0));
                configs.add(new FarmingConfig(0,8,false,false, PatchType.ALLOTMENT.ordinal(),0,0));
                configs.add(new FarmingConfig(0,16,false,false, PatchType.FLOWER.ordinal(),0,0));
                configs.add(new FarmingConfig(0,24,false,false, PatchType.HERB.ordinal(),0,0));
                break;
        }
        return configs;
    }

    private PlayerFarmingSaveLoader() {
        super(PlayerFarmingSaveDAO.getInstance());
    }
}
