package ethos.runehub.entity.player.action.impl.npc;

import ethos.model.players.Player;
import ethos.runehub.content.journey.JourneyStepType;
import ethos.runehub.dialog.Dialog;
import ethos.runehub.dialog.DialogOption;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.entity.player.action.impl.ClickNPCAction;
import ethos.runehub.skill.gathering.farming.action.AdvanceGrowthStageAction;

public class FirstClickMartinAction extends ClickNPCAction {

    private static final int ID = 5832;
    private static final String NAME = "Martin";

    @Override
    public void perform() {
        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat(NAME, ID, "Hello there how may I help you?")
                .addOptions(this.getHelpOptions())
                .setMovementRestricted(false)
                .setActionLocked(false)
                .build());
    }

    private DialogSequence getFarmingInfoSequence() {
        return new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat(NAME, ID, "All around the world Gilenor are farming patches", "these patches each can grow a specific type of crop.",
                        "Once you've found a patch just rake it clear of weeds", "apply your compost and plant your seeds.")
                .addNpcChat(NAME, ID, "After some time your plant will grow.", "Occasionally your plant may get diseased",
                        " and will die if not cured. Oh you're probably wondering", "where to get the many supplies you will need.")
                .addNpcChat(NAME, ID, "The general store can sell you the basic tools.", "Seeds can be found as drops, and shops may carry them.",
                        "If you bring me any weeds you get from farming I will", "turn them into compost for you.")
                .addDialogueAction(new Dialog() {
                    @Override
                    public void onSend() {
                        player.getAttributes().getAchievementController().completeAchievement(-9010718152367518955L);
//                        player.getAttributes().getJourneyController().checkJourney(-7567195874892689851L,1);
                        player.getAttributes().getJourneyController().checkJourney(ID,1, JourneyStepType.DIALOG);
                    }
                })
                .build();
    }

    private DialogOption[] getHelpOptions() {
        return new DialogOption[]{
                new DialogOption("How do I start farming?") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(getFarmingInfoSequence());
                    }
                },
                new DialogOption("What is foraging?") {
                    @Override
                    public void onAction() {

                    }
                },
                new DialogOption("Nevermind") {
                    @Override
                    public void onAction() {
                        player.getAttributes().getActiveDialogSequence().next();
                        player.getPA().closeAllWindows();
                    }
                }
        };
    }

    public FirstClickMartinAction(Player player, int npcX, int npcY, int npcId, int npcIndex) {
        super(player, npcX, npcY, npcId, npcIndex);
    }
}
