package ethos.rshd.loot;

import org.menaphos.Menaphos;
import org.menaphos.model.loot.LootableItem;
import org.menaphos.model.loot.LootableNpc;
import org.menaphos.model.loot.LootableObject;

import java.util.HashMap;
import java.util.Map;

public final class CustomLootFactory {

    private static Map<Integer, LootableObject> lootableObjectMap = new HashMap<>();
    private static Map<Integer, LootableItem> lootableItemMap = new HashMap<>();
    private static Map<Integer, LootableNpc> lootableNpcMap = new HashMap<>();


    public static LootableNpc getLootableNpc(int id) {
        LootableNpc lootableNpc = lootableNpcMap.get(id);

        if (lootableNpc == null) {
            lootableNpc = LootableNpcRepositoryManager.getInstance().getRepository().readByKey(String.valueOf(id));
        }
        lootableNpcMap.put(id,lootableNpc);

        return lootableNpc;
    }

    public static LootableObject getLootableObject(int id) {
        LootableObject lootableObject = lootableObjectMap.get(id);

        if (lootableObject == null) {
            lootableObject = Menaphos.getLootableObjectRepositoryManager().getRepository().readByKey(String.valueOf(id));
        }
        lootableObjectMap.put(id,lootableObject);

        return lootableObject;
    }

    public static LootableItem getLootableItem(int id) {
        LootableItem lootableItem = lootableItemMap.get(id);

        if (lootableItem == null) {
            lootableItem = Menaphos.getLootableItemRepositoryManger().getRepository().readByKey(String.valueOf(id));
        }
        lootableItemMap.put(id,lootableItem);

        return lootableItem;
    }

}
