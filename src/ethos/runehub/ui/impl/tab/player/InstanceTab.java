package ethos.runehub.ui.impl.tab.player;

import ethos.Server;
import ethos.event.Event;
import ethos.model.players.Player;
import ethos.runehub.TimeUtils;
import ethos.runehub.content.instance.BossArenaInstanceController;
import ethos.runehub.content.instance.Instance;
import ethos.runehub.content.instance.impl.BossArenaInstance;
import ethos.runehub.content.instance.impl.TimedInstance;
import ethos.runehub.ui.component.impl.ProgressBarComponent;
import ethos.runehub.ui.component.impl.TextComponent;
import ethos.runehub.ui.impl.tab.TabUI;

import java.util.Arrays;

public class InstanceTab extends TabUI {


    @Override
    protected void onOpen() {
        this.writeLine(titleLabel);
        this.writeLine(headerLabel);
        this.writeLine(subheaderLabel);
        Arrays.stream(playerNameLabels).forEach(this::writeLine);
    }

    @Override
    protected void onClose() {

    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected void refresh() {

    }

    private void sendProgress() {
        final long instanceEndTimestamp = (BossArenaInstanceController.getInstance().getInstance(getPlayer().getAttributes().getInstanceId()).getInstanceStartTimestamp()
                + BossArenaInstanceController.getInstance().getInstance(getPlayer().getAttributes().getInstanceId()).getDurationMS());
        Server.getEventHandler().submit(new Event<>("progress-update",getPlayer(),1) {
            @Override
            public void execute() {
                if (System.currentTimeMillis() >= instanceEndTimestamp) {
                    attachment.getPA().sendProgressUpdate(instanceProgressComponent.getLineId(), 0, 100);
                    subheaderLabel.setText("Time Remaining: " + TimeUtils.getShortDurationString(0));
                    this.stop();
                } else {
                    final BossArenaInstance instance = BossArenaInstanceController.getInstance().getInstance(getPlayer().getAttributes().getInstanceId());
                    if (instance != null) {
                        final long timeRemaining = (instanceEndTimestamp) - System.currentTimeMillis();
                        final double progress = ((elapsed * 600 + 0D) / instance.getDurationMS()) * 100D;
//                    System.out.println("Progress: " + progress);
//                    System.out.println("Time Remaining: " + timeRemaining);
                        attachment.getPA().sendProgressUpdate(instanceProgressComponent.getLineId(), 0, (int) progress);
                        subheaderLabel.setText("Time Remaining: " + TimeUtils.getHoursDurationString(timeRemaining));
                        writeLine(subheaderLabel);
                    }
                }
            }
        });
    }

    public InstanceTab(Player player, TimedInstance instance) {
        super(57500, player, 13);
        this.titleLabel = new TextComponent(57507, "Instances");
        this.headerLabel = new TextComponent(57514, "Boss Instance");
        this.subheaderLabel = new TextComponent(57515, "Time Remaining: " + TimeUtils.getHoursDurationString(instance.getDurationMS()));
        this.instanceProgressComponent = new ProgressBarComponent(57517);
        this.playerNameLabels = new TextComponent[]{new TextComponent(57551), new TextComponent(57552), new TextComponent(57553), new TextComponent(57554)};
        this.sendProgress();
    }

    private final TextComponent titleLabel, headerLabel, subheaderLabel;
    private final ProgressBarComponent instanceProgressComponent;
    private final TextComponent[] playerNameLabels;
}
