package ethos.runehub.skill.support.sailing.io;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class SailingMetricDAO extends BetaAbstractDataAcessObject<SailingMetric> {

    private static SailingMetricDAO instance = null;

    @Override
    public SailingMetric read(long id) {
        try {
            SailingMetric save = super.read(id);
            if (save != null) {
                return save;
            }
            save = new SailingMetric(id,0,0,0,new long[5],new long[5]);
            create(save);
            return save;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SailingMetricDAO getInstance() {
        if (instance == null)
            instance = new SailingMetricDAO();
        return instance;
    }

    private SailingMetricDAO() {
        super(RunehubConstants.METRICS_DB, SailingMetric.class);
    }
}
