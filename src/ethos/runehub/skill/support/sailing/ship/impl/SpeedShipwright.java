package ethos.runehub.skill.support.sailing.ship.impl;

import ethos.model.players.Player;
import ethos.runehub.skill.support.sailing.ship.DefaultShipwrightImpl;
import ethos.runehub.skill.support.sailing.ship.Ship;

public class SpeedShipwright extends DefaultShipwrightImpl {

    @Override
    public void upgrade(int shipSlot) {
        Ship ship = this.getShip(shipSlot);
        double upgradeAmount = ship.getSpeed() * BASE_UPGRADE;
        int upgradePotential = PLAYER_STAT_CAP - ship.getSpeed();
        int amountToIncrease = (int) Math.min(upgradePotential,Math.max(1,upgradeAmount));

        if (consumeMaterials(ship.getSpeed(),getSpeedTier(ship))) {
            ship.setSpeed(ship.getSpeed() + amountToIncrease);
            pushChanges(shipSlot,ship);
            player.sendMessage("@blu@You successfully upgrade your ship's speed.");
        }
    }



    public SpeedShipwright(Player player) {
        super(player);
    }

}
