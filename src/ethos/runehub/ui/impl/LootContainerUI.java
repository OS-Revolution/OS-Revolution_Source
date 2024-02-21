package ethos.runehub.ui.impl;

import ethos.Server;
import ethos.model.players.Player;
import ethos.runehub.event.OpenLootContainerEvent;
import ethos.runehub.skill.Skill;
import ethos.runehub.ui.GameUI;
import ethos.runehub.ui.component.button.Button;
import ethos.runehub.ui.component.impl.TextComponent;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.io.load.impl.LootTableContainerDefinitionLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.GameItem;
import org.runehub.api.model.entity.item.loot.Loot;
import org.runehub.api.model.entity.item.loot.LootTable;
import org.runehub.api.model.entity.item.loot.LootTableContainer;
import org.runehub.api.model.math.impl.IntegerRange;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class LootContainerUI extends GameUI {

    @Override
    protected void onOpen() {
        this.clearWheel();
        this.drawItems();
        spin1XTextComponent.setText("Open");
        infoTextComponent.setText(LootTableContainerDefinitionLoader.getInstance().read(lootTableContainer.getId()).getDescription());
        button2TextComponent.setText("Open All");
        titleTextComponent.setText(LootTableContainerDefinitionLoader.getInstance().read(lootTableContainer.getId()).getName());
        this.registerButton(actionEvent -> spin(1), 183156);
        this.registerButton(actionEvent -> {
            if (this.getPlayer().getAttributes().isMember())
                spin(this.getPlayer().getItems().getItemAmount(consumedItem.getId()) / consumedItem.getAmount());
            else
                this.getPlayer().sendMessage("You must be a member to do this.");
        }, 183164);
        this.registerButton(new UICloseActionListener(), 183155);
        this.writeLine(titleTextComponent);
        this.writeLine(spin1XTextComponent);
        this.writeLine(button2TextComponent);
        this.writeLine(infoTextComponent);
    }

    @Override
    protected void onClose() {
        Server.getEventHandler().stop("lootbox");
//        clearWheel();
    }

    @Override
    protected void onEvent() {

    }

    private void spin(int times) {
        Server.getEventHandler().stop("lootbox");
        this.getPlayer().boxCurrentlyUsing = lootTableContainer.getContainerId();
        this.getPlayer().sendPlainMessage(":spin");
        final List<Loot> rewards = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            if (i == 0) {
                rewards.add(lootPool.get(55));
            } else {
                rewards.add(getLootPool().get(55));
            }

        }
        Server.getEventHandler().submit(new OpenLootContainerEvent(getPlayer(), rewards, consumedItem));
    }

    private void drawItems() {
        for (int i = 0; i < lootPool.size(); i++) {
            Loot loot = lootPool.get(i);
            sendItem(i, (int) loot.getId(), (int) loot.getAmount());
        }
    }

    private void clearWheel() {
//        this.getPlayer().sendPlainMessage(":resetBox");
        for (int i = 0; i < 66; i++) {
            this.sendItemToUI(-1, 1, i);
        }
    }

    private void sendItem(int slot, int itemId, int amount) {
        this.sendItemToUI(itemId, amount, slot);
    }

    private void sendItemToUI(int item, int amount, int slot) {
        if (this.getPlayer().getOutStream() != null) {
            this.getPlayer().getOutStream().createFrameVarSizeWord(34);
            this.getPlayer().getOutStream().writeWord(47101);
            this.getPlayer().getOutStream().writeByte(slot);
            this.getPlayer().getOutStream().writeWord(item + 1);
            this.getPlayer().getOutStream().writeByte(255);
            this.getPlayer().getOutStream().writeDWord(amount);
            this.getPlayer().getOutStream().endFrameVarSizeWord();
        }
    }

    private List<LootTable> getRolledLootTable() {
        return lootTableContainer.roll(this.getPlayer().getAttributes().getMagicFind()).stream().map(loot -> LootTableLoader.getInstance().read(loot.getId())).collect(Collectors.toList());
    }

    private List<Loot> getAllLoot() {
        final List<Loot> items = new ArrayList<>();
        this.getRolledLootTable().forEach(lootTable -> {
            lootTable.getLootTableEntries().forEach(lootTableEntry -> {
                items.add(new Loot(lootTableEntry.getId(), lootTableEntry.getAmount().getRandomValue(), lootTableEntry.getChance(), lootTable.getId()));
            });
        });
        return items;
    }

    private List<Loot> getRolledLoot() {
        final List<Loot> items = new ArrayList<>();
        this.getRolledLootTable().forEach(lootTable -> {
            items.addAll(lootTable.roll(this.getPlayer().getAttributes().getMagicFind()));
        });
        return items;
    }

    private List<Loot> getLootPool() {
        final List<Loot> items = new ArrayList<>();
        final List<Loot> allItem = this.getAllLoot();
        final List<Loot> rolledLoot = this.getRolledLoot();
        if (!rolledLoot.isEmpty()) {
            for (int i = 0; i < 66; i++) {
                if (i == 55) {
                    items.add(rolledLoot.get(0));
                } else if (!allItem.isEmpty()){
                    items.add(allItem.get(new IntegerRange(0,allItem.size() - 1).getRandomValue()));
                } else {
                    this.getPlayer().sendMessage("Something went wrong. Please try again.");
                    Logger.getGlobal().severe("Failed to open Shiny Chest item pool was empty.");
                    break;
                }
            }
        } else {
            return getLootPool();
        }
        return items;
    }


    public LootContainerUI(Player player, LootTableContainer lootTableContainer, GameItem consumeItem) {
        super(47000, player);
        this.lootTableContainer = lootTableContainer;
        this.lootPool = getLootPool();
        this.consumedItem = consumeItem;
        this.titleTextComponent = new TextComponent(47002);
        this.spin1XTextComponent = new TextComponent(47005);
        this.headerTextComponent = new TextComponent(47008);
        this.infoTextComponent = new TextComponent(47009);
        this.spin1XButton = new Button(47004);
        this.button2TextComponent = new TextComponent(47013);
    }

    private final List<Loot> lootPool;
    private final LootTableContainer lootTableContainer;
    private final GameItem consumedItem;

    private final TextComponent titleTextComponent, spin1XTextComponent, headerTextComponent, infoTextComponent,button2TextComponent;
    private final Button spin1XButton;
}
