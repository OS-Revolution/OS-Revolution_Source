package ethos.runehub.dialog;

import java.util.logging.Logger;

public abstract class DialogOption  {

    public abstract void onAction();

    public String getOptionText() {
        return optionText;
    }

    public DialogOption(String optionText) {
//        this.id = id;
        this.optionText = optionText;
    }

    private final String optionText;


}
