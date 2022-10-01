package ethos.runehub.ui;

import ethos.runehub.markup.MarkupUtils;
import ethos.runehub.ui.component.impl.TextComponent;

public class UIUtils {

    public static void highlightText(String highlight, TextComponent component) {
        component.setText(MarkupUtils.highlightText(highlight,component.getText()));
    }

    public static void removeHighlight(TextComponent component) {
        component.setText(MarkupUtils.removeHighlightFromText(component.getText()));
    }
}
