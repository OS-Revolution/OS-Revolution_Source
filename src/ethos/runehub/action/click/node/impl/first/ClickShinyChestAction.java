package ethos.runehub.action.click.node.impl.first;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.clip.Region;
import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.runehub.skill.support.thieving.action.StealFromStallAction;
import ethos.world.objects.GlobalObject;
import org.runehub.api.io.load.impl.LootTableContainerDefinitionLoader;
import org.runehub.api.io.load.impl.LootTableContainerLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.ContainerType;
import org.runehub.api.model.entity.item.loot.LootTableContainer;
import org.runehub.api.model.world.Face;

import java.text.NumberFormat;

public class ClickShinyChestAction extends ClickNodeAction {

    @Override
    protected void validateNode() {
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(85, 1), "You need a @" + 85);
    }

    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(881);
        this.getActor().sendMessage("^Loot You open the Shiny Chest...");
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        LootTableContainerDefinitionLoader.getInstance().readAll().stream()
                .filter(lootTableContainer -> lootTableContainer.getContainerId() == getNodeId())
                .filter(lootTableContainerDefinition -> lootTableContainerDefinition.getType() == ContainerType.OBJECT.ordinal())
                .findAny().ifPresent(definition -> {
                    LootTableContainer lootTableContainer = LootTableContainerLoader.getInstance().read(definition.getId());
                    lootTableContainer.roll(this.getActor().getAttributes().getMagicFind()).forEach(lootTable -> {
                        LootTableLoader.getInstance().read(lootTable.getId()).roll(this.getActor().getAttributes().getMagicFind())
                                .forEach(loot -> {
                                    this.getActor().getItems().addItem((int) loot.getId(), (int) loot.getAmount());
                                    this.getActor().sendMessage("^Loot You received $" + NumberFormat.getInstance().format(loot.getAmount()) + " @" + loot.getId() + " @bla@!");
                                });
                    });
                });
        this.getActor().getItems().deleteItem2(85, 1);
        Server.getGlobalObjects().add(
                new GlobalObject(
                        10621,
                        this.getNodeX(),
                        this.getNodeY(),
                        nodeZ,
                        0,
                        10,
                        3,
                        4126
                )
        );
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    public ClickShinyChestAction(Player attachment, int nodeId, int nodeX, int nodeY, int nodeZ) {
        super(attachment, 5, nodeId, nodeX, nodeY);
        this.nodeZ = nodeZ;
    }

    private final int nodeZ;
}
