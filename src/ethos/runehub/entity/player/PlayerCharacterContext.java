package ethos.runehub.entity.player;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ethos.phantasye.job.Job;
import ethos.runehub.building.Hotspot;
import org.rhd.api.entity.EntityContext;
import org.rhd.api.math.AdjustableNumber;
import org.rhd.api.math.impl.AdjustableDouble;
import org.rhd.api.math.impl.AdjustableInteger;
import org.rhd.api.math.impl.AdjustableLong;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class PlayerCharacterContext extends EntityContext {

    private final AdjustableInteger jobScore;
    private Job activeJob;
    private final AdjustableDouble magicFind;
    private final Map<Integer, Hotspot> hotspotMap;
    private final AdjustableLong spawnPoints;
    private long lastHomeTeleportTimestamp;
    private final AdjustableInteger instantTeleportCharges;
    private final Map<Integer,Integer> skillAnimationOverrideMap;

    private PlayerCharacterContext(PlayerCharacterContextBuilder builder) {
        super(builder.id);
        this.jobScore = builder.jobScore;
        this.activeJob = builder.job;
        this.magicFind = builder.magicFind;
        this.hotspotMap = builder.hotspotMap;
        this.spawnPoints = builder.spawnPoints;
        this.lastHomeTeleportTimestamp = builder.lastHomeTeleportTimestamp;
        this.instantTeleportCharges = builder.instantTeleportCharges;
        this.skillAnimationOverrideMap = builder.skillAnimationOverrideMap;
    }

    public Map<Integer, Integer> getSkillAnimationOverrideMap() {
        return skillAnimationOverrideMap;
    }

    public long getLastHomeTeleportTimestamp() {
        return lastHomeTeleportTimestamp;
    }

    public void setLastHomeTeleportTimestamp(long lastHomeTeleportTimestamp) {
        this.lastHomeTeleportTimestamp = lastHomeTeleportTimestamp;
    }

    public AdjustableNumber<Long> getSpawnPoints() {
        return spawnPoints;
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

    public String getSkillAnimationOverridesAsJson() {
        final Gson gson = new Gson();
        return gson.toJson(skillAnimationOverrideMap);
    }

    public AdjustableNumber<Integer> getInstantTeleportCharges() {
        return instantTeleportCharges;
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
        private final AdjustableInteger jobScore;
        private Job job;
        private final AdjustableDouble magicFind;
        private final AdjustableLong spawnPoints;
        private long lastHomeTeleportTimestamp;
        private final AdjustableInteger instantTeleportCharges;
        private Map<Integer, Hotspot> hotspotMap;
        private Map<Integer,Integer> skillAnimationOverrideMap;

        public PlayerCharacterContextBuilder(Long id) {
            this.id = id;
            this.jobScore = new AdjustableInteger(0);
            this.magicFind = new AdjustableDouble(0D);
            this.spawnPoints = new AdjustableLong(0L);
            this.instantTeleportCharges = new AdjustableInteger(3);
            this.hotspotMap = new HashMap<>();
            this.skillAnimationOverrideMap = new HashMap<>();
        }

        public PlayerCharacterContextBuilder withInstantTeleportCharges(int value) {
            this.instantTeleportCharges.setValue(value);
            return this;
        }

        public PlayerCharacterContextBuilder withLastHomeTeleportTimestamp(long timestamp) {
            this.lastHomeTeleportTimestamp = timestamp;
            return this;
        }

        public PlayerCharacterContextBuilder withHotspots(String json) {
            Gson gson = new Gson();
            Type token = new TypeToken<Map<Integer, Hotspot>>() {}.getType();
            System.out.println(json);
            this.hotspotMap = gson.fromJson(json,token);
            return this;
        }

        public PlayerCharacterContextBuilder withSkillAnimationOverrides(String json) {
            Gson gson = new Gson();
            Type token = new TypeToken<Map<Integer, Integer>>() {}.getType();
            System.out.println(json);
            this.skillAnimationOverrideMap = gson.fromJson(json,token);
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

        public PlayerCharacterContextBuilder withSpawnPoints(long value) {
            this.spawnPoints.setValue(value);
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
