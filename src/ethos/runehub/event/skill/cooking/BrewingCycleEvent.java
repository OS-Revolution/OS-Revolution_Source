package ethos.runehub.event.skill.cooking;

import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.runehub.db.PlayerCharacterContextDataAccessObject;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.entity.player.PlayerCharacterContext;
import ethos.runehub.entity.player.PlayerSaveData;
import ethos.runehub.event.FixedScheduleEvent;
import ethos.runehub.skill.artisan.cooking.food.Brew;
import ethos.runehub.skill.artisan.cooking.food.BrewDAO;
import ethos.util.Misc;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class BrewingCycleEvent extends FixedScheduleEvent {

    @Override
    public void execute() {
        PlayerCharacterContextDataAccessObject.getInstance().getAllEntries().forEach(player -> {
            if (PlayerHandler.getPlayer(player.getId()).isPresent()) {
                this.doCycleForOnlinePlayer(PlayerHandler.getPlayer(player.getId()).get());
            } else {
                this.doCycleForOfflinePlayer(player);
            }
        });
    }

    private void doCycleForOfflinePlayer(PlayerCharacterContext player) {
        this.removeMaterialsFromOfflinePlayer(player.getPlayerSaveData());
        this.addBrew(player.getPlayerSaveData());
        PlayerCharacterContextDataAccessObject.getInstance().update(player);
    }

    private void doCycleForOnlinePlayer(Player player) {
       this.removeMaterialsFromOnlinePlayer(player);
       this.addBrew(player.getContext().getPlayerSaveData());
       player.save();
    }

    private void addBrew(PlayerSaveData player) {
        if (player.getIdleBrewId() > 0) {
            Brew brew = BrewDAO.getInstance().read(player.getIdleBrewId());
            int theStuff = this.getItemIndex(8988, player.getIdleBrewingStation());
            int existingBrew = this.getItemIndex(brew.getPremiumBrewedItemId(), player.getIdleBrewingStation());
            int existingPremiumBrew = this.getItemIndex(brew.getPremiumBrewedItemId(), player.getIdleBrewingStation());
            int amountBrewed = 1;
            if (Misc.random(100) <= (theStuff != -1 ? 64 : 5)) {
                if (existingPremiumBrew == -1) {
                    player.getIdleBrewingStation().add(new GameItem(brew.getPremiumBrewedItemId(), amountBrewed));
                } else {
                    player.getIdleBrewingStation().get(existingPremiumBrew).setAmount(player.getIdleBrewingStation().get(existingPremiumBrew).getAmount() + amountBrewed);
                }
            } else {
                if (existingBrew == -1) {
                    player.getIdleBrewingStation().add(new GameItem(brew.getBaseBrewedItemId(), amountBrewed));
                } else {
                    player.getIdleBrewingStation().get(existingBrew).setAmount(player.getIdleBrewingStation().get(existingBrew).getAmount() + amountBrewed);
                }
            }
            player.setIdleBrewedXp(player.getIdleBrewedXp() + brew.getXp());
            this.removeTheStuff(player, theStuff);
        }
    }

    private void removeTheStuff(PlayerSaveData player, int theStuff) {
        if(theStuff != -1) {
            player.getIdleBrewingStation().get(theStuff).setAmount(player.getIdleBrewingStation().get(theStuff).getAmount() - 1);
        }
    }

    private int getItemIndex(int id, List<GameItem> items) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id)
                return i;
        }
        return -1;
    }


    private void removeMaterialsFromOfflinePlayer(PlayerSaveData player) {
        if (player.getIdleBrewId() > 0) {
            Brew brew = BrewDAO.getInstance().read(player.getIdleBrewId());
            List<GameItem> stationItems = player.getIdleBrewingStation();
            if (Arrays.stream(brew.getMaterials()).allMatch(gameItem -> stationItems.stream()
                    .filter(item -> item.getId() == gameItem.getId())
                    .anyMatch(item -> item.getAmount() >= gameItem.getAmount()))) {
                for (GameItem item: brew.getMaterials()) {
                    GameItem stationItem = stationItems.stream()
                            .filter(gameItem -> gameItem.getId() == item.getId())
                            .findAny().orElse(null);
                    if (stationItem != null) {
                        player.getIdleBrewingStation().remove(stationItem);
                        stationItem.setAmount(stationItem.getAmount() - item.getAmount());
                        player.getIdleBrewingStation().add(stationItem);
                    }
                }
            } else {
                player.setIdleBrewId(0);
            }
        }
    }

    private void removeMaterialsFromOnlinePlayer(Player player) {
        if (player.getContext().getPlayerSaveData().getIdleBrewId() > 0) {
            Brew brew = BrewDAO.getInstance().read(player.getContext().getPlayerSaveData().getIdleBrewId());
            List<GameItem> stationItems = player.getContext().getPlayerSaveData().getIdleBrewingStation();
            if (Arrays.stream(brew.getMaterials()).allMatch(gameItem -> stationItems.stream()
                    .filter(item -> item.getId() == gameItem.getId())
                    .anyMatch(item -> item.getAmount() >= gameItem.getAmount()))) {
                for (GameItem item: brew.getMaterials()) {
                    GameItem stationItem = stationItems.stream()
                            .filter(gameItem -> gameItem.getId() == item.getId())
                            .findAny().orElse(null);
                    if (stationItem != null) {
                        player.getContext().getPlayerSaveData().getIdleBrewingStation().remove(stationItem);
                        stationItem.setAmount(stationItem.getAmount() - item.getAmount());
                        player.getContext().getPlayerSaveData().getIdleBrewingStation().add(stationItem);
                    }
                }
            } else {
                player.getContext().getPlayerSaveData().setIdleBrewId(0);
            }
        }
    }

    public BrewingCycleEvent() {
        super(Duration.ofMinutes(90).toMillis(), "brewing-cycle");
    }
}
