package ethos.runehub;

import ethos.Server;
import ethos.model.players.ClientGameTimer;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WorldSettingsController {

    public static final String SAVE_LOCATION = "Data/rune-script/world-settings.json";

    private static WorldSettingsController instance = null;

    public static WorldSettingsController getInstance() {
        if (instance == null)
            instance = new WorldSettingsController();
        return instance;
    }

    public void saveSettings() {
        final WorldSettingsSerializer serializer = new WorldSettingsSerializer();
        serializer.write(new File(SAVE_LOCATION), serializer.serialize(worldSettings));
    }

    public void addBonusXp(Player player, int time) {
        PlayerHandler.executeGlobalMessage("@blu@[News]@red@ " + player.getName() + "@blu@ has activated a @red@" + time + "@blu@ hour XP boost for the server!");
        worldSettings.getBonusXpTimer().add((long) time * 60);
        PlayerHandler.getPlayers().forEach(p -> p.getPA().sendGameTimer(ClientGameTimer.EXPERIENCE, TimeUnit.MINUTES, Math.toIntExact(worldSettings.getBonusXpTimer().value())));
        this.saveSettings();
    }

    public void addMagicFind(Player player, int time) {
        PlayerHandler.executeGlobalMessage("@blu@[News]@red@ " + player.getName() + "@blu@ has activated a @red@" + time + "@blu@ hour Magic Find boost for the server!");
        worldSettings.getDoubleDropRateTimer().add((long) time * 60);
        PlayerHandler.getPlayers().forEach(p -> p.getPA().sendGameTimer(ClientGameTimer.DROPS, TimeUnit.MINUTES,Math.toIntExact(worldSettings.getDoubleDropRateTimer().value())));
        this.saveSettings();
    }

    private WorldSettingsController() {
        try {
            this.worldSettings = new WorldSettingsSerializer().read(new File(SAVE_LOCATION));
        } catch (IOException e) {
            this.worldSettings = new WorldSettings();
            this.saveSettings();
            Logger.getLogger("World Logger").severe("Failed to load world settings.");
        }
    }



    public WorldSettings getWorldSettings() {
        return worldSettings;
    }

    private WorldSettings worldSettings;
}
