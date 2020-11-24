package ethos.runehub.entity.player;

import ethos.phantasye.job.Job;
import org.rhd.api.entity.EntityContext;
import org.rhd.api.math.AdjustableNumber;
import org.rhd.api.math.impl.AdjustableInteger;

public class PlayerCharacterContext extends EntityContext {

    private final AdjustableNumber<Integer> jobScore;
    private Job activeJob;

    private PlayerCharacterContext(PlayerCharacterContextBuilder builder) {
        super(builder.id);
        this.jobScore = builder.jobScore;
        this.activeJob = builder.job;
    }

    public Job getActiveJob() {
        return activeJob;
    }

    public void setActiveJob(Job activeJob) {
        this.activeJob = activeJob;
    }

    public AdjustableNumber<Integer> getJobScore() {
        return jobScore;
    }

    @Override
    public Long getId() {
        return super.getId().longValue();
    }

    public static class PlayerCharacterContextBuilder {

        private final Long id;
        private final AdjustableNumber<Integer> jobScore;
        private Job job;

        public PlayerCharacterContextBuilder(Long id) {
            this.id = id;
            this.jobScore = new AdjustableInteger(0);
        }

        public PlayerCharacterContextBuilder withJob(Job job) {
            this.job = job;
            return this;
        }

        public PlayerCharacterContextBuilder withJobScore(int value) {
            this.jobScore.setValue(value);
            return this;
        }

        public PlayerCharacterContext build() {
            return new PlayerCharacterContext(this);
        }
    }
}