package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.ui.GameUI;
import ethos.runehub.ui.component.impl.TextComponent;

import java.util.Arrays;

public class JournalUI extends GameUI {


    @Override
    protected void onOpen() {
        this.clearUI();
        this.updateUI();
    }

    @Override
    protected void onClose() {
        this.clearUI();
    }

    @Override
    protected void onEvent() {

    }

    protected void clearUI() {
        titleTextComponent.setText("");
        Arrays.stream(entryTextComponent).forEach(textComponent -> textComponent.setText(""));
    }

    protected void updateUI() {
        this.writeLine(titleTextComponent);
        Arrays.stream(entryTextComponent).forEach(this::writeLine);
    }

    public JournalUI(Player player) {
        super(8134, player);
        this.titleTextComponent = new TextComponent(8144);
        this.entryTextComponent = new TextComponent[51];

        for (int i = 0; i < entryTextComponent.length; i++) {
            entryTextComponent[i] = new TextComponent(8146 + i);
        }

        this.registerButton(actionEvent -> close(),59229);
    }

    protected final TextComponent titleTextComponent;
    protected final TextComponent[] entryTextComponent;
}
