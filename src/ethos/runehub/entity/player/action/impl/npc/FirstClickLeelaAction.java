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

public class FirstClickLeelaAction extends ClickNPCAction {

    final String NAME = "Leela";
    final int ID = 4274;

    @Override
    public void perform() {
        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                .addOptions(getMainOptions())
                .setMovementRestricted(false)
                .setActionLocked(false)
                .build());
    }

    private DialogSequence getJobInfoDialogSequence() {
        return new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat(NAME,ID,
                        "My workshop is a busy place. I would",
                        "need your help smithing items too fill orders.",
                        "I'll pay you with Smithing XP and coins. The",
                        "more difficult the job the more I'll pay."
                        )
                .addNpcChat(NAME,ID,
                        "Successfully completing jobs will boost",
                        "your job score. Abandoning jobs will reduce it.",
                        "I offer access to my exclusive stash of items",
                        "to anyone who proves themselves a loyal smith."
                )
                .addNpcChat(NAME,ID,
                        "If you complete 10 tier 1 jobs for me",
                        "I'll let you have access."
                )
                .build();
    }

    private DialogSequence getUpgradeInfoDialogSequence() {
        return new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat(NAME,ID,
                        "Upgrading is a great way to improve your gear",
                        "without having to make it yourself. Head over to my",
                        "upgrade bench and any items you have on you that can",
                        "be upgraded will appear."
                )
                .addNpcChat(NAME,ID,
                        "Select the item you'd like to upgrade and",
                        "you will see useful information about the upgrade.",
                        "Upgrades cost PvM points although I'm not sure what",
                        "killing monsters has to do with upgrades..."
                )
                .addNpcChat(NAME,ID,
                        "Keep in mind a successful upgrade is far",
                        "from a guarantee. In most cases failing the",
                        "upgrade only results in a higher point cost and",
                        "a higher success rate. There are some instances"
                )
                .addNpcChat(NAME,ID,
                        "Where the item used will be destroyed.",
                        "To help prevent this you can train both",
                        "smithing and crafting to receive up-to a 50%",
                        "increased chance for each."
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
                .addNpcChat(NAME, ID, "Great! Use the items on me.")
                .build();
    }

    private String getScorePercent() {
        double val = player.getContext().getPlayerSaveData().getJobScore() * 100.D;
        DecimalFormat df = new DecimalFormat("###.##");

        return df.format(val) + "%";
    }

    private DialogSequence getJobAssignmentDialogSequence() {
        Job job = JobUtils.generateSmithingJob(player.getSkillController().getLevel(SkillDictionary.Skill.SMITHING.getId()), player.getContext().getPlayerSaveData().getJobScore());
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

    private DialogSequence getShopSequence() {
        int level1JobsCompleted = player.getContext().getPlayerSaveData().getJobTypeCompleted()[SkillDictionary.Skill.SMITHING.getId()][1];
        if (level1JobsCompleted >= 10) {
            return new DialogSequence.DialogSequenceBuilder(player)
                    .addNpcChat(NAME, ID, "An experienced smith! I'm sure you'll appreciate these")
                    .addDialogueAction(new Dialog() {
                        @Override
                        public void onSend() {
                            MerchantCache.getInstance().read(ID).openShop(player);
                        }
                    })
                    .build();
        }
        return new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat(NAME, ID, "You are not an experienced smith.","Come back when you've completed at least","10 level 1 difficulty smithing jobs.")
                .build();
    }

    private DialogOption[] getMainOptions() {
        return new DialogOption[]{
                new DialogOption("Tell me about upgrading") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(getUpgradeInfoDialogSequence());
                    }
                },
                new DialogOption("Tell me about jobs") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(getJobInfoDialogSequence());
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

    public FirstClickLeelaAction(Player player, int npcX, int npcY, int npcId, int npcIndex) {
        super(player, npcX, npcY, npcId, npcIndex);
    }
}
