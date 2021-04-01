package ethos.runehub.entity.player;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ethos.phantasye.job.Job;
import ethos.runehub.building.Hotspot;
import org.rhd.api.entity.EntityContext;
import org.rhd.api.math.AdjustableNumber;
import org.rhd.api.math.impl.AdjustableDouble;
import org.rhd.api.math.impl.AdjustableInteger;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class PlayerCharacterContext extends EntityContext {

    private final AdjustableNumber<Integer> jobScore;
    private Job activeJob;
    private final AdjustableNumber<Double> magicFind;
    private final Map<Integer, Hotspot> hotspotMap;

    private PlayerCharacterContext(PlayerCharacterContextBuilder builder) {
        super(builder.id);
        this.jobScore = builder.jobScore;
        this.activeJob = builder.job;
        this.magicFind = builder.magicFind;
        this.hotspotMap = builder.hotspotMap;
    }

    public Map<Integer, Hotspot> getHotspotMap() {
        return hotspotMap;
    }

    public AdjustableNumber<Double> getMagicFind() {
        return magicFind;
    }

    public String getHotspotsAsJson() {
        final Gson gson = new Gson();
        return gson.toJson(hotspotMap);
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
        private final AdjustableNumber<Double> magicFind;
        private Map<Integer, Hotspot> hotspotMap;

        public PlayerCharacterContextBuilder(Long id) {
            this.id = id;
            this.jobScore = new AdjustableInteger(0);
            this.magicFind = new AdjustableDouble(0D);
        }

        public PlayerCharacterContextBuilder withHotspots(String json) {
            Gson gson = new Gson();
            Type token = new TypeToken<Map<Integer, Hotspot>>() {}.getType();
            System.out.println(json);
            this.hotspotMap = gson.fromJson(json,token);
            return this;
        }

        public PlayerCharacterContextBuilder setMagicFind(double mf) {
            this.magicFind.setValue(mf);
            return this;
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
