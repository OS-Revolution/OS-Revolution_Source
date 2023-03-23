package ethos.runehub.event.shop.impl;

import ethos.Server;
import ethos.clip.Region;
import ethos.model.npcs.NPC;
import ethos.model.npcs.NPCHandler;
import ethos.model.players.PlayerHandler;
import ethos.runehub.entity.merchant.MerchantCache;
import ethos.runehub.entity.merchant.impl.CommodityMerchant;
import ethos.runehub.entity.merchant.impl.RotatingStockMerchant;
import ethos.runehub.event.shop.TravellingMerchantEvent;
import ethos.world.objects.GlobalObject;
import org.runehub.api.model.world.region.location.Location;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

public class TravellingCommodityMerchantEvent extends TravellingMerchantEvent {


    @Override
    protected void onSpawn() {
        ((RotatingStockMerchant) attachment).rotateStock();
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
        ((RotatingStockMerchant) attachment).rotateStock();
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
        PlayerHandler.getPlayers().stream().filter(player -> player.myShopId == attachment.getMerchantId())
                .forEach(player -> player.getPA().closeAllWindows());
        if (this.getCurrentPoint().getYCoordinate() == 0)
            PlayerHandler.executeGlobalMessage("^Market $Trader $Stan has a special offer of $" + decimalFormat.format(100 * (1.0 - sale
            )) + " % off @" + saleId + " !");
    }

    public TravellingCommodityMerchantEvent() {
        super(MerchantCache.getInstance().read(1328), 18000, new LinkedList<>(List.of(new Location(3099, 3251), new Location(3099, 1,3251))));
        this.saleId = ((CommodityMerchant) attachment).getItemOnSaleId();
        this.sale = ((CommodityMerchant) attachment).getSale();
    }

    private final int saleId;
    private final double sale;
    private final DecimalFormat decimalFormat = new DecimalFormat("##");
}
