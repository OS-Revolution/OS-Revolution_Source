package ethos.runehub.ui;

import ethos.runehub.ui.component.button.Button;
import ethos.runehub.ui.event.action.ActionListener;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ActionDispatcher {

    public void registerButton(ActionListener actionListener, int id) {
        Button button = new Button(id);
        button.addActionListener(actionListener);
        actionMap.put(id, button);
    }

    public void remove(int id) {
        actionMap.remove(id);
    }


    public void registerButton(Button button) {
        actionMap.put(button.getLineId(), button);
    }

    public void dispatch(int id) {
        if (actionMap.containsKey(id)) {
            actionMap.get(id).onAction();
        } else {
            Logger.getGlobal().warning("Button not Registered: " + id);
        }
    }

    public ActionDispatcher() {
        this.actionMap = new HashMap<>();
    }

    private final Map<Integer,Button> actionMap;
}
