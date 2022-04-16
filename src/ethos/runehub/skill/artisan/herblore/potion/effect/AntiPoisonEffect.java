package ethos.runehub.skill.artisan.herblore.potion.effect;

import ethos.model.entity.HealthStatus;
import ethos.model.players.ClientGameTimer;
import ethos.model.players.Player;

import java.util.concurrent.TimeUnit;

public class AntiPoisonEffect extends Effect{

    public AntiPoisonEffect(int id, int durationInSeconds) {
        super(id);
        this.durationInSeconds = durationInSeconds;
    }

    @Override
    public void doEffectOnPlayer(Player player) {
        player.getHealth().resolveStatus(HealthStatus.POISON, durationInSeconds);
        player.getPA().sendGameTimer(ClientGameTimer.ANTIPOISON, TimeUnit.SECONDS, durationInSeconds);
        player.getPA().requestUpdates();
    }

    private final int durationInSeconds;
}
