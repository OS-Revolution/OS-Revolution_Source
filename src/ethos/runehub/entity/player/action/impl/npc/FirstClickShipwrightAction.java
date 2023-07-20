package ethos.runehub.entity.player.action.impl.npc;

import ethos.model.players.DialogueHandler;
import ethos.model.players.Player;
import ethos.runehub.dialog.DialogOption;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.entity.player.action.impl.ClickNPCAction;
import ethos.runehub.skill.support.sailing.ui.ShipwrightUI;

public class FirstClickShipwrightAction extends ClickNPCAction {

    private static final String NAME = "Shipwright";
    private static final int ID = 1430;

    @Override
    public void perform() {
        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat(NAME, ID, "Whatd'ya want? Can't you see I'm busy")
                .addOptions(this.getMainOptions())
                .build());
    }

    private DialogSequence getUpgradeInfoDialogSequence() {

        return new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat(NAME, ID,
                        "It's straightforward mate. You get the materials.",
                        "You pay me. I upgrade your ship. A better ship can",
                        "perform better at sea, go faster, and carry more."
                )
                .addOptions(this.getMainOptions())
                .build();
    }

    private DialogOption[] getMainOptions() {
        return new DialogOption[]{
                new DialogOption("Tell me about ship upgrades") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(getUpgradeInfoDialogSequence());
                    }
                },
                new DialogOption("Upgrade my ship") {
                    @Override
                    public void onAction() {
                        player.sendUI(new ShipwrightUI(player,0));
                    }
                },
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

    public FirstClickShipwrightAction(Player player, int npcX, int npcY, int npcId, int npcIndex) {
        super(player, npcX, npcY, npcId, npcIndex);
    }

}
