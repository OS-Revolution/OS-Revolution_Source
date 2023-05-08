package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.content.job.Job;
import ethos.runehub.content.job.JobUtils;
import ethos.runehub.skill.SkillUtils;
import org.runehub.api.io.load.impl.ItemIdContextLoader;

import java.text.NumberFormat;

public class JobUI extends JournalUI{

    @Override
    protected void onOpen() {
        this.clearUI();
        this.titleTextComponent.setText("Current Job");
        if (this.getPlayer().getContext().getPlayerSaveData().getActiveJob() == 0) {
            this.entryTextComponent[1].setText("@blu@Unemployed");
        } else {
            Job job = Job.fromBitArray(this.getPlayer().getContext().getPlayerSaveData().getActiveJob());
            this.entryTextComponent[1].setText("@blu@Collect: @bla@"
            + ItemIdContextLoader.getInstance().read(job.getTargetId()).getName());
            this.entryTextComponent[2].setText("@blu@Collected: @bla@"
                    + NumberFormat.getInstance().format(job.getCollected())
                    +"/"
                    + NumberFormat.getInstance().format(job.getQuota()));
            this.entryTextComponent[3].setText("@blu@Base Pay: @bla@" + NumberFormat.getInstance().format(job.getBasePay()) + " coins");
            this.entryTextComponent[4].setText("@blu@Difficulty: @bla@" + job.getDifficulty());
            this.entryTextComponent[5].setText("@blu@Skill: @bla@" + SkillUtils.getSkillName(job.getSkillId()));
            this.entryTextComponent[6].setText("@blu@Bonus Pay: @bla@" + NumberFormat.getInstance().format(JobUtils.getBonusPay(job)) + " coins");
            this.entryTextComponent[7].setText("@blu@XP Reward: @bla@" + NumberFormat.getInstance().format(JobUtils.getXpReward(job)));
        }
        this.updateUI();
    }

    public JobUI(Player player) {
        super(player);
    }
}
