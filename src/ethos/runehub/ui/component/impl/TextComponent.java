package ethos.runehub.ui.component.impl;

import ethos.runehub.ui.component.UIComponent;

public class TextComponent extends UIComponent {

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextComponent(int lineId, String text) {
        super(lineId);
        this.text = text;
    }

    public TextComponent(int lineId) {
        this(lineId,"N/A");
    }

    private String text;
}
