package ethos.runehub.ui.impl.tab.player;

import ethos.model.players.Player;
import ethos.runehub.content.journey.Journey;
import ethos.runehub.content.journey.JourneyCache;
import ethos.runehub.ui.component.impl.TextComponent;
import ethos.runehub.ui.impl.JourneyUI;

import java.util.Arrays;
import java.util.Objects;

public class Tab5 extends InfoTab {


    @Override
    protected void onOpen() {
        Arrays.stream(journeyNameLabel).filter(Objects::nonNull).forEach(this::writeLine);
        Arrays.stream(journeySecondaryLabel).filter(Objects::nonNull).forEach(this::writeLine);
    }

    @Override
    protected void onClose() {

    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected void refresh() {
        Arrays.stream(journeyNameLabel).filter(Objects::nonNull).forEach(this::writeLine);
        Arrays.stream(journeySecondaryLabel).filter(Objects::nonNull).forEach(this::writeLine);
    }

    private void registerJourneyNameLabels() {
        for (int i = 0; i < JourneyCache.getInstance().readAll().size(); i++) {
            journeyNameLabel[i] = new TextComponent(startIndex + (totalChildren) + i, JourneyCache.getInstance().readAll().toArray(new Journey[0])[i].getName());
        }
    }

    private void registerJourneySecondaryLabels() {
        for (int i = 0; i < JourneyCache.getInstance().readAll().size(); i++) {
            journeySecondaryLabel[i] = new TextComponent(startIndex + (totalChildren * 2) + i, "Total Steps: " + JourneyCache.getInstance().readAll().toArray(new Journey[0])[i].getSteps().length);
        }
    }

    public Tab5(Player player) {
        super(57400, player);

        registerButton(actionEvent -> refresh(), 224061);
        registerButton(actionEvent -> getPlayer().sendUI(new QuestTab(player)), 224058);
        registerButton(actionEvent -> getPlayer().sendUI(new AchievementTab(player)), 224059);
        registerButton(actionEvent -> getPlayer().sendUI(new DistractionsTab(player)), 224060);
        registerButton(actionEvent -> getPlayer().sendUI(new PlayerTabUI(player)), 224057);

        for (int i = 224071; i < 224071 + totalChildren; i++) {
            final int index = i;
            registerButton(actionEvent -> {
                if (JourneyCache.getInstance().containsKey(index - 224070)) {
                    getPlayer().sendUI(new JourneyUI(player, JourneyCache.getInstance().read(index - 224070)));
                }
            }, i);
        }

        this.journeyNameLabel = new TextComponent[totalChildren];
        this.journeySecondaryLabel = new TextComponent[totalChildren];
        this.registerJourneyNameLabels();
        this.registerJourneySecondaryLabels();
    }

    private final int totalChildren = 3;
    private final int startIndex = 57415;
    private final TextComponent[] journeyNameLabel, journeySecondaryLabel;
}
