package ethos.phantasye.job;

import java.util.HashMap;
import java.util.Map;

public final class JobFactory {

    private static final Map<Integer,JobTarget> jobTargetMap = new HashMap<>();

    public static JobTarget getJobTarget(int itemId) {
        JobTarget target = jobTargetMap.get(itemId);

        if (target == null) {
            target = Job.JOB_REPOSITORY.getRepository().readByKey(String.valueOf(itemId));
            if (target == null) {
                throw new NullPointerException("Job not found!");
            }
            jobTargetMap.put(itemId,target);
        }
        return target;
    }
}
