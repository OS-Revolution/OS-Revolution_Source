package ethos.runehub.ui.event.action.impl;

import ethos.runehub.ui.event.action.ActionEvent;
import ethos.runehub.ui.event.action.ActionListener;

import java.util.logging.Logger;

public class SystemActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Logger.getGlobal().fine("Action logged from source ID: " + actionEvent.getSource().getLineId());
    }
}
