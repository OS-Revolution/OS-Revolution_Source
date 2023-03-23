package ethos.runehub.ui.impl.tab.player;

import ethos.model.players.Player;
import ethos.runehub.TimeUtils;
import ethos.runehub.entity.merchant.MerchantCache;
import ethos.runehub.entity.merchant.impl.CommodityMerchant;
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
        eventNameLabel[0] = new TextComponent(startIndex + (totalChildren) + 0,this.getEventName(0));
        eventNameLabel[1] = new TextComponent(startIndex + (totalChildren) + 1,this.getEventName(2));
        eventNameLabel[2] = new TextComponent(startIndex + (totalChildren) + 2,this.getEventName(3));
//        for (int i = 0; i < eventNameLabel.length; i++) {
//            eventNameLabel[i] = new TextComponent(startIndex + (totalChildren) + i);
//            eventNameLabel[i].setText(FixedScheduledEventController.getInstance().getFixedScheduleEvents()[i].getName());
//        }
    }

    private void registerEventTimeLabels() {
        eventTimeLabel[0] = new TextComponent(startIndex + (totalChildren * 2) + 0,this.getEventText(0));
        eventTimeLabel[1] = new TextComponent(startIndex + (totalChildren * 2) + 1,this.getEventText(2));
        eventTimeLabel[2] = new TextComponent(startIndex + (totalChildren * 2) + 2,this.getEventText(3));
//        for (int i = 0; i < eventTimeLabel.length; i++) {
//            eventTimeLabel[i] = new TextComponent(startIndex + (totalChildren * 2) + i);
//            eventTimeLabel[i].setText("Next in: " + TimeUtils.getShortDurationString(
//                            TimeUtils.getDurationBetween(System.currentTimeMillis(),
//                                    FixedScheduledEventController.getInstance().getNextCycle(
//                                            FixedScheduledEventController.getInstance().getFixedScheduleEvents()[i]).toInstant().toEpochMilli()
//                            )
//                    )
//            );
//        }
    }

    private String getEventName(int eId) {
        return FixedScheduledEventController.getInstance().getFixedScheduleEvents()[eId].getName();
    }

    private String getEventText(int eId) {
        return "Next in: " + TimeUtils.getShortDurationString(
                            TimeUtils.getDurationBetween(System.currentTimeMillis(),
                                    FixedScheduledEventController.getInstance().getNextCycle(
                                            FixedScheduledEventController.getInstance().getFixedScheduleEvents()[eId]).toInstant().toEpochMilli()));
    }

    public DistractionsTab(Player player) {
        super(57300, player);

        registerButton(actionEvent -> refresh(), 223216);
        registerButton(actionEvent -> getPlayer().sendUI(new QuestTab(player)), 223214);
        registerButton(actionEvent -> getPlayer().sendUI(new AchievementTab(player)), 223215);
        registerButton(actionEvent -> getPlayer().sendUI(new PlayerTabUI(player)), 223213);
        registerButton(actionEvent -> getPlayer().sendUI(new Tab5(player)), 223217);
        registerButton(actionEvent -> getPlayer().sendMessage(FixedScheduledEventController.getInstance().getFixedScheduleEvents()[0].toString()),223227);
        registerButton(actionEvent -> {
            int saleId = (((CommodityMerchant) MerchantCache.getInstance().read(1328)).getItemOnSaleId());
            if (saleId > 0) {
                getPlayer().sendMessage("^D&D $Trader_Stan has @"
                        + saleId
                        + " on sale for $"
                        + (((CommodityMerchant) MerchantCache.getInstance().read(1328)).getSale())
                        + "% off!");
            } else {
                getPlayer().sendMessage("^D&D $Trader_Stan is unavailable.");
            }
        },223229);
        this.eventNameLabel = new TextComponent[totalChildren];
        this.eventTimeLabel = new TextComponent[totalChildren];
        this.registerEventNameLabels();
        this.registerEventTimeLabels();
    }

    private final int totalChildren =3;
    private final int startIndex = 57315;
    private final TextComponent[] eventNameLabel, eventTimeLabel;
}
