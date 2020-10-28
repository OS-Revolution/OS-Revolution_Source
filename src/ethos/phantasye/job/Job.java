package ethos.phantasye.job;

import org.menaphos.Menaphos;
import org.menaphos.io.fs.Extension;
import org.menaphos.io.fs.FileRequest;
import org.menaphos.io.fs.impl.DefaultFileSystem;
import org.phantasye.RepositoryManager;
import org.rhd.api.math.impl.AdjustableInteger;

import java.util.Observable;

public final class Job extends Observable {

    public static final RepositoryManager<JobTarget,JobRepository> JOB_REPOSITORY =
            new RepositoryManager<>(new FileRequest.FileRequestBuilder(Menaphos.getInstance().getFileSystem())
                    .inDirectory(DefaultFileSystem.SERVER)
                    .inDirectory(DefaultFileSystem.DATA)
                    .inDirectory(DefaultFileSystem.REPOSITORY)
                    .withFileName("job-data")
                    .withExtension(Extension.JSON)
                    .build().getFile().getAbsolutePath(),JobRepository.class);

    public static enum Difficulty {
        EASY(50,500,20000),MEDIUM(100,5000,50000),HARD(250,10000, 100000),LEGENDARY(500,50000, 500000);

        private final int quotaMin;
        private final int xpMin;
        private final int basePay;

        private Difficulty(int min, int mod, int basePay) {
            this.quotaMin = min;
            this.xpMin = mod;
            this.basePay = basePay;
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
    private transient final Employee employee;

    private Job(JobBuilder builder) {
        this.skillId = builder.skillId;
        this.quota = new AdjustableInteger(builder.quota);
        this.targetId = builder.targetId;
        this.employee = builder.employee;
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
        private final Employee employee;

        public JobBuilder(Employee employee) {
            this.employee = employee;
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
