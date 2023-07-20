package ethos.runehub.skill.support.sailing.io;

import ethos.runehub.entity.item.GameItem;
import ethos.runehub.skill.support.sailing.voyage.TradeGood;
import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

import java.util.*;

@StoredObject(tableName = "sailing_save_data")
public class SailingSaveData {

    public Map<Integer,TradeGood> getSoldTradeGoods(int vIndex) {
        final Map<Integer,TradeGood> tradeGoodMap = new HashMap<>();
        for (int i = 0; i < voyageSoldGoods[vIndex].length; i++) {
            TradeGood tradeGood = TradeGood.fromLong(voyageSoldGoods[vIndex][i]);
            if (tradeGood.getItemId() != 0) {
                if (tradeGoodMap.containsKey(tradeGood.getItemId())) {
                    tradeGoodMap.get(tradeGood.getItemId()).increaseStock(tradeGood.getStock());
                } else {
                    tradeGoodMap.put(tradeGood.getItemId(), tradeGood);
                }
            }
        }
        return tradeGoodMap;
    }

    public Map<Integer,TradeGood> getBoughtTradeGoods(int vIndex) {
        final Map<Integer,TradeGood> tradeGoodMap = new HashMap<>();
        for (int i = 0; i < voyageBoughtGoods[vIndex].length; i++) {
            TradeGood tradeGood = TradeGood.fromLong(voyageBoughtGoods[vIndex][i]);
            if (tradeGood.getItemId() != 0) {
                if (tradeGoodMap.containsKey(tradeGood.getItemId())) {
                    tradeGoodMap.get(tradeGood.getItemId()).increaseStock(tradeGood.getStock());
                } else {
                    tradeGoodMap.put(tradeGood.getItemId(), tradeGood);
                }
            }
        }
        return tradeGoodMap;
    }

    public long getPlayerId() {
        return playerId;
    }

    public long[][] getBuyingCargo() {
        return buyingCargo;
    }

    public long[][] getSellingCargo() {
        return sellingCargo;
    }

    public long[] getShipSlotTimestamp() {
        return shipSlotTimestamp;
    }

    public void setCargo(int index, long val) {
        cargo[index] = val;
    }

    public void setBuyCargo(int index, long val, int ship) {
        buyingCargo[ship][index] = val;
    }

    public void setSellCargo(int index, long val,int ship) {
        sellingCargo[ship][index] = val;
    }

    public void setOrRemoveCargo(long val) {
        GameItem gameItem = GameItem.decodeGameItem(val);
        boolean added = false;
        for (int i = 0; i < cargo.length; i++) {
            if (cargo[i] != 0) {
                GameItem cargoItem = GameItem.decodeGameItem(cargo[i]);
                if (cargoItem.getId() == gameItem.getId()) {
                    int inStockAmount = cargoItem.getAmount();
                    int amountToAdd = gameItem.getAmount();
                    if (inStockAmount - amountToAdd > 0) {
                        cargoItem.setAmount(inStockAmount - amountToAdd);
                        added = true;
                        cargo[i] = cargoItem.encodeGameItem();
                    }
                }
            }
        }
        if (!added) {
            setCargo(getNextAvailableCargoIndex(),gameItem.encodeGameItem());
        }
    }

    public void setOrAddCargo(long val) {
        GameItem gameItem = GameItem.decodeGameItem(val);
        boolean added = false;
        for (int i = 0; i < cargo.length; i++) {
            if (cargo[i] != 0) {
                GameItem cargoItem = GameItem.decodeGameItem(cargo[i]);
                if (cargoItem.getId() == gameItem.getId()) {
                    int inStockAmount = cargoItem.getAmount();
                    int amountToAdd = gameItem.getAmount();
                    cargoItem.setAmount(inStockAmount + amountToAdd);
                    added = true;
                    cargo[i] = cargoItem.encodeGameItem();
                }
            }
        }
        if (!added) {
            System.out.println("Adding: " + gameItem);
            setCargo(getNextAvailableCargoIndex(),gameItem.encodeGameItem());
        }
    }

    public int getNextAvailableCargoIndex() {
        for (int i = 0; i < cargo.length; i++) {
            if (cargo[i] == 0)
                return i;
        }
        throw new IllegalArgumentException("Cargo hold is full!");
    }

