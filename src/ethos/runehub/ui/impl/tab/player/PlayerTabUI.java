package ethos.runehub.ui.impl.tab.player;

import ethos.model.players.Player;
import ethos.runehub.TimeUtils;
import ethos.runehub.content.journey.JourneyCache;
import ethos.runehub.ui.component.impl.ProgressBarComponent;
import ethos.runehub.ui.component.impl.TextComponent;
import ethos.runehub.ui.impl.JourneyUI;
import ethos.runehub.ui.impl.PlayPassUI;
import ethos.runehub.world.MembershipController;
import ethos.util.Misc;

import java.text.NumberFormat;
import java.util.Arrays;

public class PlayerTabUI extends InfoTab {


    @Override
    protected void onOpen() {
        this.writeLine(timePlayedLabel);
        this.writeLine(totalLevelLabel);
        this.writeLine(totalXPLabel);
        this.writeLine(combatLevelLabel);
        this.writeLine(membershipLabel);
        this.writeLine(nameLabel);
        this.writeLine(journeyLabel);
        this.getPlayer().getPA().sendProgressUpdate(playPassProgressComponent.getLineId(),0, playPassProgressComponent.getProgress());
        this.getPlayer().getPA().sendProgressUpdate(journeyProgressComponent.getLineId(),0, journeyProgressComponent.getProgress());
    }

    @Override
    protected void onClose() {

    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected void refresh() {
        nameLabel.setText(Misc.capitalize(this.getPlayer().getAttributes().getName().replaceAll("_"," ")));
        combatLevelLabel.setText(String.valueOf(this.getPlayer().combatLevel));
        totalLevelLabel.setText(String.valueOf(this.getPlayer().getSkillController().getTotalLevel()));
        totalXPLabel.setText(NumberFormat.getInstance().format(Arrays.stream(this.getPlayer().playerXP).sum()));
        membershipLabel.setText(MembershipController.getInstance().getDaysOfMembershipRemainingAsString(this.getPlayer().getContext()) + " Days");
        timePlayedLabel.setText(TimeUtils.getDurationAsDaysAndHoursString(TimeUtils.getDurationBetween(this.getPlayer().getContext().getPlayerSaveData().getJoinTimestamp(),System.currentTimeMillis())));
        playPassProgressComponent.setProgress((int) ((double)this.getPlayer().getContext().getPlayerSaveData().getPlayPassXp() / 500000.0D * 100));
        journeyLabel.setText(JourneyCache.getInstance().read(this.getPlayer().getContext().getPlayerSaveData().getJourneyId()).getName());
        journeyProgressComponent.setProgress(this.getPlayer().getAttributes().getJourneyController().getJourneyTotalCompletion());
        onOpen();
    }

    public PlayerTabUI(Player player) {
        super(57000, player);
        this.nameLabel = new TextComponent(57011, Misc.capitalize(player.getAttributes().getName().replaceAll("_"," ")));
        this.combatLevelLabel = new TextComponent(57016, String.valueOf(player.combatLevel));
        this.totalLevelLabel  = new TextComponent(57017,String.valueOf(player.getSkillController().getTotalLevel()));
        this.totalXPLabel  = new TextComponent(57021, NumberFormat.getInstance().format(Arrays.stream(player.playerXP).sum()));
        this.membershipLabel =  new TextComponent(57025, MembershipController.getInstance().getDaysOfMembershipRemainingAsString(player.getContext()) + " Days");
        this.timePlayedLabel  = new TextComponent(57036, TimeUtils.getDurationAsDaysAndHoursString(TimeUtils.getDurationBetween(player.getContext().getPlayerSaveData().getJoinTimestamp(),System.currentTimeMillis())));
        this.playPassProgressComponent = new ProgressBarComponent(57026, (int) ((double)this.getPlayer().getContext().getPlayerSaveData().getPlayPassXp() / 500000.0D * 100));
        this.journeyLabel = new TextComponent(57039, JourneyCache.getInstance().read(player.getContext().getPlayerSaveData().getJourneyId()).getName());
        this.journeyProgressComponent = new ProgressBarComponent(57037, this.getPlayer().getAttributes().getJourneyController().getJourneyTotalCompletion());
        registerButton(actionEvent -> refresh(),222169);
        registerButton(actionEvent -> getPlayer().sendUI(new QuestTab(player)),222170);
        registerButton(actionEvent -> getPlayer().sendUI(new AchievementTab(player)),222171);
        registerButton(actionEvent -> getPlayer().sendUI(new DistractionsTab(player)),222172);
        registerButton(actionEvent -> getPlayer().sendUI(new Tab5(player)),222173);
        registerButton(actionEvent -> getPlayer().sendUI(new PlayPassUI(player)),222195);
        registerButton(actionEvent -> getPlayer().sendUI(new JourneyUI(player,JourneyCache.getInstance().read(player.getContext().getPlayerSaveData().getJourneyId()))),222206);
    }

    private final TextComponent nameLabel,combatLevelLabel,totalLevelLabel,totalXPLabel,membershipLabel,timePlayedLabel,journeyLabel;
    private final ProgressBarComponent playPassProgressComponent,journeyProgressComponent;
}
