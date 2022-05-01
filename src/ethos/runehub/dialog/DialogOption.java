package ethos.runehub.dialog;

public class DialogOption {

//    public DialogSequence getSequence() {
//        return sequence;
//    }

    public String getOptionText() {
        return optionText;
    }

//    public int getId() {
//        return id;
//    }

    public DialogOption(String optionText) {
//        this.id = id;
        this.optionText = optionText;
//        this.sequence = sequence;
    }

//    private final int id;
//    private final DialogSequence sequence;
    private final String optionText;
}
