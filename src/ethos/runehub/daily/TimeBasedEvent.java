package ethos.runehub.daily;

import ethos.event.Event;
import ethos.model.players.Player;
import org.runehub.api.model.math.impl.AdjustableInteger;

import java.time.LocalTime;
import java.time.temporal.ChronoField;

public class TimeBasedEvent implements Runnable {

    @Override
    public void run() {
        System.out.println("Running");
    }

    protected boolean isAvailable(boolean available) {
        return available;
    }

    public void recharge() {

    }

    public Player getPlayer() {
        return player;
    }

    public long getFrequencyMS() {
        return frequencyMS;
    }

    public TimeBasedEvent(Player player, int hour, int minute) {
        this.player = player;
        this.frequencyMS = LocalTime.of(hour,minute).getLong(ChronoField.HOUR_OF_DAY);
    }

    private final long frequencyMS;
    private final Player player;
}
