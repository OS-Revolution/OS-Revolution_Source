package ethos.runehub.dialog;

import java.util.ArrayList;
import java.util.List;

public abstract class Dialog {

    public abstract void onSend();

    public List<DialogOption> getOptions() {
        return options;
    }

    public Dialog() {
        this.options = new ArrayList<>();
    }

    public Dialog(DialogOption[] options) {
        this.options = List.of(options);
    }

    private final List<DialogOption> options;
}
