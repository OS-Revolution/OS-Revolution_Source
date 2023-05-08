package ethos.runehub.entity.player.action.impl;

import ethos.model.players.Player;
import ethos.runehub.RunehubConstants;
import ethos.runehub.RunehubUtils;
import ethos.runehub.content.journey.JourneyStepType;
import ethos.runehub.dialog.Dialog;
import ethos.runehub.dialog.DialogOption;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.skill.support.slayer.Slayer;
import ethos.runehub.skill.support.slayer.SlayerAssignmentDAO;
import ethos.runehub.ui.impl.CharterUI;
import ethos.runehub.ui.impl.JourneySelectionUI;
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
                        .addDialogueAction(new Dialog() {
                            @Override
                            public void onSend() {
//                                        player.getAttributes().getJourneyController().checkJourney(-7837366150055375731L,1);
                                player.getAttributes().getJourneyController().checkJourney(npcId, 1, JourneyStepType.DIALOG);
                            }
                        })
                        .build());
                break;
            case 1328:
                player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                        .addNpcChat("Trader Stan", npcId, "Hello, and welcome to my travelling shop!", "I trade exotic wares from my travels for Jewels.", "If you don't find something you like come back next time.", "I've always got something new!")
                        .addDialogueAction(new Dialog() {
                            @Override
                            public void onSend() {
                                player.getAttributes().getAchievementController().completeAchievement(-730417261381203336L);
                            }
                        })
                        .build());
                break;
            case 306:
                player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                        .addNpcChat("Journey Guide", npcId,
                                "Hello there, what can I do for you?"
                        )
                        .addOptions(getJourneyOptions())
                        .build());
                break;
            case 1039:
                player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                        .addNpcChat("Jewel Merchant", npcId,
                                "Greetings adventurer, my name is Fareed.",
                                "I am a merchant. I offer many valuable goods",
                                "that will help you on your adventures. However",
                                "I only accept Jewels as payment for my exclusive goods."
                        )
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
                        .addNpcChat("Trader Crewmember", npcId, "Greetings! Where can I take you?")
                        .addOptions(new DialogOption("Show me your destinations") {
                                        @Override
                                        public void onAction() {
                                            player.getAttributes().getActiveDialogSequence().next();
                                            player.sendUI(new CharterUI(player));

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

    private DialogOption[] getJourneyOptions() {
        return new DialogOption[]{
                new DialogOption("Tell me about journeys") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addNpcChat("Journey Guide", npcId,
                                        "I'd love to! Journeys are a great way to",
                                        "learn more about the world around you.",
                                        "You start by selecting a Path the path",
                                        "you choose will unlock a series of journeys."
                                )
                                .addNpcChat("Journey Guide", npcId,
                                        "You will be able to do any journey",
                                        "on the path you unlocked as long as you",
                                        "have done all of the pre-requisite journeys.",
                                        "Journeys are very linear and must be done"
                                )
                                .addNpcChat("Journey Guide", npcId,
                                        "step-by-step to progress through.",
                                        "Upon completing a step you will be able",
                                        "to claim rewards for that step. Journeys",
                                        "may vary in length and difficulty."
                                )
                                .addNpcChat("Journey Guide", npcId,
                                        "Be mindful when you start a",
                                        "journey as you will not be able to",
                                        "cancel it or change it until you",
                                        "have completed it."
                                )
                                .build());
                    }
                },
                new DialogOption("I'd like to start a journey") {
                    @Override
                    public void onAction() {
                        player.sendUI(new JourneySelectionUI(player));
                    }
                },
                new DialogOption("Nevermind") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addNpcChat("Journey Guide", npcId,
                                        "Have a nice day."
                                )
                                .build());
                    }
                }
        };
    }

    public FirstClickDialogueAction(Player player, int npcX, int npcY, int npcId, int npcIndex) {
        super(player, npcX, npcY, npcId, npcIndex);
    }
}
