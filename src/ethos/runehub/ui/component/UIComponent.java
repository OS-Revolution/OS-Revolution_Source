package ethos.runehub.ui.component;

public class UIComponent {

    public int getLineId() {
        return lineId;
    }

    protected UIComponent(int lineId) {
        this.lineId = lineId;
    }

    private final int lineId;
}
