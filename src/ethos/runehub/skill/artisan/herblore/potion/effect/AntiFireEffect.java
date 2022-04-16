package ethos.runehub.skill.artisan.herblore.potion.effect;

import ethos.model.players.ClientGameTimer;
import ethos.model.players.Player;

import java.util.concurrent.TimeUnit;

public class AntiFireEffect extends Effect{

    public AntiFireEffect(int id, int durationInSeconds) {
        super(id);
        this.durationInSeconds = durationInSeconds;
    }

    @Override
    public void doEffectOnPlayer(Player player) {
        player.sendMessage("You now have resistance against dragon fire.");
        player.lastAntifirePotion = System.currentTimeMillis();
        player.antifireDelay = durationInSeconds * 1000L;
        player.getPA().sendGameTimer(ClientGameTimer.ANTIFIRE, TimeUnit.SECONDS, durationInSeconds);
    }

    private final int durationInSeconds;
}
