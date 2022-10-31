package ethos.runehub.ui.impl.tab.player;

import ethos.model.players.Player;
import ethos.runehub.TimeUtils;
import ethos.runehub.ui.component.impl.ProgressBarComponent;
import ethos.runehub.ui.component.impl.TextComponent;
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
        this.getPlayer().getPA().sendProgressUpdate(playPassProgressComponent.getLineId(),0, playPassProgressComponent.getProgress());
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
        playPassProgressComponent.setProgress(this.getPlayer().getContext().getPlayerSaveData().getPlayPassXp() / 500000);
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
        this.playPassProgressComponent = new ProgressBarComponent(57026,player.getContext().getPlayerSaveData().getPlayPassXp() / 500000);

        registerButton(actionEvent -> refresh(),222169);
        registerButton(actionEvent -> getPlayer().sendUI(new QuestTab(player)),222170);
        registerButton(actionEvent -> getPlayer().sendUI(new AchievementTab(player)),222171);
        registerButton(actionEvent -> getPlayer().sendUI(new DistractionsTab(player)),222172);
        registerButton(actionEvent -> getPlayer().sendUI(new Tab5(player)),222173);
        registerButton(actionEvent -> getPlayer().sendUI(new PlayPassUI(player)),222195);
    }

    private final TextComponent nameLabel,combatLevelLabel,totalLevelLabel,totalXPLabel,membershipLabel,timePlayedLabel;
    private final ProgressBarComponent playPassProgressComponent;
}
