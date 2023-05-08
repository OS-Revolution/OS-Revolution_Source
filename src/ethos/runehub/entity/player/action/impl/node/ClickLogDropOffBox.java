package ethos.runehub.entity.player.action.impl.node;

import ethos.model.players.Player;
import ethos.runehub.content.job.Job;
import org.runehub.api.io.load.impl.ItemIdContextLoader;

public class ClickLogDropOffBox extends ClickNodeAction {


    @Override
    public void perform() {
        if (player.getContext().getPlayerSaveData().getActiveJob() != 0L) {
            final Job job = Job.fromBitArray(player.getContext().getPlayerSaveData().getActiveJob());
            if (job.getSkillId() == 8) {
                int targetId = job.getTargetId();
                int notedTargetId = ItemIdContextLoader.getInstance().read(targetId).getLinkedIdNoted();
                int quota = job.getQuota();
                if (player.getItems().playerHasItem(targetId) || player.getItems().playerHasItem(notedTargetId)) {
                    if (job.getQuota() > job.getCollected()) {
                        int amount = player.getItems().getItemAmount(targetId);
                        int amountNoted = player.getItems().getItemAmount(notedTargetId);
                        int remainingQuota = quota - job.getCollected();
                        // Subtract from the amount first
                        if (amount > 0) {
                            int amountToSubtract = Math.min(amount, remainingQuota);
                            player.getItems().deleteItem2(targetId, amountToSubtract);
                            remainingQuota -= amountToSubtract;
                        }
                        // Subtract from the notedAmount if needed
                        if (remainingQuota > 0 && amountNoted > 0) {
                            int amountToSubtract = Math.min(amountNoted, remainingQuota);
                            player.getItems().deleteItem2(notedTargetId, amountToSubtract);
                            remainingQuota -= amountToSubtract;
                        }
                        int collected = quota - remainingQuota;
                        player.getAttributes().getJobController().updateJobProgress(collected);
                        if (remainingQuota != 0) {
                            player.sendMessage("You have deposited " + (quota - remainingQuota) + " items. You need to deposit " + remainingQuota + " more items.");
                        }
                    } else {
                        player.sendMessage("You've collected enough for your current job.");
                    }
                } else {
                    player.sendMessage("You do not have anything to deposit.");
                }
            } else {
                player.sendMessage("This box is for logs only.");
            }
        } else {
            player.sendMessage("You are not currently on a job and have nothing you can deposit.");
        }
    }

    public ClickLogDropOffBox(Player player, int nodeX, int nodeY, int nodeId) {
        super(player, nodeX, nodeY, nodeId);
    }
}
