package ethos.runehub.event.dnd;

import ethos.Server;
import ethos.model.npcs.NPC;
import ethos.model.npcs.NPCHandler;
import ethos.model.players.PlayerHandler;
import ethos.runehub.entity.merchant.MerchantCache;
import ethos.runehub.entity.merchant.impl.CommodityMerchant;
import ethos.runehub.entity.merchant.impl.RotatingStockMerchant;
import ethos.world.objects.GlobalObject;
import org.runehub.api.model.world.region.location.Location;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

public class TravellingCommodityMerchantEvent extends TravellingMerchantEvent {

    @Override
    public void execute() {
        NPC npc = NPCHandler.getNpc(merchant.getMerchantId());
        ((RotatingStockMerchant) merchant).rotateStock();

        if (npc == null) {
            this.spawn();
            this.saleId = ((CommodityMerchant) MerchantCache.getInstance().read(1328)).getItemOnSaleId();
            this.sale = ((CommodityMerchant) MerchantCache.getInstance().read(1328)).getSale();
        } else {
            this.relocate();
            this.saleId = -1;
            this.sale = 0;
        }
    }

    @Override
    protected void onSpawn() {
//        ((RotatingStockMerchant) merchant).rotateStock();
        Server.getGlobalObjects().add(
                new GlobalObject(
                        10457,
                        this.getCurrentPoint().getXCoordinate(),
                        this.getCurrentPoint().getZCoordinate() - 3,
                        0,
                        2,
                        10
                )
        );
        PlayerHandler.executeGlobalMessage("^Market $Trader $Stan has a special offer of $" + decimalFormat.format(100 * (1.0 - sale
        )) + " % off @" + saleId + " !");
    }

    @Override
    protected void onRelocate() {
//        ((RotatingStockMerchant) merchant).rotateStock();
        if(this.getCurrentPoint().getYCoordinate() == 1) {
            Server.getGlobalObjects().add(
                    new GlobalObject(
                            -1,
                            this.getCurrentPoint().getXCoordinate(),
                            this.getCurrentPoint().getZCoordinate() - 3,
                            0,
                            2,
                            10
                    )
            );
            Server.getGlobalObjects().remove(
                    new GlobalObject(
                            10457,
                            this.getCurrentPoint().getXCoordinate(),
                            this.getCurrentPoint().getZCoordinate() - 3,
                            0,
                            2,
                            10
                    )
            );
        } else {
            Server.getGlobalObjects().add(
                    new GlobalObject(
                            10457,
                            this.getCurrentPoint().getXCoordinate(),
                            this.getCurrentPoint().getZCoordinate() - 3,
                            0,
                            2,
                            10
                    )
            );
            Server.getGlobalObjects().remove(
                    new GlobalObject(
                            -1,
                            this.getCurrentPoint().getXCoordinate(),
                            this.getCurrentPoint().getZCoordinate() - 3,
                            0,
                            2,
                            10
                    )
            );
        }
        PlayerHandler.getPlayers().stream().filter(player -> player.myShopId == merchant.getMerchantId())
                .forEach(player -> player.getPA().closeAllWindows());
        if (this.getCurrentPoint().getYCoordinate() == 0)
            PlayerHandler.executeGlobalMessage("^Market $Trader $Stan has a special offer of $" + decimalFormat.format(100 * (1.0 - sale
            )) + " % off @" + saleId + " !");
    }

    public TravellingCommodityMerchantEvent() {
        super(MerchantCache.getInstance().read(1328), Duration.of(3, ChronoUnit.HOURS), new LinkedList<>(List.of(new Location(3099, 3251), new Location(3099, 1, 3251))));
    }

    private int saleId;
    private double sale;
    private final DecimalFormat decimalFormat = new DecimalFormat("##");
}
