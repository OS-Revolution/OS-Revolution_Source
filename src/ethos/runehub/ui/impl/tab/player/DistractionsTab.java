package ethos.runehub.ui.impl.tab.player;

import ethos.model.players.Player;
import ethos.runehub.TimeUtils;
import ethos.runehub.event.FixedScheduledEventController;
import ethos.runehub.ui.component.impl.TextComponent;
import ethos.runehub.world.WorldSettingsController;

import java.util.Arrays;

public class DistractionsTab extends InfoTab {


    @Override
    protected void onOpen() {
        Arrays.stream(eventNameLabel).forEach(this::writeLine);
        Arrays.stream(eventTimeLabel).forEach(this::writeLine);
    }

    @Override
    protected void onClose() {

    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected void refresh() {
        Arrays.stream(eventNameLabel).forEach(this::writeLine);
        Arrays.stream(eventTimeLabel).forEach(this::writeLine);
    }

    private void registerEventNameLabels() {
        for (int i = 0; i < eventNameLabel.length; i++) {
            eventNameLabel[i] = new TextComponent(startIndex + (totalChildren) + i);
            eventNameLabel[i].setText(WorldSettingsController.getInstance().getFixedScheduleEvents()[i].getName());
        }
    }

    private void registerEventTimeLabels() {
        for (int i = 0; i < eventTimeLabel.length; i++) {
            eventTimeLabel[i] = new TextComponent(startIndex + (totalChildren * 2) + i);
            eventTimeLabel[i].setText("Next in: " + TimeUtils.getShortDurationString(
                            TimeUtils.getDurationBetween(System.currentTimeMillis(),
                                    FixedScheduledEventController.getInstance().getNextCycle(WorldSettingsController.getInstance().getFixedScheduleEvents()[i]).toInstant().toEpochMilli()
                            )
                    )
            );
        }
    }

    public DistractionsTab(Player player) {
        super(57300, player);

        registerButton(actionEvent -> refresh(), 223216);
        registerButton(actionEvent -> getPlayer().sendUI(new QuestTab(player)), 223214);
        registerButton(actionEvent -> getPlayer().sendUI(new AchievementTab(player)), 223215);
        registerButton(actionEvent -> getPlayer().sendUI(new PlayerTabUI(player)), 223213);
        registerButton(actionEvent -> getPlayer().sendUI(new Tab5(player)), 223217);
        registerButton(actionEvent -> getPlayer().sendMessage(WorldSettingsController.getInstance().getFixedScheduleEvents()[0].toString()),223227);
        this.eventNameLabel = new TextComponent[totalChildren];
        this.eventTimeLabel = new TextComponent[totalChildren];
        this.registerEventNameLabels();
        this.registerEventTimeLabels();
    }

    private final int totalChildren = WorldSettingsController.getInstance().getFixedScheduleEvents().length;
    private final int startIndex = 57315;
    private final TextComponent[] eventNameLabel, eventTimeLabel;
}
