package ethos.runehub.markup;

public class HighlightableText {

    public String getText() {
        return highlighted ?  highlightColor + text : defaultColor + text;
    }

    public String getDefaultColor() {
        return defaultColor;
    }

    public String getHighlightColor() {
        return highlightColor;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDefaultColor(String defaultColor) {
        this.defaultColor = defaultColor;
    }

    public void setHighlightColor(String highlightColor) {
        this.highlightColor = highlightColor;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    private String text;
    private String defaultColor;
    private String highlightColor;
    private boolean highlighted;
}
