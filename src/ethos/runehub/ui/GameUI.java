package ethos.runehub.ui;

import ethos.model.players.Player;
import ethos.runehub.ui.component.button.Button;
import ethos.runehub.ui.component.impl.TextComponent;
import ethos.runehub.ui.event.action.ActionEvent;
import ethos.runehub.ui.event.action.ActionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class GameUI {

    protected abstract void onOpen();

    protected abstract void onClose();

//    protected abstract void onAction(int buttonId);

    protected abstract void onEvent();

    public void open() {
        this.onOpen();
        this.setShowing(true);
        this.setState(State.ACTIVE);
        player.getAttributes().setActiveUI(this);
        player.getPA().showInterface(id);
        if (walkable) {
            player.getPA().walkableInterface(id);
        }

    }

    public void close() {
        this.onClose();
        player.getPA().closeUI();
        player.getAttributes().setActiveUI(null);
        this.setShowing(false);
        this.setState(State.CANCELLED);
        this.buttonIds.forEach(integer -> this.getPlayer().getAttributes().getActionDispatcher().remove(integer));
    }

    public void action(int buttonId) {
        Logger.getGlobal().fine("Button Press on UI: " + id + " - ActionID: " + buttonId);
        this.onAction(buttonId);
    }

    protected void onAction(int buttonId) {
        this.getPlayer().getAttributes().getActionDispatcher().dispatch(buttonId);
    }

    protected void writeLine(String text, int line) {
        player.getPA().sendFrame126(text, line);
    }

    protected void writeLine(TextComponent component) {
        player.getPA().sendFrame126(component.getText(), component.getLineId());
    }

    protected void registerButton(ActionListener actionListener, int id) {
        this.buttonIds.add(id);
        this.getPlayer().getAttributes().getActionDispatcher().registerButton(actionListener, id);
    }

    protected void registerButton(Button button) {
        this.buttonIds.add(button.getLineId());
        this.getPlayer().getAttributes().getActionDispatcher().registerButton(button);
    }

    public Player getPlayer() {
        return player;
    }

    public int getId() {
        return id;
    }

    public boolean isClosable() {
        return closable;
    }

    public boolean isShowing() {
        return showing;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setClosable(boolean closable) {
        this.closable = closable;
    }

    public void setShowing(boolean showing) {
        this.showing = showing;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public GameUI(int id, Player player) {
        this.id = id;
        this.player = player;
        this.buttonIds = new ArrayList<>();
    }

    private boolean showing, walkable, closable = true;
    private final Player player;
    private final int id;
    private State state;
    private final List<Integer> buttonIds;

    public enum State {
        ACTIVE, COMPLETED, CANCELLED;
    }

    public class UICloseActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            close();
        }
    }

}
