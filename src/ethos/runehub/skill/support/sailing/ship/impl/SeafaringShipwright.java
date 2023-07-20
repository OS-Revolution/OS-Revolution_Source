package ethos.runehub.skill.support.sailing.ship.impl;

import ethos.model.players.Player;
import ethos.runehub.skill.support.sailing.ship.DefaultShipwrightImpl;
import ethos.runehub.skill.support.sailing.ship.Ship;

public class SeafaringShipwright extends DefaultShipwrightImpl {

    @Override
    public void upgrade(int shipSlot) {
        Ship ship = this.getShip(shipSlot);
        double upgradeAmount = ship.getSeafaring() * BASE_UPGRADE;
        int upgradePotential = PLAYER_STAT_CAP - ship.getSeafaring();
        int amountToIncrease = (int) Math.min(upgradePotential,Math.max(1,upgradeAmount));

        if (consumeMaterials(ship.getSeafaring(),getSeafaringTier(ship))) {
            ship.setSeafaring(ship.getSeafaring() + amountToIncrease);
            pushChanges(shipSlot,ship);
            player.sendMessage("@blu@You successfully upgrade your ship's seafaring.");
        }
    }



    public SeafaringShipwright(Player player) {
        super(player);
    }

}
