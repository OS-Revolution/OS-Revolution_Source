package ethos.runehub.content;

import ethos.model.players.Player;
import ethos.util.Misc;

public class PointController {

    public enum PointType {
        JOURNEY,PVM,LOYALTY;

        @Override
        public String toString() {
            return Misc.capitalize(name().toLowerCase());
        }
    }

    public int getPoints(PointType type) {
        return this.getPoints(type.ordinal());
    }

    public boolean subtractPoints(PointType type, int amount) {
        return this.subtractPointsFrom(type.ordinal(),amount);
    }

    public boolean addPoints(PointType type, int amount) {
        return this.addPoints(type.ordinal(),amount);
    }

    public void setPoints(int index, int amount) {
        player.getContext().getPlayerSaveData().getPoints()[index] = amount;
    }

    public boolean subtractPointsFrom(int index, int amount) {
        int points = this.getPoints(index);
        if (points >= amount) {
            this.setPoints(index, points - amount);
            return true;
        }
        return false;
    }

    public boolean addPoints(int index, int amount) {
        int points = this.getPoints(index);
        int newPoints = Math.max(0, Math.min(Integer.MAX_VALUE, points + amount));
        this.setPoints(index, newPoints);
        return newPoints == points + amount;
    }

    public int getPoints(int index) {
        return player.getContext().getPlayerSaveData().getPoints()[index];
    }

    public PointController(Player player) {
        this.player = player;
    }

    private final Player player;
}
