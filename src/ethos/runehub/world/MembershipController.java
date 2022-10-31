package ethos.runehub.world;

import ethos.model.players.Player;
import ethos.runehub.db.PlayerCharacterContextDataAccessObject;
import ethos.runehub.entity.player.PlayerCharacterContext;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.logging.Logger;

public class MembershipController {

    private static MembershipController instance = null;

    public static MembershipController getInstance() {
        if (instance == null)
            instance = new MembershipController();
        return instance;
    }

    public void updateMembership(Player player) {
        this.updateMembership(player.getContext());
        player.save();
    }

    public void updateMembership(PlayerCharacterContext player) {
        if (this.getDaysOfMembershipRemaining(player) <= 0) {
            player.getPlayerSaveData().setMembershipDurationMS(0);
            player.getPlayerSaveData().setMembershipStartDateMS(0);
        }
        PlayerCharacterContextDataAccessObject.getInstance().update(player);
    }

    public long getDaysOfMembershipRemaining(PlayerCharacterContext ctx) {
        try {
            final ZonedDateTime membershipStart = ZonedDateTime.ofInstant(Instant.ofEpochMilli(ctx.getPlayerSaveData().getMembershipStartDateMS()), ZoneId.of("UTC"));
            final ZonedDateTime membershipEnd = ZonedDateTime.ofInstant(Instant.ofEpochMilli(ctx.getPlayerSaveData().getMembershipStartDateMS() + ctx.getPlayerSaveData().getMembershipDurationMS()), ZoneId.of("UTC"));
            final ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
            final Duration remainingMembership = Duration.between(now,membershipEnd);
            return remainingMembership.toMillis();
        } catch (IllegalArgumentException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
        return 0L;
    }


    public String getDaysOfMembershipRemainingAsString(PlayerCharacterContext ctx) {
        try {
            return DurationFormatUtils.formatDuration(this.getDaysOfMembershipRemaining(ctx), "dd");
        } catch (IllegalArgumentException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
        return "0";
    }

    private MembershipController(){
    }
}
