package ethos.runehub.skill.support.sailing.ship.impl;

import ethos.model.players.Player;
import ethos.runehub.skill.support.sailing.ship.DefaultShipwrightImpl;
import ethos.runehub.skill.support.sailing.ship.Ship;

public class MoraleShipwright extends DefaultShipwrightImpl {

    @Override
    public void upgrade(int shipSlot) {
        Ship ship = this.getShip(shipSlot);
        double upgradeAmount = ship.getMorale() * BASE_UPGRADE;
        int upgradePotential = PLAYER_STAT_CAP - ship.getMorale();
        int amountToIncrease = (int) Math.min(upgradePotential,Math.max(1,upgradeAmount));

        if (consumeMaterials(ship.getMorale(),getMoraleTier(ship))) {
            ship.setMorale(ship.getMorale() + amountToIncrease);
            pushChanges(shipSlot,ship);
            player.sendMessage("@blu@You successfully upgrade your ship's morale.");
        }
    }



    public MoraleShipwright(Player player) {
        super(player);
    }

}
