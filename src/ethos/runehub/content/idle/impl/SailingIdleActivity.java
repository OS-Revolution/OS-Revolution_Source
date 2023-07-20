package ethos.runehub.content.idle.impl;

import ethos.runehub.content.idle.IdleActivity;

public class SailingIdleActivity extends IdleActivity {

    @Override
    public void onOfflineCompletion() {

    }

    @Override
    public void onOnlineCompletion() {

    }

    public SailingIdleActivity(long ownerId, long duration, int shipSlot) {
        super(ownerId, duration);
        this.slot = shipSlot;
    }

    private final int slot;

}
