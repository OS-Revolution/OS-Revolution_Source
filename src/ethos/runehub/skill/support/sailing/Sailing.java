package ethos.runehub.skill.support.sailing;

import ethos.model.players.Player;
import ethos.runehub.skill.support.SupportSkill;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
import ethos.runehub.skill.support.sailing.voyage.VoyageController;
import ethos.runehub.ui.impl.SailingUI;
import ethos.runehub.world.wushanko.island.IslandLoader;

import java.util.ArrayList;
import java.util.List;

public class Sailing extends SupportSkill {

    public static final int BASE_MAX_DAILY_VOYAGES = 5;
    public static final int BASE_DAILY_REROLLS = 3;
    public static final int SHIP_ID = 0;
    public static final int VOYAGE_ID = 1;
    public static final int REGION_ID = 2;
    public static final int ISLAND_ID = 3;
    public static final int START_TIMESTAMP = 0;
    public static final int END_TIMESTAMP = 1;
    public static final double BASE_KNOTS_PER_HOUR = 7.3D;
    public static final int BASE_HOURS_SAILED_PER_DAY = 12;
    public static final double BASE_KNOTS_PER_MS = BASE_KNOTS_PER_HOUR / 3600000;

    public void sendVoyageSelectionUI() {
        this.getPlayer().sendUI(new SailingUI(this.getPlayer(), this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages()));
    }

    public void updateVoyageController(int shipSlot, Voyage voyage) {
        voyageController.setShipSlot(shipSlot);
        voyageController.setVoyage(voyage);
    }

    public void generateDailyVoyages() {
        this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages().clear();
        if (this.getPlayer().getContext().getPlayerSaveData().isDailyAvailable()) {
            final List<Voyage> dailyVoyages = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                dailyVoyages.add(voyageController.generateVoyage());
            }
            this.getPlayer().getContext().getPlayerSaveData().setAvailableVoyages(dailyVoyages);
            this.getPlayer().save();
        }
    }

    public void startVoyage() {
        this.getPlayer().sendMessage("Staring Voyage...");
    }

    public void finishVoyage() {
        if (voyageController.completeVoyage()) {
            this.getPlayer().sendMessage("You've successfully completed the voyage!");
            voyageController.getLootFromVoyage().forEach(loot -> this.getPlayer().getItems().addItem(
                    (int) loot.getId(),
                    (int) loot.getAmount()
            ));
            voyageController.removeVoyage();
            voyageController.updatePlayerVoyageStats();
        } else if (voyageController.isShipOnVoyage()) {
            this.getPlayer().sendMessage("This ship has not returned yet.");
        } else if (voyageController.isSelectedShipOnSelectedVoyage()) {
            this.getPlayer().sendMessage("You can't complete a voyage you're not on. Your ship is heading for " + IslandLoader.getInstance().read(voyageController.getVoyage().getIsland()).getName());
        } else {
            this.getPlayer().sendMessage("Your ship failed to complete the voyage.");
        }
    }

    @Override
    public int getId() {
        return 23;
    }

    public Sailing(Player player) {
        super(player);
        this.voyageController = new VoyageController(player);
    }

    private final VoyageController voyageController;
}
