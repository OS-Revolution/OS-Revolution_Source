package ethos.runehub.ui.event.action;

import ethos.runehub.ui.component.UIComponent;
import ethos.runehub.ui.event.Event;

public class ActionEvent extends Event {

    public long getTimestamp() {
        return timestamp;
    }

    public ActionEvent(UIComponent source, int id) {
        super(source);
        this.id = id;
        this.timestamp = System.currentTimeMillis();
    }

    private final int id;
    private final long timestamp;
}
