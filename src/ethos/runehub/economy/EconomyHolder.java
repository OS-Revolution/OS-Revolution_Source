package ethos.runehub.economy;

import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.model.math.impl.AdjustableLong;

import java.util.Map;

public class EconomyHolder {

    public int getHighestValueItemBaseValue() {
        return items.keySet().stream().mapToInt(EconomyEvaluator::getBaseValue).max().orElse(-1);
    }

    public int getHighestValueItemId() {
        final AdjustableInteger max = new AdjustableInteger(0);
        final AdjustableInteger maxID = new AdjustableInteger(0);
        items.keySet().forEach(id -> {
            int val = EconomyEvaluator.getBaseValue(id);
            if (val > max.value()) {
                max.setValue(val);
                maxID.setValue(id);
            }
        });
        return maxID.value();
    }

    public int getGreatestWealthContributorId() {
        final AdjustableLong max = new AdjustableLong(0L);
        final AdjustableInteger maxID = new AdjustableInteger(0);
        items.keySet().stream().forEach(id -> {
            long val = EconomyEvaluator.getBaseValue(id) * items.get(id).value();
            if (val > max.value()) {
                max.setValue(val);
                maxID.setValue(id);
            }
        });
        return maxID.value();
    }

    public long getGreatestWealthContributorValue() {
        final AdjustableLong max = new AdjustableLong(0L);
        final AdjustableInteger maxID = new AdjustableInteger(0);
        items.keySet().stream().forEach(id -> {
            long val = EconomyEvaluator.getBaseValue(id) * items.get(id).value();
            if (val > max.value()) {
                max.setValue(val);
                maxID.setValue(id);
            }
        });
        return max.value();
    }


    public String getPlayerName() {
        return playerName.split("\\.")[0];
    }

    public long getValue() {
        return items.keySet().stream().mapToLong(itemId -> EconomyEvaluator.getBaseValue(itemId) * items.get(itemId).value()).sum();
    }

    public long getExchangeValue() {
        return items.keySet().stream().mapToLong(itemId -> EconomyEvaluator.getExchangeValue(itemId) * items.get(itemId).value()).sum();
    }

    public Map<Integer, AdjustableLong> getItems() {
        return items;
    }

    public EconomyHolder(String playerName,  Map<Integer, AdjustableLong> items) {
        this.playerName = playerName;
        this.items = items;
    }

    private final String playerName;
    private final Map<Integer, AdjustableLong> items;
}
