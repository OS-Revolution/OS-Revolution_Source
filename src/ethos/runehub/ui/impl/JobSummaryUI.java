package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.content.job.Job;
import ethos.runehub.content.job.JobUtils;
import ethos.runehub.skill.SkillUtils;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.util.SkillDictionary;

import java.text.NumberFormat;

public class JobSummaryUI extends JournalUI {

    @Override
    protected void onOpen() {
        this.clearUI();
        this.titleTextComponent.setText("Job Summary");
        this.entryTextComponent[1].setText("@blu@Job Score: @bla@"
                + JobUtils.getScorePercent(this.getPlayer().getContext().getPlayerSaveData().getJobScore()));
        this.entryTextComponent[2].setText("@blu@Jobs Completed: @bla@"
                + NumberFormat.getInstance().format(this.getPlayer().getContext().getPlayerSaveData().getJobsCompleted()));
        this.entryTextComponent[3].setText("@blu@Job Streak: @bla@" + NumberFormat.getInstance().format(this.getPlayer().getContext().getPlayerSaveData().getJobStreak()));
        this.entryTextComponent[4].setText("@blu@Jobs Abandoned: @bla@" + NumberFormat.getInstance().format(this.getPlayer().getContext().getPlayerSaveData().getJobsAbandoned()));
        this.drawJobData();
        this.updateUI();
    }

    private void drawJobData() {
        int startingLine = 5;
            for (int skillId = startingLine; skillId < SkillDictionary.Skill.values().length; skillId++) {
                if (skillId != SkillDictionary.Skill.HUNTER.getId() && skillId != SkillDictionary.Skill.CONSTRUCTION.getId()) {
                    entryTextComponent[startingLine].setText("@blu@" + RunehubUtils.getSkillName(skillId));
                    entryTextComponent[startingLine + 1].setText(
                            "@bla@Easy: @red@" + NumberFormat.getInstance().format(this.getPlayer().getContext().getPlayerSaveData().getJobTypeCompleted()[skillId][0]) + " "
                                    + "@bla@Medium: @red@" + NumberFormat.getInstance().format(this.getPlayer().getContext().getPlayerSaveData().getJobTypeCompleted()[skillId][1]) + " "
                                    + "@bla@Hard: @red@" + NumberFormat.getInstance().format(this.getPlayer().getContext().getPlayerSaveData().getJobTypeCompleted()[skillId][2]) + " "
                                    + "@bla@Elite: @red@" + NumberFormat.getInstance().format(this.getPlayer().getContext().getPlayerSaveData().getJobTypeCompleted()[skillId][3]) + " "
                    );
                    startingLine += 2;
                }
            }
    }

    public JobSummaryUI(Player player) {
        super(player);
    }
}
