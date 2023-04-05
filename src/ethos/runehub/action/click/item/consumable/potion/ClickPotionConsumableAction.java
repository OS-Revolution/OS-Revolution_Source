package ethos.runehub.action.click.item.consumable.potion;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.model.multiplayer_session.MultiplayerSessionType;
import ethos.model.multiplayer_session.duel.DuelSession;
import ethos.model.multiplayer_session.duel.DuelSessionRules;
import ethos.model.players.Player;
import ethos.runehub.action.click.item.ClickConsumableItemAction;
import ethos.runehub.skill.artisan.herblore.potion.Potion;
import ethos.runehub.skill.artisan.herblore.potion.PotionEffectFactory;
import ethos.runehub.skill.artisan.herblore.potion.PotionFactory;
import ethos.runehub.skill.artisan.herblore.potion.effect.Effect;
import ethos.util.PreconditionUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;

public class ClickPotionConsumableAction extends ClickConsumableItemAction {

    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(829);
    }

    @Override
    protected boolean checkPrerequisites() {
        try {
            if (Objects.nonNull(session))
                Preconditions.checkArgument(PreconditionUtils.isFalse(session.getRules().contains(DuelSessionRules.Rule.NO_DRINKS)), "Drinks have been disabled for this duel.");
            Preconditions.checkArgument(PreconditionUtils.isFalse(this.getActor().isDead));
            Preconditions.checkArgument(PreconditionUtils.isFalse(System.currentTimeMillis() - this.getActor().lastSpear < 3000), "You are stunned and can not drink!");
            Preconditions.checkArgument(this.getActor().potionTimer.elapsed() > 1200);
            this.playerHasItemPrerequisite();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            this.getActor().sendMessage(e.getMessage());
        }
        return false;
    }

    @Override
    protected void consume() {
        this.consumeDose();
        this.applyEffect();
//        this.stop();
    }

    protected void applyEffect() {
        Arrays.stream(this.getPotion().getEffectId()).forEach(value -> {
            Effect effect = PotionEffectFactory.getInstance().read(value);
            effect.doEffectOnPlayer(this.getActor());
        });
    }

    protected Potion getPotion() {
        return potion;
    }

    private void consumeDose() {
        Logger.getGlobal().fine("Potion ID: " + this.getItemId());
        this.getActor().getItems().deleteItem(this.getItemId(), this.getItemSlot(), 1);
        if (this.getItemId() == potion.getFourDoseId()) {
            Logger.getGlobal().fine("4 Dose Potion ID: " + this.getItemId());
            this.getActor().getItems().addItem(potion.getThreeDoseId(), 1);
        } else if (this.getItemId() == potion.getThreeDoseId()) {
            Logger.getGlobal().fine("3 Dose Potion ID: " + this.getItemId());
            this.getActor().getItems().addItem(potion.getTwoDoseId(), 1);
        } else if (this.getItemId() == potion.getTwoDoseId()) {
            Logger.getGlobal().fine("2 Dose Potion ID: " + this.getItemId());
            this.getActor().getItems().addItem(potion.getOneDoseId(), 1);
        } else if (this.getItemId() == potion.getOneDoseId()) {
            Logger.getGlobal().fine("1 Dose Potion ID: " + this.getItemId());
            this.getActor().getItems().addItem(229, 1);
        }
        this.getActor().getItems().resetItems(3214);
    }

    public ClickPotionConsumableAction(Player player, int ticks, int itemId, int itemAmount, int itemSlot) {
        super(player, ticks, itemId, itemAmount, itemSlot);
        this.session = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(player, MultiplayerSessionType.DUEL);
        this.potion = PotionFactory.getInstance().read(itemId);
    }

    public ClickPotionConsumableAction(Player player, int itemId, int itemAmount, int itemSlot) {
        this(player, 3, itemId, itemAmount, itemSlot);
    }

    private final DuelSession session;
    private final Potion potion;
}
