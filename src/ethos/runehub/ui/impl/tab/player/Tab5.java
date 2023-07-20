package ethos.runehub.ui.impl.tab.player;

import ethos.model.players.Player;
import ethos.runehub.content.journey.Journey;
import ethos.runehub.content.journey.JourneyCache;
import ethos.runehub.ui.component.impl.TextComponent;
import ethos.runehub.ui.impl.JobSummaryUI;
import ethos.runehub.ui.impl.JobUI;
import ethos.runehub.ui.impl.JourneyUI;
import ethos.runehub.ui.impl.PointStatUI;
import org.apache.poi.hssf.record.formula.functions.T;

import java.util.Arrays;
import java.util.Objects;

public class Tab5 extends InfoTab {


    @Override
    protected void onOpen() {
        Arrays.stream(lineLabel).filter(Objects::nonNull).forEach(this::writeLine);
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

    public Tab5(Player player) {
        super(57400, player);

        registerButton(actionEvent -> refresh(), 224061);
        registerButton(actionEvent -> getPlayer().sendUI(new QuestTab(player)), 224058);
        registerButton(actionEvent -> getPlayer().sendUI(new AchievementTab(player)), 224059);
        registerButton(actionEvent -> getPlayer().sendUI(new DistractionsTab(player)), 224060);
        registerButton(actionEvent -> getPlayer().sendUI(new PlayerTabUI(player)), 224057);

        this.lineLabel = new TextComponent[totalChildren];

        lineLabel[0] = new TextComponent(57415,"Active Job");
        lineLabel[1] = new TextComponent(57416,"Job Summary");
        lineLabel[2] = new TextComponent(57417,"Point Summary");
        this.registerButton(actionEvent -> player.sendUI(new JobUI(player)),224071);
        this.registerButton(actionEvent -> player.sendUI(new JobSummaryUI(player)),224072);
        this.registerButton(actionEvent -> player.sendUI(new PointStatUI(player)),224073);
    }

    private final int totalChildren = 30;
    private final int startIndex = 57415;
    private final TextComponent[] lineLabel;
//    private final TextComponent[] journeyNameLabel, journeySecondaryLabel;
}
