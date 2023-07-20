package ethos.runehub.content.upgrading;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UpgradeRuleCache {

    private static UpgradeRuleCache instance = null;

    public static UpgradeRuleCache getInstance() {
        if (instance == null)
            instance = new UpgradeRuleCache();
        return instance;
    }

    private final Map<Integer, List<UpgradeRule>> cache;

    private UpgradeRuleCache() {
        this.cache = new HashMap<>();
    }

    public List<UpgradeRule> getUpgradeRules(int sourceId, int upgradeId) {
        // Check if the cache already contains the requested data
        if (cache.containsKey(sourceId)) {
            List<UpgradeRule> cachedRules = cache.get(sourceId);
            if (cachedRules != null && !cachedRules.isEmpty()) {
                // Return the cached UpgradeRule list for the sourceId
                return filterUpgradeRules(cachedRules, upgradeId);
            }
        }

        // If not found in the cache, perform the read operation and populate the cache
        List<UpgradeRule> upgradeRules = readUpgradeRulesFromDatabase(sourceId, upgradeId);
        cache.put(sourceId, upgradeRules);

        return upgradeRules;
    }

    private List<UpgradeRule> filterUpgradeRules(List<UpgradeRule> upgradeRules, int upgradeId) {
        // If upgradeId is -1, return all the upgradeRules
        if (upgradeId == -1) {
            return upgradeRules;
        }

        // Filter and return the upgradeRules that match the upgradeId
        return upgradeRules.stream()
                .filter(rule -> rule.getUpgradedItemId() == upgradeId)
                .collect(Collectors.toList());
    }

    private List<UpgradeRule> readUpgradeRulesFromDatabase(int sourceId, int upgradeId) {
        // Perform the database read operation here and return the results
        // You can use your existing read() method or adjust it to fetch the data

        // For demonstration purposes, I'll return some dummy data
        List<UpgradeRule> dummyUpgradeRules = UpgradeRuleDatabase.getInstance().read(sourceId, upgradeId);
        return filterUpgradeRules(dummyUpgradeRules, upgradeId);
    }
}
