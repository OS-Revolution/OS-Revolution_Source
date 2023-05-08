package ethos.runehub.skill.support.sailing;

public class Ship {

    public enum Status {
        AVAILABLE, ON_VOYAGE, RETURNED_SUCCESS, RETURN_FAILED
    }

    public static Ship fromBitArray(long bits) {
        int id = (int) (bits >> 54) & 0x1FF;
        int seafaring = (int) (bits >> 45) & 0x1FF;
        int morale = (int) (bits >> 36) & 0x1FF;
        int combat = (int) (bits >> 27) & 0x1FF;
        int speed = (int) (bits >> 18) & 0x1FF;
        int voyage = (int) (bits >> 9) & 0x1FF;
        int status = (int) (bits >> 7) & 0x03;
        Ship ship = new Ship(id, seafaring, morale, combat, speed, voyage, status);

        return ship;
    }

    public long toBitArray() {
        long bits = 0;

        bits |= (long) id << 54;
        bits |= (long) seafaring << 45;
        bits |= (long) morale << 36;
        bits |= (long) combat << 27;
        bits |= (long) speed << 18;
        bits |= (long) voyage << 9;
        bits |= (long) status << 7;
        return bits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeafaring() {
        return seafaring;
    }

    public void setSeafaring(int seafaring) {
        this.seafaring = seafaring;
    }

    public int getMorale() {
        return morale;
    }

    public void setMorale(int morale) {
        this.morale = morale;
    }

    public int getCombat() {
        return combat;
    }

    public void setCombat(int combat) {
        this.combat = combat;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getVoyage() {
        return voyage;
    }

    public void setVoyage(int voyage) {
        this.voyage = voyage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Ship(int id, int seafaring, int morale, int combat, int speed, int voyage, int status) {
        this.id = id;
        this.seafaring = seafaring;
        this.morale = morale;
        this.combat = combat;
        this.speed = speed;
        this.voyage = voyage;
        this.status = status;
    }

    private int id;
    private int seafaring;
    private int morale;
    private int combat;
    private int speed;
    private int voyage;
    private int status;
}

