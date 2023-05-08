package ethos.runehub.entity.player.action.impl.npc;

import ethos.model.players.Player;
import ethos.runehub.content.job.Job;
import ethos.runehub.content.job.JobUtils;
import ethos.runehub.dialog.Dialog;
import ethos.runehub.dialog.DialogOption;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.entity.merchant.MerchantCache;
import ethos.runehub.entity.player.action.impl.ClickNPCAction;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.util.SkillDictionary;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FirstClickHeadChefAction extends ClickNPCAction {

    private static final int ID = 2658;
    private static final String NAME = "Head Chef";

    @Override
    public void perform() {
        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat(NAME, ID, "Welcome to my kitchen, what can I do for you?")
                .addOptions(this.getHelpOptions())
                .setMovementRestricted(false)
                .setActionLocked(false)
                .build());
    }

    private DialogSequence getJobDialogSequence() {
        return new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat(NAME, ID, "Gladly, my catering business is very busy.", "I'll need you to help me cook orders", "so I can have them delivered on time.")
                .addNpcChat(NAME, ID, "Upon successfully completing a job I will award you with", "payment and some XP for your work. The more jobs you",
                        "complete successfully the higher your score will be.", "A higher score equals higher pay.")
                .addNpcChat(NAME, ID,
                        "The base pay of a job and the amount it will",
                        "impact your score is based on its difficulty",
                        "more difficult jobs are better. Job difficulty",
                        "is determined by the material you need and amount."
                )
                .addNpcChat(NAME, ID,
                        "To increase your chances of receiving",
                        "a higher difficulty job make sure your level",
                        "in the related skill is higher, and your score",
                        "is higher."
                )
                .addNpcChat(NAME, ID,
                        "Base pay is your minimum payment this",
                        "increases with difficulty. You will also",
                        "receive payment based on the jobs quota."
                )
                .addNpcChat(NAME, ID, "You can cancel a job at anytime,", "but be warned cancelling will", "reset your streak and lower", "your job score.")
                .addNpcChat(NAME, ID, "Just between you and me...", "My kitchen is very well stocked.", "I wouldn't mind sharing ingredients with", "an aspiring chef, for the right price.")
                .addNpcChat(NAME, ID, "Prove to me you're a worthy chef", "by completing 5 difficulty 3 jobs for me", "and I will sell you supplies.")
                .addDialogueAction(new Dialog() {
                    @Override
                    public void onSend() {

                    }
                })
                .build();
    }

    private String getScorePercent() {
        double val = player.getContext().getPlayerSaveData().getJobScore() * 100.D;
        DecimalFormat df = new DecimalFormat("###.##");

        return df.format(val) + "%";
    }

    private DialogSequence getJobAssignmentDialogSequence() {
        Job job = JobUtils.generateCookingJob(player.getSkillController().getLevel(SkillDictionary.Skill.COOKING.getId()), SkillDictionary.Skill.COOKING.getId(), player.getContext().getPlayerSaveData().getJobScore());
        if (player.getContext().getPlayerSaveData().getActiveJob() != 0L) {
            return new DialogSequence.DialogSequenceBuilder(player)
                    .addNpcChat(NAME, ID, "It looks like you've already got a job...", "cancel it or complete it before trying to", "take a new job.")
                    .setMovementRestricted(false)
                    .setActionLocked(false)
                    .build();
        }
        return new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat(NAME, ID,
                        "Your current score is " + this.getScorePercent() + " that's " + JobUtils.getJobScoreString(player.getContext().getPlayerSaveData().getJobScore()),
                        "Go and bring me " + NumberFormat.getInstance().format(job.getQuota()) + " " + ItemIdContextLoader.getInstance().read(job.getTargetId()).getName(),
                        "I'll give you a base pay of " + NumberFormat.getInstance().format(job.getBasePay()) + " coins."
                )
                .addDialogueAction(new Dialog() {
                    @Override
                    public void onSend() {
                        player.getContext().getPlayerSaveData().setActiveJob(job.toBitArray());
                    }
                })
                .build();
    }

    private DialogSequence getTurnInItemsDialogSequence() {
        return new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat(NAME, ID,
                        "Great! Please give them to me. I accept",
                        "both noted and un-noted forms."
                )
                .build();
    }

    private DialogSequence getCancellationSequence() {
        return new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat(NAME, ID, "You'd like to cancel on me?", "Very well but I don't take kindly", "to cancellations.")
                .addOptions(new DialogOption("Cancel") {
                                @Override
                                public void onAction() {
                                    player.getAttributes().getJobController().cancelJob();
                                }
                            },
                        new DialogOption("Nevermind") {
                            @Override
                            public void onAction() {
                                player.getAttributes().getActiveDialogSequence().next();
                                player.getPA().closeAllWindows();
                            }
                        })
                .build();
    }

    private DialogSequence getTurnInSequence() {
        return new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat(NAME, ID, "Great! Please just place any items","you have for me on my table.")
                .build();
    }

    private DialogSequence getShopSequence() {
        int level3JobsCompleted = player.getContext().getPlayerSaveData().getJobTypeCompleted()[7][3];
        if (level3JobsCompleted >= 5) {
            return new DialogSequence.DialogSequenceBuilder(player)
                    .addNpcChat(NAME, ID, "A worthy chef! I'm sure you'll find what you need.")
                    .addDialogueAction(new Dialog() {
                        @Override
                        public void onSend() {
                            MerchantCache.getInstance().read(ID).openShop(player);
                        }
                    })
                    .build();
        }
        return new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat(NAME, ID, "You are not a worthy chef.","Come back when you've completed at least","5 level 3 difficulty cooking jobs.")
                .build();
    }

    private DialogOption[] getJobOptions() {
        return new DialogOption[]{
                new DialogOption("I'd like a job") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(getJobAssignmentDialogSequence());
                    }
                },
                new DialogOption("I'd like to cancel my job") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(getCancellationSequence());
                    }
                },
                new DialogOption("I've got items to turn in") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(getTurnInSequence());
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

    private DialogOption[] getHelpOptions() {
        return new DialogOption[]{
                new DialogOption("Teach me about jobs") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(getJobDialogSequence());
                    }
                },
                new DialogOption("Job Management") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(getJobOptions())
                                .setMovementRestricted(false)
                                .setActionLocked(false)
                                .build());
                    }
                },
                new DialogOption("I'd like to see your shop") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(getShopSequence());
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

    public FirstClickHeadChefAction(Player player, int npcX, int npcY, int npcId, int npcIndex) {
        super(player, npcX, npcY, npcId, npcIndex);
    }
}
