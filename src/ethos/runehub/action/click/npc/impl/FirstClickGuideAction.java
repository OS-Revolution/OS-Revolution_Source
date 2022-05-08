package ethos.runehub.action.click.npc.impl;

import ethos.model.players.Player;
import ethos.runehub.action.click.npc.ClickNpcAction;
import ethos.runehub.dialog.DialogSequence;

public class FirstClickGuideAction extends ClickNpcAction {

    @Override
    protected void onActionStart() {
        this.getActor().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getActor())
                .addNpcChat(3308, "Hello there! Welcome to Draynor.", "Have I seen you before?")
                .addOptions(1, "Yes", "No")
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

    public FirstClickGuideAction(Player attachment, int npcId, int npcIndex) {
        super(attachment, 1, npcId, npcIndex);
    }
}
