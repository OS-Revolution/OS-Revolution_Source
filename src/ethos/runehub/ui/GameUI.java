package ethos.runehub.ui;

import ethos.model.players.Player;

import java.util.logging.Logger;

public abstract class GameUI {

    protected abstract void onOpen();

    protected abstract void onClose();

    protected abstract void onAction(int buttonId);

    protected abstract void onEvent();

    public void open() {
        this.onOpen();
        this.setShowing(true);
        this.setState(State.ACTIVE);
        player.getAttributes().setActiveUI(this);
        player.getPA().showInterface(id);
    }

    public void close() {
        this.onClose();
        player.getPA().closeAllWindows();
        player.getAttributes().setActiveUI(null);
        this.setShowing(false);
        this.setState(State.CANCELLED);
    }

    public void action(int buttonId) {
        Logger.getGlobal().fine("Button Press on UI: " + id + " - ActionID: " + buttonId);
        this.onAction(buttonId);
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
    }

    private boolean showing, walkable, closable = true;
    private final Player player;
    private final int id;
    private State state;

    public enum State {
        ACTIVE,COMPLETED,CANCELLED;
    }

}
