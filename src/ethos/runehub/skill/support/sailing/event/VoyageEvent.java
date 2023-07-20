package ethos.runehub.skill.support.sailing.event;

import ethos.event.Event;
import ethos.model.players.Player;
import ethos.runehub.TimeUtils;
import ethos.runehub.skill.support.sailing.SailingController;

public class VoyageEvent extends Event<Long> {

    @Override
    public void execute() {
//        this.getAttachment().getSkillController().getSailing2().getShipController(slot).completeVoyage();
        SailingController.getInstance().completeVoyage(slot,attachment);
        this.stop();
    }

    public VoyageEvent(Player player, long duration, int slot) {
        super("voyage_ship-" + slot +"-" + player.getId(), player.getId(), Math.toIntExact(duration / 600));
        this.slot = slot;
        System.out.println("Voyage Event Created"
                + "\nVoyage started with duration of " + TimeUtils.getDurationString(duration)
                + "\nTicks: " + (Math.toIntExact(duration / 600))
        );
    }

    private final int slot;
}
