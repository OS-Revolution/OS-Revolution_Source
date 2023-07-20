package ethos.runehub.skill.support.sailing.ui;

import ethos.model.players.Player;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.skill.support.sailing.SailingUtils;
import ethos.runehub.skill.support.sailing.ship.Ship;
import ethos.runehub.skill.support.sailing.ship.Shipwright;
import ethos.runehub.skill.support.sailing.ship.impl.*;
import ethos.runehub.ui.impl.SelectionParentUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ShipwrightUI extends SelectionParentUI {

    final String[] upgradeNames = {"Seafaring", "Combat", "Morale", "Speed", "Weight"};
    final String[] flavorText = {
            "Seafaring improves your ship's survivability",
            "Combat improves your ship's ability to defend itself",
            "Morale keeps your crew loyal and prevents mutiny",
            "Speed shortens the time a voyage takes",
            "Weight increases how much cargo your ship can hold"
    };

    @Override
    protected void onOpen() {
        super.onOpen();
        titleLabel.setText("Upgrade your Ship");
        listTitleLabel.setText("Upgrades");
        rewardLabel.setText("Cost");
        this.loadUpgradeList();
        this.updateUI();
    }

    private void loadUpgradeList() {
        for (int i = 0; i < upgradeNames.length; i++) {
            int index = i;
            listItemLabels[i].setText(upgradeNames[i]);
            listItemButtons[i].addActionListener(actionEvent -> selectUpgrade(index));
        }
    }

    private void selectUpgrade(int index) {
        Ship ship = this.getPlayer().getSkillController().getSailing().getShip(shipSlot);
        infoH1Label.setText(upgradeNames[index] + " Upgrade");
        infoH2Label.setText(flavorText[index]);

        infoLine2Label.setText("Upgrade amount: +" + (Shipwright.BASE_UPGRADE * 100.0) + "%");
        infoLine4Label.setText("Region Tier: " + seafaringShipwright.getRegionTier(ship));


        optionOneLabel.setText("@whi@Upgrade");
        switch (index) {
            case 0:
                this.drawMaterials(Objects.requireNonNull(this.getUpgradeMaterials(index)));
                infoLine1Label.setText("Current Seafaring: " + this.getPlayer().getSkillController().getSailing().getShip(shipSlot).getSeafaring() + "/" + Shipwright.PLAYER_STAT_CAP);
                infoLine3Label.setText("Current stat tier: " + seafaringShipwright.getSeafaringTier(ship));
                this.registerButton(actionEvent -> {
                    seafaringShipwright.upgrade(shipSlot);
                    this.selectUpgrade(index);
                }, 150111);
                break;
            case 1:
                this.drawMaterials(Objects.requireNonNull(this.getUpgradeMaterials(index)));
                infoLine1Label.setText("Current Combat: " + this.getPlayer().getSkillController().getSailing().getShip(shipSlot).getCombat() + "/" + Shipwright.PLAYER_STAT_CAP);
                infoLine3Label.setText("Current stat tier: " + combatShipwright.getCombatTier(ship));
                this.registerButton(actionEvent -> {
                    combatShipwright.upgrade(shipSlot);
                    this.selectUpgrade(index);
                }, 150111);
                break;
            case 2:
                this.drawMaterials(Objects.requireNonNull(this.getUpgradeMaterials(index)));
                infoLine1Label.setText("Current Morale: " + this.getPlayer().getSkillController().getSailing().getShip(shipSlot).getMorale() + "/" + Shipwright.PLAYER_STAT_CAP);
                infoLine3Label.setText("Current stat tier: " + moraleShipwright.getMoraleTier(ship));
                this.registerButton(actionEvent -> {
                    moraleShipwright.upgrade(shipSlot);
                    this.selectUpgrade(index);
                }, 150111);
                break;
            case 3:
                this.drawMaterials(Objects.requireNonNull(this.getUpgradeMaterials(index)));
                infoLine1Label.setText("Current Speed: " + this.getPlayer().getSkillController().getSailing().getShip(shipSlot).getSpeed() + "/" + Shipwright.PLAYER_STAT_CAP);
                infoLine3Label.setText("Current stat tier: " + speedShipwright.getSpeedTier(ship));
                this.registerButton(actionEvent -> {
                    speedShipwright.upgrade(shipSlot);
                    this.selectUpgrade(index);
                }, 150111);
                break;
            case 4:
                this.drawMaterials(Objects.requireNonNull(this.getUpgradeMaterials(index)));
                infoLine1Label.setText("Current Weight Capacity: " + this.getPlayer().getSkillController().getSailing().getShip(shipSlot).getWeightCapacity() + "/" + Shipwright.PLAYER_WEIGHT_CAP);
                infoLine3Label.setText("Current stat tier: " + weightShipwright.getWeightTier(ship));
                this.registerButton(actionEvent -> {
                    weightShipwright.upgrade(shipSlot);
                    this.selectUpgrade(index);
                }, 150111);
                break;

        }
        this.updateUI();
    }

    private void drawMaterials(GameItem[] materials) {
        for (int i = 0; i < materials.length; i++) {
            GameItem material = materials[i];
            items[i] = material;
        }
    }

    private GameItem[] getUpgradeMaterials(int index) {
        Ship ship = this.getPlayer().getSkillController().getSailing().getShip(shipSlot);
        switch (index) {
            case 0://seafaring
                return seafaringShipwright.getMaterials(ship.getSeafaring(), seafaringShipwright.getSeafaringTier(ship));
            case 1://combat
                return combatShipwright.getMaterials(ship.getCombat(), combatShipwright.getCombatTier(ship));
            case 2://morale
                return moraleShipwright.getMaterials(ship.getMorale(), moraleShipwright.getMoraleTier(ship));
            case 3://speed
                return speedShipwright.getMaterials(ship.getSpeed(), speedShipwright.getSpeedTier(ship));
            case 4: //weight
                return weightShipwright.getMaterials(ship.getWeightCapacity(), weightShipwright.getWeightTier(ship));
        }
        return new GameItem[] {new GameItem(995,SailingUtils.SHIPWRIGHT_BASE_FEE)};
    }

    public ShipwrightUI(Player player, int shipSlot) {
        super(player);
        this.shipSlot = shipSlot;
        this.seafaringShipwright = new SeafaringShipwright(player);
        this.combatShipwright = new CombatShipwright(player);
        this.moraleShipwright = new MoraleShipwright(player);
        this.speedShipwright = new SpeedShipwright(player);
        this.weightShipwright = new WeightShipwright(player);
    }

    private final int shipSlot;
    private final Shipwright seafaringShipwright,combatShipwright,moraleShipwright,speedShipwright,weightShipwright;
}
