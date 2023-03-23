package ethos.runehub.entity.player.action.impl;

import ethos.model.players.Player;
import ethos.runehub.RunehubConstants;
import ethos.runehub.RunehubUtils;
import ethos.runehub.dialog.DialogOption;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.skill.support.slayer.Slayer;
import ethos.runehub.skill.support.slayer.SlayerAssignmentDAO;
import ethos.runehub.ui.impl.SlayerTaskManagementUI;

import java.util.ArrayList;
import java.util.List;

public class FirstClickDialogueAction extends ClickNPCAction {

    @Override
    public void perform() {
        switch (npcId) {
            case 637:
                player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                        .addNpcChat("Aubury", npcId, "Oh, sorry I'm a little busy right now", "A rift has opened up at the Wizard's Tower", "I must get there and study it.")
                        .build());
                break;
            case 1328:
                player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                        .addNpcChat("Trader Stan", npcId, "Hello, and welcome to my travelling shop!", "I trade exotic wares from my travels for Jewels.", "If you don't find something you like come back next time.", "I've always got something new!")
                        .build());
                break;
            case 401:
                player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                        .addNpcChat(npcId, "Hello, what can I help you with?")
                        .addOptions(getPrimarySlayerOptionMenu())
                        .build());

                break;
            case 1329:
                player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                        .addNpcChat(npcId, "Greetings! Where can I take you?")
                        .addOptions(new DialogOption("Show me your destinations") {
                                        @Override
                                        public void onAction() {
                                            player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                                    .addOptions(getTaskManagementSlayerOptionMenu())
                                                    .build());
                                        }
                                    },
                                new DialogOption("Nevermind") {
                                    @Override
                                    public void onAction() {
                                        player.getAttributes().getActiveDialogSequence().next();
                                    }
                                })
                        .build());
                break;
            default:
                player.sendMessage("Submit a bug report with this error code [1]");
        }
    }

    private DialogOption[] getPrimarySlayerOptionMenu() {
        return new DialogOption[]{
                new DialogOption("Task Management") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(getTaskManagementSlayerOptionMenu())
                                .build());
                    }
                },
                new DialogOption("Where can I find my task?") {
                    @Override
                    public void onAction() {
                        player.getContext().getPlayerSaveData().setBurnLogs(!player.getContext().getPlayerSaveData().isBurnLogs());
                        player.sendMessage("You toggle burn mode $" + RunehubUtils.getBooleanAsOnOrOff(player.getContext().getPlayerSaveData().isBurnLogs()));
                        player.getAttributes().getActiveDialogSequence().next();
                    }
                }};
    }

    private DialogOption[] getTaskManagementSlayerOptionMenu() {
        return new DialogOption[]{
                new DialogOption("Cancel Task (" + RunehubConstants.CANCEL_TASK_COST + " points)") {
                    @Override
                    public void onAction() {
                        player.getSkillController().getSlayer().cancelCurrentTask(npcId);
                    }
                },
                new DialogOption("Extend Task (" + RunehubConstants.EXTEND_TASK_COST + " points)") {
                    @Override
                    public void onAction() {
                        player.getSkillController().getSlayer().extendCurrentTask(npcId);
                    }
                },
                new DialogOption("Block Task (" + RunehubConstants.BLOCK_TASK_COST + " points)") {
                    @Override
                    public void onAction() {
                        player.getSkillController().getSlayer().blockCurrentTask(npcId);
                    }
                },
                new DialogOption("Prefer Task (" + RunehubConstants.PREFER_TASK_COST + " points)") {
                    @Override
                    public void onAction() {
                        player.getSkillController().getSlayer().preferCurrentTask(npcId);
                    }
                },
                new DialogOption("Next") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(getTaskManagementSlayerOptionMenu2())
                                .build());
                    }
                }
        };
    }

    private DialogOption[] getTaskManagementSlayerOptionMenu2() {
        return new DialogOption[]{
                new DialogOption("Manage blocked tasks") {
                    @Override
                    public void onAction() {
                        player.sendUI(new SlayerTaskManagementUI(player, player.getSlayerSave().getBlockedAssignments(), "Blocked"));
                    }
                },
                new DialogOption("Manage preferred tasks") {
                    @Override
                    public void onAction() {
                        player.sendUI(new SlayerTaskManagementUI(player, player.getSlayerSave().getPreferredAssignments(), "Preferred"));
                    }
                },
                new DialogOption("Nevermind") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(getPrimarySlayerOptionMenu())
                                .build());
                    }
                }
        };
    }

    public FirstClickDialogueAction(Player player, int npcX, int npcY, int npcId, int npcIndex) {
        super(player, npcX, npcY, npcId, npcIndex);
    }
}
