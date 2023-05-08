package ethos.runehub.content.job;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;

import java.text.DecimalFormat;

public class JobController {

    public void updateJobProgress(int amount) {
        final Job job = Job.fromBitArray(player.getContext().getPlayerSaveData().getActiveJob());
        job.setCollected(amount);

        if (job.getCollected() == job.getQuota()) {
            int payment = job.getBasePay() + JobUtils.getBonusPay(job);
            int xp = JobUtils.getXpReward(job);
            if (player.getAttributes().isMember()) {
                xp += (int) Math.round(xp * 0.15);
            }
            player.sendMessage("^Job Job Complete! You've completed " + (player.getContext().getPlayerSaveData().getJobTypeCompleted()[job.getSkillId()][job.getDifficulty()] + 1)
                    + " level "
                    + job.getDifficulty()
                    + " "
                    + RunehubUtils.getSkillName(job.getSkillId())
                    + " jobs."
            );
            player.getItems().addItemUnderAnyCircumstance(995,payment);
            player.getSkillController().addImmutableXP(job.getSkillId(),xp);
            player.getContext().getPlayerSaveData().setActiveJob(0L);
            player.getContext().getPlayerSaveData().setJobsCompleted(player.getContext().getPlayerSaveData().getJobsCompleted() + 1);
            player.getContext().getPlayerSaveData().setJobStreak(player.getContext().getPlayerSaveData().getJobStreak() + 1);
            player.getContext().getPlayerSaveData().addCompletedJobType(job.getSkillId(), job.getDifficulty());
            player.getContext().getPlayerSaveData().setJobScore(
                    Math.min(1.0f, player.getContext().getPlayerSaveData().getJobScore() + JobUtils.getJobScoreBonus(job.getDifficulty(),job.getSkillId(),player.getContext().getPlayerSaveData().getJobStreak())));
            player.sendMessage("^Job Your new score is " + this.getScorePercent() + " and your streak is " + player.getContext().getPlayerSaveData().getJobStreak() + ".");
        } else {
            player.getContext().getPlayerSaveData().setActiveJob(job.toBitArray());
        }
    }

    private String getScorePercent() {
        double val = player.getContext().getPlayerSaveData().getJobScore() * 100.D;
        DecimalFormat df = new DecimalFormat("###.##");

        return df.format(val) + "%";
    }

    public void cancelJob() {
        if (player.getContext().getPlayerSaveData().getActiveJob() > 0L) {
            final Job job = Job.fromBitArray(player.getContext().getPlayerSaveData().getActiveJob());
            player.getContext().getPlayerSaveData().setActiveJob(0L);
            player.getContext().getPlayerSaveData().setJobStreak(0);
            player.getContext().getPlayerSaveData().setJobScore(
                    Math.max(0f, player.getContext().getPlayerSaveData().getJobScore() - JobUtils.getJobScoreBonus(job.getDifficulty(),job.getSkillId(),player.getContext().getPlayerSaveData().getJobStreak())));
            player.getContext().getPlayerSaveData().setJobsAbandoned(player.getContext().getPlayerSaveData().getJobsAbandoned() + 1);
        }
    }

    public JobController(Player player) {
        this.player = player;
    }

    private final Player player;
}
