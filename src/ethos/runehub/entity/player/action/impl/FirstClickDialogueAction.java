package ethos.runehub.entity.player.action.impl;

import ethos.model.players.Player;
import ethos.runehub.dialog.DialogSequence;

public class FirstClickDialogueAction extends ClickNPCAction{

    @Override
    public void perform() {
        switch (npcId) {
            case 637:
                player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                        .addNpcChat("Aubury",npcId, "Oh, sorry I'm a little busy right now", "A rift has opened up at the Wizard's Tower","I must get there and study it.")
                        .build());
                break;
            case 1328:
                player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                        .addNpcChat("Trader Stan",npcId, "Hello, and welcome to my travelling shop!", "I trade exotic wares from my travels for Jewels.","If you don't find something you like come back next time.","I've always got something new!")
                        .build());
                break;
            default:
                player.sendMessage("Submit a bug report with this error code [1]");
        }
    }

    public FirstClickDialogueAction(Player player, int npcX, int npcY, int npcId, int npcIndex) {
        super(player, npcX, npcY, npcId, npcIndex);
    }
}
