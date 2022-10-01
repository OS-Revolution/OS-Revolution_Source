package ethos.model.players.combat.specials;

import ethos.model.entity.Entity;
import ethos.model.players.Player;
import ethos.model.players.combat.Damage;
import ethos.model.players.combat.Special;

public class FeatherFan extends Special {
    /**
     * Creates a new special attack for a particular weapon or set of weapons
     *
     * @param cost           the cost of {@link Player#specAmount} when activated
     * @param accuracy       the accuracy of the weapon
     * @param damageModifier the amount that any and all damage will be modified by
     * @param weapon         the weapons that activate this special
     */
    public FeatherFan() {
        super(10.0, 1.0, 1.0, new int[]{2134});
    }

    @Override
    public void activate(Player player, Entity target, Damage damage) {

    }

    @Override
    public void hit(Player player, Entity target, Damage damage) {

    }
}
