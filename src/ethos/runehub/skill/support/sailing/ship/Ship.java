package ethos.runehub.skill.support.sailing.ship;

import ethos.runehub.skill.support.sailing.Sailing;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
import ethos.runehub.world.WorldSettingsController;

import java.text.DecimalFormat;
import java.time.Duration;

import static ethos.runehub.skill.Skill.SKILL_RANDOM;

public class Ship {
    public enum Status {
        AVAILABLE, ON_VOYAGE, RETURN_SUCCESS, RETURN_FAILED
    }

    public long toLong() {
        long bits = 0L;
        bits |= ((long) this.seafaring & 0xFFF) << 0;
        bits |= ((long) this.morale & 0xFFF) << 12;
        bits |= ((long) this.combat & 0xFFF) << 24;
        bits |= ((long) this.speed & 0xFFF) << 36;
        bits |= ((long) this.weightCapacity & 0x3FFF) << 48;
        bits |= ((long) this.status & 0x3) << 62;
        return bits;
    }


    public static Ship fromLong(long shipBits) {
        int seafaring = (int) (shipBits >> 0) & 0xFFF;
        int morale = (int) (shipBits >> 12) & 0xFFF;
        int combat = (int) (shipBits >> 24) & 0xFFF;
        int speed = (int) (shipBits >> 36) & 0xFFF;
        int weightCapacity = (int) (shipBits >> 48) & 0x3FFF;
        int statusOrdinal = (int) (shipBits >> 62) & 0x3;
        return new Ship(
                Math.min(Math.max(seafaring, 5), 4000),
                Math.min(Math.max(morale, 5), 4000),
                Math.min(Math.max(combat, 5), 4000),
                Math.min(Math.max(speed, 3), 4000),
                Math.min(weightCapacity, 10000),
                statusOrdinal
        );
    }

    public void completeVoyage(Voyage voyage) {
        System.out.println("Completing voyage");
        if (SKILL_RANDOM.nextFloat() <= this.getVoyageSuccessRate(voyage)) {
            this.onSuccessfulVoyage();
        } else {
            this.onFailedVoyage();
        }
    }

//    public void startVoyage() {
//        this.setStatus(Status.ON_VOYAGE.ordinal());
//    }

    private void onSuccessfulVoyage() {
        this.setStatus(Ship.Status.RETURN_SUCCESS.ordinal());
    }

    private void onFailedVoyage() {
        this.setStatus(Ship.Status.RETURN_FAILED.ordinal());
    }

    public String getSuccessRatePrint(float value) {
        final DecimalFormat decimalFormat = new DecimalFormat("##.##");
        return decimalFormat.format(value * 100) + "%";
    }

    public float getVoyageSuccessRate(Voyage voyage) {
        return (
                this.getSeafaringSuccessChance(voyage.getSeafaring())
                        + this.getMoraleSuccessChance(voyage.getMorale())
                        + this.getCombatSuccessChance(voyage.getCombat())
        ) / 3;
    }

    public float getSeafaringSuccessChance(int seafaring) {
        final int total = this.seafaring;
        float odds = (float) total / seafaring;
        return Math.min(odds, 1.0f);
    }

    public float getCombatSuccessChance(int combat) {
        final int total = this.combat;
        float odds = (float) total / combat;
        return Math.min(odds, 1.0f);
    }

    public float getMoraleSuccessChance(int morale) {
        final int total = this.morale;
        float odds = (float) total / morale;
        return Math.min(odds, 1.0f);
    }

    public long getVoyageDuration(Voyage voyage) {
        final double NAUTICAL_MILES_TRAVELLED_PER_DAY = (Sailing.BASE_KNOTS_PER_HOUR + this.speed) * Sailing.BASE_HOURS_SAILED_PER_DAY;
        final double DAYS_TO_COMPLETE_VOYAGE = voyage.getDistance() / NAUTICAL_MILES_TRAVELLED_PER_DAY;
        final double VOYAGE_DURATION_MS = (DAYS_TO_COMPLETE_VOYAGE * Duration.ofDays(1).toMillis()) *( WorldSettingsController.getInstance().getWorldSettings().getWeekendEventId() == 5 ? 0.5 : 1.0);
        final double DURATION_MS = Math.max(Duration.ofSeconds(30).toMillis(),VOYAGE_DURATION_MS);
        System.out.println(VOYAGE_DURATION_MS);
        return Math.round(DURATION_MS);
    }

    public int getSeafaring() {
        return Math.max(5,seafaring);
    }

    public void setSeafaring(int seafaring) {
        this.seafaring = seafaring;
    }

    public int getMorale() {
        return Math.max(5,morale);
    }

    public void setMorale(int morale) {
        this.morale = morale;
    }

    public int getCombat() {
        return Math.max(5,combat);
    }

    public void setCombat(int combat) {
        this.combat = combat;
    }

    public int getSpeed() {
        return Math.max(3,speed);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWeightCapacity() {
        return Math.max(50,weightCapacity);
    }

    public void setWeightCapacity(int weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "seafaring=" + seafaring +
                ", morale=" + morale +
                ", combat=" + combat +
                ", speed=" + speed +
                ", weightCapacity=" + weightCapacity +
                ", status=" + status +
                '}';
    }

    public Ship(int seafaring, int morale, int combat, int speed, int weightCapacity, int status) {
        this.seafaring = seafaring;
        this.morale = morale;
        this.combat = combat;
        this.speed = speed;
        this.weightCapacity = weightCapacity;
        this.status = status;
    }

    private int seafaring;
    private int morale;
    private int combat;
    private int speed;
    private int weightCapacity;
    private int status;
}


