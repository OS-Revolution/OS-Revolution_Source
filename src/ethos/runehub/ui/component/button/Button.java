package ethos.runehub.ui.component.button;

import ethos.runehub.ui.component.UIComponent;
import ethos.runehub.ui.component.impl.TextComponent;
import ethos.runehub.ui.event.action.ActionEvent;
import ethos.runehub.ui.event.action.ActionListener;
import ethos.runehub.ui.event.action.impl.SystemActionListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Button extends UIComponent {

    public void onAction() {
        if (!actionListeners.isEmpty())
            actionListeners.forEach(actionListener -> actionListener.actionPerformed(new ActionEvent(this, this.getLineId())));
        else
            Logger.getGlobal().warning("No Action Listener registered.");
    }

    public void addActionListener(ActionListener actionListener) {
        actionListeners.add(actionListener);
    }

    public void removeActionListener(ActionListener actionListener) {
        actionListeners.remove(actionListener);
    }

    public Button(int lineId) {
        super(lineId);
        this.actionListeners = new ArrayList<>();
        actionListeners.add(new SystemActionListener());
    }

    public List<ActionListener> getActionListeners() {
        return actionListeners;
    }

    private List<ActionListener> actionListeners;
}
