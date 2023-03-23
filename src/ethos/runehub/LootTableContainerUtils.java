package ethos.runehub;

import org.runehub.api.io.load.impl.LootTableContainerDefinitionLoader;
import org.runehub.api.io.load.impl.LootTableContainerLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class LootTableContainerUtils {

    public static Optional<LootTableContainer> getLootTableContainer(int id, ContainerType type) {
        final Optional<LootTableContainerDefinition> definition =LootTableContainerDefinitionLoader.getInstance().readAll().stream()
                .filter(def -> def.getType() == type.ordinal())
                .filter(def -> def.getContainerId() == id)
                .findAny();
        return definition.map(lootTableContainerDefinition -> LootTableContainerLoader.getInstance().read(lootTableContainerDefinition.getId()));
    }

    public static List<Loot> open(LootTableContainer container,float magicFind) {
       final List<LootTable> rolledTables = new ArrayList<>();
        final List<Loot> rolledLoot = new ArrayList<>();

        container.roll(magicFind).forEach(loot -> rolledTables.add(LootTableLoader.getInstance().read(loot.getId())));
        rolledTables.forEach(lootTable -> rolledLoot.addAll(lootTable.roll(magicFind)));

        return rolledLoot;
    }

    public static Collection<Loot> open(LootTable table, float magicFind) {
        return table.roll(magicFind);
    }
}
