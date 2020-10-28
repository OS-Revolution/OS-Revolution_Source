package ethos.runehub.entity.player;

import ethos.runehub.db.PlayerCharacterContextDataAccessObject;
import org.rhd.api.action.Action;
import org.rhd.api.action.ActionScheduler;
import org.rhd.api.action.impl.interaction.Interactable;
import org.rhd.api.action.impl.interaction.Interaction;
import org.rhd.api.entity.user.character.CharacterEntityAttribute;
import org.rhd.api.entity.user.character.player.PlayerCharacterEntity;
import org.rhd.api.item.container.ItemContainer;

public class PlayerCharacter implements PlayerCharacterEntity {

    @Override
    public ItemContainer inventory() {
        return null;
    }

    @Override
    public PlayerCharacterContext getContext() {
        return context;
    }

    @Override
    public CharacterEntityAttribute getAttributes() {
        return attributes;
    }

    @Override
    public void perform(Action<?> action) {
        actionQueue.queue(action);
    }

    @Override
    public void interact(Interactable interactable) {
        actionQueue.queue(interactable.onInteraction());
    }

    @Override
    public Interaction onInteraction() {
        return null;
    }

    public PlayerCharacter(long id) {
        this.attributes = new PlayerCharacterAttribute(this);
        this.actionQueue = new ActionScheduler(1, 10);
        this.context = PlayerCharacterContextDataAccessObject.getInstance().read(id);
    }

    private final ActionScheduler actionQueue;
    private final PlayerCharacterAttribute attributes;
    private final PlayerCharacterContext context;
}
