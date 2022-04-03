package ethos.runehub.action.item;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.model.multiplayer_session.MultiplayerSessionType;
import ethos.model.multiplayer_session.duel.DuelSession;
import ethos.model.multiplayer_session.duel.DuelSessionRules;
import ethos.model.players.Player;
import ethos.util.PreconditionUtils;

import java.util.Objects;

public abstract class ClickPotionConsumableAction extends ClickConsumableItemAction {

    protected abstract void applyEffect();

    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(829);
    }

    @Override
    protected void checkPrerequisites() {
        try {
            if (Objects.nonNull(session))
                Preconditions.checkArgument(PreconditionUtils.isFalse(session.getRules().contains(DuelSessionRules.Rule.NO_DRINKS)), "Drinks have been disabled for this duel.");
            Preconditions.checkArgument(this.getActor().isDead);
            Preconditions.checkArgument(System.currentTimeMillis() - this.getActor().lastSpear < 3000, "You are stunned and can not drink!");
            Preconditions.checkArgument(this.getActor().potionTimer.elapsed() > 1200);
            this.playerHasItemPrerequisite();
        } catch (Exception e) {
            this.getActor().sendMessage(e.getMessage());
        }
    }

    @Override
    protected void consume() {
        this.consumeDose();
        this.applyEffect();
    }

    private void consumeDose() {
        this.getActor().getItems().deleteItem(this.getItemId(),1,this.getItemSlot());
        if (this.getItemId() == fourDoseId) {
            this.getActor().getItems().addItem(threeDoseId,1);
        } else if (this.getItemId() == threeDoseId) {
            this.getActor().getItems().addItem(twoDoseId,1);
        } else if (this.getItemId() == twoDoseId) {
            this.getActor().getItems().addItem(oneDoseId,1);
        }
        this.getActor().getItems().resetItems(3214);
    }

    public ClickPotionConsumableAction(Player player, int ticks, int itemId, int itemAmount, int itemSlot, int fourDoseId, int threeDoseId, int twoDoseId, int oneDoseId) {
        super(player, ticks, itemId, itemAmount, itemSlot);
        this.session = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(player, MultiplayerSessionType.DUEL);
        this.fourDoseId = fourDoseId;
        this.threeDoseId = threeDoseId;
        this.twoDoseId = twoDoseId;
        this.oneDoseId = oneDoseId;
    }

    public ClickPotionConsumableAction(Player player, int itemId, int itemAmount, int itemSlot, int fourDoseId, int threeDoseId, int twoDoseId, int oneDoseId) {
        this(player,2,itemId,itemAmount,itemSlot,fourDoseId,threeDoseId,twoDoseId,oneDoseId);
    }

    private final DuelSession session;
    private final int fourDoseId, threeDoseId, twoDoseId, oneDoseId;
}
