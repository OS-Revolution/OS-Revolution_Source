package ethos.runehub.skill.support.sailing.ship.impl;

import ethos.model.players.Player;
import ethos.runehub.skill.support.sailing.ship.DefaultShipwrightImpl;
import ethos.runehub.skill.support.sailing.ship.Ship;

public class CombatShipwright extends DefaultShipwrightImpl {

    @Override
    public void upgrade(int shipSlot) {
        Ship ship = this.getShip(shipSlot);
        double upgradeAmount = ship.getCombat() * BASE_UPGRADE;
        int upgradePotential = PLAYER_STAT_CAP - ship.getCombat();
        int amountToIncrease = (int) Math.min(upgradePotential,Math.max(1,upgradeAmount));

        if (consumeMaterials(ship.getCombat(),getCombatTier(ship))) {
            ship.setCombat(ship.getCombat() + amountToIncrease);
            pushChanges(shipSlot,ship);
            player.sendMessage("@blu@You successfully upgrade your ship's combat.");
        }
    }



    public CombatShipwright(Player player) {
        super(player);
    }

}
