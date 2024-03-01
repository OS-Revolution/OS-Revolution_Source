package ethos.runehub.skill.support.firemaking.action;

import ethos.Server;
import ethos.event.Event;
import ethos.model.items.GroundItem;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.event.item.CreateGroundItemEvent;
import org.runehub.api.model.world.region.location.Location;

public class FireBurningAction extends Event<ItemInteractionContext> {
    @Override
    public void execute() {
//        System.out.println("Spawning ashes: X: " + attachment.getX() + " Y: " + attachment.getY() + " Z: " + attachment.getZ());
//        Server.itemHandler.createGlobalItem(new GroundItem(592, attachment.getX(), attachment.getY(), attachment.getZ(), 1,5,""));
//        Server.itemHandler.createUnownedGroundItem(592,attachment.getX(), attachment.getY(), attachment.getZ(), 1);
        Location location = new Location(attachment.getX(),attachment.getY(),attachment.getZ());
        GroundItem groundItem = new GroundItem(592, attachment.getX(), attachment.getY(), attachment.getZ(), 1,5,"");
        Server.getEventHandler().submit(new CreateGroundItemEvent(groundItem,location,null));
        this.stop();
    }

    public FireBurningAction(ItemInteractionContext attachment, int ticks) {
        super(attachment, ticks);
    }
}
