package ethos.runehub.entity.player;

import org.rhd.api.entity.EntityContext;
import org.rhd.api.math.AdjustableNumber;
import org.rhd.api.math.impl.AdjustableInteger;

public class PlayerCharacterContext extends EntityContext {

    private final AdjustableNumber<Integer> jobScore;

    private PlayerCharacterContext(PlayerCharacterContextBuilder builder) {
        super(builder.id);
        this.jobScore = builder.jobScore;
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

        public PlayerCharacterContextBuilder(Long id) {
            this.id = id;
            this.jobScore = new AdjustableInteger(0);
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
