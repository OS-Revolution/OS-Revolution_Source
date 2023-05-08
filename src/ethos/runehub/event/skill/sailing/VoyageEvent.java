package ethos.runehub.event.skill.sailing;

import ethos.event.Event;
import ethos.model.players.Player;
import ethos.runehub.TimeUtils;

public class VoyageEvent extends Event<Player> {

    @Override
    public void execute() {
        System.out.println("Completing Voyage");
        this.getAttachment().getSkillController().getSailing2().getShipController(slot).completeVoyage();
        this.stop();
    }

    public VoyageEvent(Player player, long duration, int slot) {
        super("voyage_ship-" + slot, player, Math.toIntExact(duration / 600));
        this.slot = slot;
        System.out.println("Voyage Event Created"
                + "\nVoyage started with duration of " + TimeUtils.getDurationString(duration)
                + "\nTicks: " + (Math.toIntExact(duration / 600))
        );
    }

    private final int slot;
}
