package ethos.runehub;

import org.runehub.api.io.load.impl.LootTableContainerDefinitionLoader;
import org.runehub.api.io.load.impl.LootTableContainerLoader;
import org.runehub.api.model.entity.item.loot.ContainerType;
import org.runehub.api.model.entity.item.loot.LootTableContainer;
import org.runehub.api.model.entity.item.loot.LootTableContainerDefinition;

import java.util.Optional;

public class LootTableContainerUtils {

    public static Optional<LootTableContainer> getLootTableContainer(int id, ContainerType type) {
        final Optional<LootTableContainerDefinition> definition =LootTableContainerDefinitionLoader.getInstance().readAll().stream()
                .filter(def -> def.getType() == type.ordinal())
                .filter(def -> def.getContainerId() == id)
                .findAny();
        return definition.map(lootTableContainerDefinition -> LootTableContainerLoader.getInstance().read(lootTableContainerDefinition.getId()));
    }
}
