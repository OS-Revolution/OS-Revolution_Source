package ethos.runehub.action.click.item.consumable;

import ethos.model.players.Player;
import ethos.runehub.TimeUtils;
import ethos.runehub.action.click.item.ClickConsumableItemAction;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.time.Duration;
import java.time.LocalDate;

public class MembershipBond extends ClickConsumableItemAction {


    @Override
    protected void consume() {
        final long currentMembershipDuration = this.getActor().getContext().getPlayerSaveData().getMembershipDurationMS();
        if (currentMembershipDuration == 0) {
            this.getActor().getContext().getPlayerSaveData().setMembershipStartDateMS(System.currentTimeMillis());
            this.getActor().getContext().getPlayerSaveData().setMembershipDurationMS(Duration.ofDays(this.getDurationInDays(this.getItemId())).toMillis());
        } else {
            this.getActor().getContext().getPlayerSaveData().setMembershipDurationMS(currentMembershipDuration + Duration.ofDays(this.getDurationInDays(this.getItemId())).toMillis());
        }
        final long daysRemaining =
                 Duration.ofDays((this.getActor().getContext().getPlayerSaveData().getMembershipStartDateMS() + this.getActor().getContext().getPlayerSaveData().getMembershipDurationMS()) -
                        System.currentTimeMillis()).toDays();
        this.getActor().sendMessage("^Membership You've redeemed your @" + getItemId() + " for $" + this.getDurationInDays(this.getItemId()) + " days of membership.");
        this.getActor().sendMessage("^Membership You now have $" + DurationFormatUtils.formatDuration(daysRemaining, "dd") + " of membership.");
    }

    private int getDurationInDays(int itemId) {
        switch (itemId) {
            case 13190:
                return 30;
            case 13192:
                return 7;
            default:return 30;
        }
    }

    public MembershipBond(Player attachment,int itemId, int itemAmount, int itemSlot) {
        super(attachment, 1, itemId, itemAmount, itemSlot);
    }
}
