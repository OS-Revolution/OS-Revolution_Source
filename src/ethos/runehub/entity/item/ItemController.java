package ethos.runehub.entity.item;

import ethos.Server;
import ethos.model.items.GroundItem;
import ethos.model.players.Player;
import ethos.runehub.entity.mob.hostile.HostileMob;
import ethos.runehub.entity.mob.hostile.HostileMobIdContextLoader;
import ethos.runehub.skill.Skill;
import ethos.runehub.skill.support.slayer.SlayerAssignmentDAO;
import ethos.runehub.world.RegionCache;
import ethos.runehub.world.RegionLoader;
import org.runehub.api.model.entity.item.loot.Loot;
import org.runehub.api.util.math.geometry.Point;

import java.util.logging.Logger;

public class ItemController {


    private static ItemController instance = null;

    public static ItemController getInstance() {
        if (instance == null)
            instance = new ItemController();
        return instance;
    }

    public void onDropReceivedEffect(Player player, Loot loot, int dropX, int dropY, int dropZ, int droppedBy) {
        int amountDropped = (int) loot.getAmount();
        int itemId = (int) loot.getId();
        if (player.getItems().playerHasItem(8411) && itemId == 995 && player.getContext().getPlayerSaveData().isGoldAccumulatorActive()) {
            amountDropped = doGoldAccumulatorEffect(player, amountDropped);
        } else if (player.getItems().playerHasItem(8412) && itemId == 995 && player.getContext().getPlayerSaveData().isAdvancedGoldAccumulatorActive()) {
            amountDropped = doAdvancedGoldAccumulatorEffect(player, amountDropped);
        } else if (player.getItems().playerHasItem(8413) && itemId == 995 && player.getContext().getPlayerSaveData().isMasterGoldAccumulatorActive()) {
            amountDropped = doMasterGoldAccumulatorEffect(player, amountDropped);
        }

        if (itemId == 275) {
           if (!RegionCache.getInstance().read(-6596346361728053460L).getBoundingShape().contains(new Point(dropX, dropY))
           || !HostileMobIdContextLoader.getInstance().read(droppedBy).getCategory().contains(SlayerAssignmentDAO.getInstance().read(player.getSlayerSave().getAssignmentId()).getCategory())) {
               Logger.getGlobal().finest("Would've received key");
           } else {
               Server.itemHandler.createGroundItem(player, (int) loot.getId(), dropX, dropY, dropZ, (int) loot.getAmount());
           }

        } else if (amountDropped > 0) {
            Server.itemHandler.createGroundItem(player, (int) loot.getId(), dropX, dropY, dropZ, (int) loot.getAmount());
        }
    }

    private int doGoldAccumulatorEffect(Player player, int amount) {
        final int max = 1000000;
        final int limit = max - player.getContext().getPlayerSaveData().getGoldAccumulatorAccumulated();
        final int quotient = limit / amount;
        final int remainder = limit % amount;
        if (player.getContext().getPlayerSaveData().getGoldAccumulatorAccumulated() < max) {
            if (quotient >= 1) {
                player.getContext().addGoldAccumulatorGold(amount);
                player.sendMessage("Your @" + 8411 + " picks the $" + amount + " coins up for you.");
                player.getItems().addOrDropItem(995, amount);
            } else {
                int amountDropped = amount - remainder;
                player.getContext().addGoldAccumulatorGold(remainder);
                player.sendMessage("Your @" + 8411 + " picks up $" + remainder + " coins for you and degrades to dust.");
                doGoldAccumulatorDegrade(player);
                player.getItems().addOrDropItem(995, remainder);
                return amountDropped;
            }
        }
        return 0;
    }

    private int doAdvancedGoldAccumulatorEffect(Player player, int amount) {
        final int amountDropped = (int) (amount * 0.9);
        player.getContext().addAdvancedGoldAccumulatorGold(amountDropped);
        player.getItems().addOrDropItem(995, amountDropped);
        player.sendMessage("Your @" + 8412 + " picks up $" + amount + " coins for you and consumes $" + (amount - amountDropped) + " coins.");
        return 0;
    }

    private int doMasterGoldAccumulatorEffect(Player player, int amount) {
        final float roll = Skill.SKILL_RANDOM.nextFloat();
        double odds = Math.min(0.5f, Math.max(0.01F, 0.01f * (amount / 100.0)));
        System.out.println("Odds: " + odds);
        if (roll <= odds) {
            int jewelsGenerated = 1;
            player.getContext().addMasterGoldAccumulatorGold(jewelsGenerated);
            player.getItems().addOrDropItem(1459, jewelsGenerated);
            player.sendMessage("Your @" + 8413 + " picks up $" + amount + " coins for you and generates $" + (jewelsGenerated) + " jewels.");
        }

        return 0;
    }

    private void doGoldAccumulatorDegrade(Player player) {
        player.getItems().deleteItem(8411, 1);
        player.getContext().getPlayerSaveData().setGoldAccumulatorAccumulated(0);
    }

    private ItemController() {

    }
}
