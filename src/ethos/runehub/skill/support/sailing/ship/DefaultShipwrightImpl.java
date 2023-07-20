package ethos.runehub.skill.support.sailing.ship;

import ethos.model.players.Player;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.skill.support.sailing.SailingUtils;
import org.runehub.api.model.math.impl.IntegerRange;

public abstract class DefaultShipwrightImpl implements Shipwright {

    @Override
    public GameItem[] getMaterials(int stat, int tier) {
        int regionTier = this.getRegionTierForStat(stat);
        GameItem[] materials = new GameItem[SailingUtils.getMaterialsForRegionTier(regionTier).length + 1];

        // Calculate modified amounts based on costModifier
        double modifier = BASE_UPGRADE_COST_MODIFIER * tier;

        for (int i = 0; i < SailingUtils.getMaterialsForRegionTier(regionTier).length; i++) {
            GameItem material = SailingUtils.getMaterialsForRegionTier(regionTier)[i];
            int modifiedAmount = (int) Math.ceil(material.getAmount() + (modifier * material.getAmount()));
            GameItem item = new GameItem(material.getId(),modifiedAmount);
            materials[i] = item;
        }
        materials[materials.length -1] = new GameItem(995,(int) Math.ceil(SailingUtils.SHIPWRIGHT_BASE_FEE + (modifier * SailingUtils.SHIPWRIGHT_BASE_FEE )));
        return materials;
    }

    protected boolean consumeMaterials(int stat, int tier) {
        int regionTier = this.getRegionTierForStat(stat);
        GameItem[] materials = SailingUtils.getMaterialsForRegionTier(regionTier);
        double modifier = tier * BASE_UPGRADE_COST_MODIFIER;

        if (player.getSailingSaveData().getCoffer() <  SailingUtils.SHIPWRIGHT_BASE_FEE) {
            // Player does not have enough fee
            player.sendMessage("You do not have #" + SailingUtils.SHIPWRIGHT_BASE_FEE + " @" + 995 + " in your coffers.");
            return false;
        }

        for (GameItem material : materials) {
            int amountRequired = (int) Math.ceil(material.getAmount() + (material.getAmount() * modifier));
            if (SailingUtils.getCargoAmount(player,material.getId())  < amountRequired) {
                // Player does not have enough of the required material
                player.sendMessage("You do not have  #" + amountRequired +" @" + material.getId() + " in your stockpile.");
                return false;
            }
        }

        // Player has all the materials and fee, proceed with the upgrade process

        // Deduct the fee from the player's inventory
        player.getSailingSaveData().setCoffer(player.getSailingSaveData().getCoffer() -  SailingUtils.SHIPWRIGHT_BASE_FEE);

        // Deduct the required materials from the player's inventory
        for (GameItem material : materials) {
            GameItem item = new GameItem(material.getId(), (int) Math.ceil(material.getAmount() + (material.getAmount() * modifier)));
            player.getSailingSaveData().setOrRemoveCargo(item.encodeGameItem());
        }
        return true;
    }

    protected int getRegionTierForStat(int stat) {
        return getRegionForTier(stat);
    }

    @Override
    public int getRegionTier(Ship  ship) {
        int seafaring = ship.getSeafaring();
        int combat = ship.getCombat();
        int morale = ship.getMorale();
        int speed = ship.getSpeed();

        int statTotal = (seafaring + combat + morale + speed) / 4;
        int region = getRegionForTier(statTotal);

        return region;
    }

    protected int getRegionForTier(int statAverage) {
        if (new IntegerRange(5, 15).within(statAverage)) {
            return 0;
        } else if (new IntegerRange(15, 50).within(statAverage)) {
            return 1;
        } else if (new IntegerRange(50, 100).within(statAverage)) {
            return 2;
        } else if (new IntegerRange(100, 200).within(statAverage)) {
            return 3;
        } else if (new IntegerRange(200, 300).within(statAverage)) {
            return 4;
        } else if (new IntegerRange(400, 500).within(statAverage)) {
            return 5;
        } else if (new IntegerRange(600, 700).within(statAverage)) {
            return 6;
        } else if (new IntegerRange(800, 900).within(statAverage)) {
            return 7;
        } else if (new IntegerRange(1000, 1500).within(statAverage)) {
            return 8;
        } else {
            return 0; // Default or invalid region
        }
    }

    protected void pushChanges(int shipSlot,Ship ship) {
        player.getSailingSaveData().setShipSlot(shipSlot,ship.toLong());
        player.save();
    }

    protected Ship getShip(int slot) {
        return player.getSkillController().getSailing().getShip(slot);
    }


    public DefaultShipwrightImpl(Player player) {
        this.player = player;
    }

    protected final Player player;
}
