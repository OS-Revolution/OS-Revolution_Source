package ethos.runehub.skill.artisan.herblore.potion.effect;

import ethos.model.players.Player;

public class EnergyRestoreEffect extends Effect{

    @Override
    public void doEffectOnPlayer(Player player) {
        int total = player.getRunEnergy() + baseIncrease;
        if (total <= 10000) {
            player.setRunEnergy(total);
        } else {
            player.setRunEnergy(10000);
        }
    }

    public EnergyRestoreEffect(int id, int baseIncrease) {
        super(id);
        this.baseIncrease = baseIncrease;
    }

    private final int baseIncrease;
}
