package ethos.phantasye.job;

import ethos.runehub.ServerFileSystem;
import org.phantasye.RepositoryManager;
import org.rhd.api.io.fs.ApplicationFileSystem;
import org.rhd.api.io.fs.Extension;
import org.rhd.api.math.impl.AdjustableInteger;

import java.util.Observable;

public final class Job extends Observable {

    public static final RepositoryManager<JobTarget,JobRepository> JOB_REPOSITORY =
            new RepositoryManager<>(ServerFileSystem.getInstance().buildFileRequest()
                    .inDirectory(ApplicationFileSystem.APP_DIRECTORY)
                    .inDirectory(ServerFileSystem.APP_HOME)
                    .inDirectory(ServerFileSystem.RESOURCE_HOME)
                    .inDirectory(ServerFileSystem.DATABASE_HOME)
                    .withFileName("job-data")
                    .withExtension(Extension.JSON)
                    .build().getFile().getAbsolutePath(),JobRepository.class);

    public static enum Difficulty {
        EASY(50,500,20000,0),MEDIUM(100,5000,50000,0.1),HARD(250,10000, 100000,0.3),LEGENDARY(500,50000, 500000,0.5);

        private final int quotaMin;
        private final int xpMin;
        private final int basePay;
        private final double containerId;

        private Difficulty(int min, int mod, int basePay,double containerId) {
            this.quotaMin = min;
            this.xpMin = mod;
            this.basePay = basePay;
            this.containerId = containerId;
        }

        public double getMagicFindBonus() {
            return containerId;
        }

        public int getBasePay() {
            return basePay;
        }

        public int getQuotaMin() {
            return quotaMin;
        }

        public int getXpMin() {
            return xpMin;
        }

        public static Difficulty previous(Difficulty difficulty) {
            return difficulty.ordinal() > 0 ? Difficulty.values()[difficulty.ordinal() - 1] : difficulty;
        }
    }

    private final int skillId;
    private final AdjustableInteger quota;
    private final int targetId;
    private final Difficulty difficulty;

    private Job(JobBuilder builder) {
        this.skillId = builder.skillId;
        this.quota = new AdjustableInteger(builder.quota);
        this.targetId = builder.targetId;
        this.difficulty = builder.difficulty;
    }


    public AdjustableInteger getQuota() {
        return quota;
    }

    public int getSkillId() {
        return skillId;
    }

    public int getTargetId() {
        return targetId;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public static class JobBuilder {

        private int skillId;
        private int quota;
        private int targetId;
        private Difficulty difficulty;

        public JobBuilder() {
        }

        public JobBuilder forSkill(int skillId) {
            this.skillId = skillId;
            return this;
        }

        public JobBuilder withQuota(int quota) {
            this.quota = quota;
            return this;
        }

        public JobBuilder withTargetId(int targetId) {
            this.targetId = targetId;
            return this;
        }

        public JobBuilder withDifficulty(Difficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public Job build() {
            return new Job(this);
        }
    }
}
