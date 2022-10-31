package ethos.runehub.ui.component.impl;

import ethos.runehub.ui.component.UIComponent;

public class ProgressBarComponent extends UIComponent {

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public ProgressBarComponent(int lineId, int progress) {
        super(lineId);
        this.progress = progress;
    }

    public ProgressBarComponent(int lineId) {
        this(lineId,0);
    }

    private int progress;
}
