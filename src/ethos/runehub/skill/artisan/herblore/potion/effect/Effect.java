package ethos.runehub.skill.artisan.herblore.potion.effect;

import ethos.model.players.Player;

public abstract class Effect {

    public abstract void doEffectOnPlayer(Player player);

    public int getId() {
        return id;
    }

    public Effect(int id) {
        this.id = id;
    }

    private final int id;
}