    public int getNextAvailableCargoIndex(long[] hold) {
        for (int i = 0; i < hold.length; i++) {
            if (hold[i] == 0)
                return i;
        }
        throw new IllegalArgumentException("Cargo hold is full!");
    }

    public long[] getAvailabeVoyages() {
        return availabeVoyages;
    }

    public long[] getShipSlot() {
        return shipSlot;
    }

    public void setLeaguesTravelled(long leaguesTravelled) {
        this.leaguesTravelled = leaguesTravelled;
    }

    public long getLeaguesTravelled() {
        return leaguesTravelled;
    }

    public int getPreferredRegionId() {
        return preferredRegionId;
    }

    public void setPreferredRegionId(int preferredRegionId) {
        this.preferredRegionId = preferredRegionId;
    }

    public long[] getActiveVoyages() {
        return activeVoyages;
    }

    public void setAvailableVoyage(int index, long value) {
        availabeVoyages[index] = value;
    }

    public void setActiveVoyage(int index, long value) {
        activeVoyages[index] = value;
    }

    public void setShipSlot(int index, long value) {
        shipSlot[index] = value;
    }

    public void setShipSlotTimestamp(int index, long value) {
        shipSlotTimestamp[index] = value;
    }

    public long[] getCargo() {
        return cargo;
    }

    public long[][] getVoyageBoughtGoods() {
        return voyageBoughtGoods;
    }

    public long[][] getVoyageSoldGoods() {
        return voyageSoldGoods;
    }

    public void setVoyageSoldGoods(int vIndex,int index, long val) {
        this.voyageSoldGoods[vIndex][index] = val;
    }

    public void setVoyageBoughtGoods(int vIndex,int index, long val) {
        this.voyageBoughtGoods[vIndex][index] = val;
    }

    public long getCoffer() {
        return coffer;
    }

    public void setCoffer(long coffer) {
        this.coffer = coffer;
    }

    public SailingSaveData(long playerId, long[][] buyingCargo, long[][] sellingCargo,
                           long[] shipSlot, long[] availabeVoyages, long[] shipSlotTimestamp, long leaguesTravelled,
                           int preferredRegionId, long[] activeVoyages, long[] cargo,
                           long[][] voyageSoldGoods, long[][] voyageBoughtGoods, long coffer) {
        this.playerId = playerId;
        this.buyingCargo = buyingCargo;
        this.sellingCargo = sellingCargo;
        this.shipSlot = shipSlot;
        this.availabeVoyages = availabeVoyages;
        this.shipSlotTimestamp = shipSlotTimestamp;
        this.leaguesTravelled = leaguesTravelled;
        this.preferredRegionId = preferredRegionId;
        this.activeVoyages = activeVoyages;
        this.cargo = cargo;
        this.voyageSoldGoods = voyageSoldGoods;
        this.voyageBoughtGoods = voyageBoughtGoods;
        this.coffer = coffer;
    }

    @StoredValue(type = SqlDataType.BIGINT, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final long playerId;
    @StoredValue(type = SqlDataType.JSON)
    private final long[][] buyingCargo;
    @StoredValue(type = SqlDataType.JSON)
    private final long[][] sellingCargo;
    @StoredValue(type = SqlDataType.JSON)
    private final long[] shipSlot;
    @StoredValue(type = SqlDataType.JSON)
    private final long[] availabeVoyages;
    @StoredValue(type = SqlDataType.JSON)
    private final long[] shipSlotTimestamp;
    @StoredValue(type = SqlDataType.BIGINT)
    private long leaguesTravelled;
    @StoredValue(type = SqlDataType.INTEGER)
    private int preferredRegionId = -1;
    @StoredValue(type = SqlDataType.JSON)
    private final long[] activeVoyages;
    @StoredValue(type = SqlDataType.JSON)
    private final long[] cargo;
    @StoredValue(type = SqlDataType.JSON)
    private final long[][] voyageSoldGoods;
    @StoredValue(type = SqlDataType.JSON)
    private final long[][] voyageBoughtGoods;
    @StoredValue(type = SqlDataType.BIGINT)
    private long coffer;
}
