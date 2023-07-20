package ethos.runehub.skill.support.sailing.ship.impl;

import ethos.model.players.Player;
import ethos.runehub.skill.support.sailing.ship.DefaultShipwrightImpl;
import ethos.runehub.skill.support.sailing.ship.Ship;

public class WeightShipwright extends DefaultShipwrightImpl {

    @Override
    public void upgrade(int shipSlot) {
        Ship ship = this.getShip(shipSlot);
        double upgradeAmount = ship.getWeightCapacity() * BASE_UPGRADE;
        int upgradePotential = PLAYER_WEIGHT_CAP - ship.getSpeed();
        int amountToIncrease = (int) Math.min(upgradePotential,Math.max(1,upgradeAmount));

        if (consumeMaterials(ship.getWeightCapacity(),getWeightTier(ship))) {
            ship.setWeightCapacity(ship.getWeightCapacity() + amountToIncrease);
            pushChanges(shipSlot,ship);
            player.sendMessage("@blu@You successfully upgrade your ship's weight limit.");
        }
    }



    public WeightShipwright(Player player) {
        super(player);
    }

}
