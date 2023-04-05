package ethos.runehub.action.click.node.impl.first;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.clip.Region;
import ethos.model.players.Player;
import ethos.runehub.LootTableContainerUtils;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.runehub.dialog.DialogOption;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.loot.Lootbox;
import ethos.runehub.skill.support.thieving.action.StealFromStallAction;
import ethos.runehub.ui.impl.LootContainerUI;
import ethos.runehub.world.WorldSettingsController;
import ethos.world.objects.GlobalObject;
import org.runehub.api.io.data.impl.LootTableContainerDAO;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.io.load.impl.LootTableContainerDefinitionLoader;
import org.runehub.api.io.load.impl.LootTableContainerLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.GameItem;
import org.runehub.api.model.entity.item.loot.ContainerType;
import org.runehub.api.model.entity.item.loot.Loot;
import org.runehub.api.model.entity.item.loot.LootTableContainer;
import org.runehub.api.model.world.Face;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

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
        if (WorldSettingsController.getInstance().getWorldSettings().getCurrentEventId() != 2) {
            this.getActor().sendUI(new LootContainerUI(this.getActor(), LootTableContainerLoader.getInstance().read(
                    LootTableContainerDefinitionLoader.getInstance().readAll().stream()
                            .filter(lootTableContainer -> lootTableContainer.getContainerId() == 4126)
                            .filter(lootTableContainerDefinition -> lootTableContainerDefinition.getType() == ContainerType.OBJECT.ordinal())
                            .findAny().orElseThrow().getId()
            ), new GameItem(85, 1)));
        } else {
            final List<Loot> lootOptions = new ArrayList<>();

            lootOptions.addAll(LootTableContainerUtils.open(LootTableContainerDAO.getInstance().read(8806239177860205239L),this.getActor().getAttributes().getMagicFind()));
            lootOptions.addAll(LootTableContainerUtils.open(LootTableContainerDAO.getInstance().read(8806239177860205239L),this.getActor().getAttributes().getMagicFind()));
            this.getActor().getItems().deleteItem2(85,1);
            Loot loot1 = lootOptions.get(0);
            Loot loot2 = lootOptions.get(1);
            this.getActor().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getActor())
                    .addOptions(new DialogOption(ItemIdContextLoader.getInstance().read((int) loot1.getId()).getName()) {
                                    @Override
                                    public void onAction() {
                                        getActor().getItems().addItemUnderAnyCircumstance((int) loot1.getId(), (int) loot1.getAmount());
                                        getActor().getPA().closeAllWindows();
                                    }
                                },
                            new DialogOption(ItemIdContextLoader.getInstance().read((int) loot2.getId()).getName()) {
                                @Override
                                public void onAction() {
                                    getActor().getItems().addItemUnderAnyCircumstance((int) loot2.getId(), (int) loot2.getAmount());
                                    getActor().getPA().closeAllWindows();
                                }
                            })
                    .build());
        }
        this.getActor().getAttributes().getAchievementController().completeAchievement(459819806242196529L);
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
