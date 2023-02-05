package ethos.runehub.event.dnd;

import ethos.runehub.content.PlayPassController;
import ethos.runehub.event.FixedScheduleEvent;
import ethos.runehub.world.WorldSettingsController;

import java.time.Duration;

public class PlayPassStartSeasonEvent extends FixedScheduleEvent {


    @Override
    public void execute() {
        PlayPassController.resetPreviousSeasonData();

    }



    public PlayPassStartSeasonEvent() {
        super(Duration.ofDays(60).toMillis(), "Play Pass Season");
    }
}
