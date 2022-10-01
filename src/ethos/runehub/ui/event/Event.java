package ethos.runehub.ui.event;

import ethos.runehub.ui.component.UIComponent;

public class Event {

    public UIComponent getSource() {
        return source;
    }

    public Event(UIComponent source) {
        this.source = source;
    }

    private final UIComponent source;
}
