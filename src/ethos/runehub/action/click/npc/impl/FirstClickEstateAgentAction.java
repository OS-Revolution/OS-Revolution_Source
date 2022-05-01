package ethos.runehub.action.click.npc.impl;

import ethos.model.players.Player;
import ethos.runehub.action.click.npc.ClickNpcAction;
import ethos.runehub.dialog.DialogSequence;

import java.text.NumberFormat;

public class FirstClickEstateAgentAction extends ClickNpcAction {

    public static final int FEE = 1000000;

    @Override
    protected void onActionStart() {
        if(!this.getActor().getContext().getPlayerSaveData().isHomeUnlocked()) {
            this.getActor().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getActor())
                    .addNpcChat("Estate Agent", this.getNpcId(),
                            "Hello there, are you interested in buying this house?",
                            "For a humble fee of " + NumberFormat.getInstance().format(FEE) + " Coins",
                            "of course."
                    )
                    .addOptions(3, "Yes", "No")
                    .build());
        } else {
            this.getActor().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getActor())
                    .addNpcChat("Estate Agent", this.getNpcId(),
                            "Would you like another tour?"
                    )
                    .addOptions(4, "Yes", "No")
                    .build());
        }
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    public FirstClickEstateAgentAction(Player attachment, int npcId, int npcIndex) {
        super(attachment, 1, npcId, npcIndex);
    }
}
