package ethos.runehub.ui;

import ethos.Server;
import ethos.event.Event;
import ethos.model.players.Player;

public abstract class TickableUI extends GameUI {

    public abstract void draw();

    @Override
    protected void onClose() {
        Server.getEventHandler().stop("ui-tick");
    }

    public Event<TickableUI> onTick() {
        return new Event<>("ui-tick",this,4) {
            @Override
            public void execute() {
                attachment.
                        draw();
            }
        };
    }

    public TickableUI(int id, Player player) {
        super(id, player);
        Server.getEventHandler().submit(onTick());
    }
}
