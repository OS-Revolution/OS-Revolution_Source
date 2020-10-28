package ethos.phantasye.job;

import org.phantasye.AbstractJsonRepository;

public class JobRepository extends AbstractJsonRepository<JobTarget> {
    @Override
    public void create(JobTarget jobTarget) {
        entries.put(String.valueOf(jobTarget.getItemId()),jobTarget);
    }

    @Override
    public JobTarget read(JobTarget jobTarget) {
        return entries.get(String.valueOf(jobTarget.getItemId()));
    }

    @Override
    public void delete(JobTarget jobTarget) {
        entries.remove(String.valueOf(jobTarget.getItemId()));
    }
}
