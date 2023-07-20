package ethos.runehub.entity.player.action.impl.npc;

import ethos.model.players.DialogueHandler;
import ethos.model.players.Player;
import ethos.model.players.packets.Dialogue;
import ethos.runehub.action.click.npc.ClickNpcAction;
import ethos.runehub.dialog.DialogOption;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.dialog.DialogueUtils;
import ethos.runehub.entity.player.action.impl.ClickNPCAction;

public class FirstClickPortMasterAction extends ClickNPCAction {

    @Override
    public void perform() {
        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat("Port Master", 6999, "Hello " + this.getTitle(), "How may I help you?")
                .addOptions(this.getMainOptions())
                .build());
    }

    private DialogSequence getSailingInfoDialogSequence() {
        final String NAME = "Port Master";
        final int ID = 6999;
        return new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat(NAME, ID,
                        "Certainly! Sailing is a lucrative skill that allows you",
                        "to send ships out on voyages of exploration and trade.",
                        "Here we specifically sail into the vast archipelago known",
                        "as the Wushanko Isles."
                )
                .addNpcChat(NAME, ID,
                        "There are many regions of many islands in the",
                        "Wushanko Isles each of them offering their own unique",
                        "trades to sailors who make the voyage. However Wushanko",
                        "is a very dangerous place and it is very far away."
                )
                .addNpcChat(NAME, ID,
                        "Not all ships return home safely. If that happens",
                        "you'll find that all of the cargo you sent has been lost",
                        "at sea. For those who are brave enough though great fortune",
                        "and rare goods are waiting!"
                )
                .addNpcChat(NAME, ID,
                        "I'll show you the voyages you have available.",
                        "Come back every day as I will always have new ones.",
                        "You can tell me where you'd like to sail and create",
                        "your trade form and I will send a ship immediately."
                )
                .addNpcChat(NAME, ID,
                        "All the money and trade goods your ships",
                        "acquire will be added to your stockpile.",
                        "You can not sell items if they are not in your",
                        "stockpile so make sure to keep that well supplied."
                )
                .addNpcChat(NAME, ID,
                        "Likewise you can not buy any trade goods",
                        "if your coffers are empty. You can give me any money",
                        "you'd like added to your coffers so things can keep",
                        "running smoothly."
                )
                .addNpcChat(NAME, ID,
                        "Each voyage offers trade goods unique to",
                        "the region and island it is travelling to and",
                        "varying prices, so keep an eye out for good profit",
                        "margins and any potential unique items."
                )
                .addNpcChat(NAME, ID,
                        "I will let you know when a ship has returned",
                        "to port. You may come unload it at your leisure",
                        "but you will not be able to send it out again until",
                        "you've unloaded it."
                )
                .addPlayerChat(
                        DialogueHandler.SLIGHTLY_SAD,
                        "That's great and all but I was",
                        "hoping I'd get to do some sailing myself.",
                        "Will I be able to?"
                )
                .addNpcChat(NAME, ID,
                        "Hahahaha You must be joking!",
                        "I'd never let you sail one of these ships they're",
                        "far to expensive to risk for a land lubber like you!"
                )
                .addOptions(this.getMainOptions())
                .build();
    }

    private DialogOption[] getMainOptions() {
        return new DialogOption[]{
                new DialogOption("Tell me about the Sailing skill") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(getSailingInfoDialogSequence());
                    }
                },
                new DialogOption("What are my Sailing statistics?") {
                    @Override
                    public void onAction() {

                    }
                },
                new DialogOption("Focus on a specific region") {
                    @Override
                    public void onAction() {

                    }
                },
                new DialogOption("I'd like to start a voyage") {
                    @Override
                    public void onAction() {

                    }
                }
        };
    }

    private String getTitle() {
        final int score = 0;//this.getActor().getSkillController().getSailing().getScore();
        if (score < 400) {
            return "Sailor";
        } else if (score < 800) {
            return "Bo'sun";
        } else if (score < 1200) {
            return "Cap'n";
        } else if (score < 1600) {
            return "Commodore";
        } else if (score < 2000) {
            return "Admiral";
        }
        return "Portmaster";
    }

    public FirstClickPortMasterAction(Player player, int npcX, int npcY, int npcId, int npcIndex) {
        super(player, npcX, npcY, npcId, npcIndex);
    }

}
