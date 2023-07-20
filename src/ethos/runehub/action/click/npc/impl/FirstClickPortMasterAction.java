package ethos.runehub.action.click.npc.impl;

import ethos.model.players.Player;
import ethos.runehub.action.click.npc.ClickNpcAction;
import ethos.runehub.dialog.DialogSequence;

public class FirstClickPortMasterAction extends ClickNpcAction {

    @Override
    protected void onActionStart() {
        this.getActor().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getActor())
                        .addNpcChat("Port Master",getNpcId(),"Hello " + this.getTitle(),"How may I help you?")
                        .addOptions(1,"Tell me about the Sailing skill","What are my Sailing statistics?","Focus on a specific region","I'd like to start a voyage")
                .build());
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {

    }

    @Override
    protected void onUpdate() {

    }

    private String getTitle() {
        final int score = 0;//this.getActor().getSkillController().getSailing().getScore();
        if (score < 400) {
            return "Sailor";
        } else if(score < 800) {
            return "Bo'sun";
        } else if(score < 1200) {
            return "Cap'n";
        } else if(score < 1600) {
            return "Commodore";
        } else if(score < 2000) {
            return "Admiral";
        }
        return "Portmaster";
    }

    public FirstClickPortMasterAction(Player attachment, int npcId, int npcIndex) {
        super(attachment, 1, npcId, npcIndex);
    }
}
