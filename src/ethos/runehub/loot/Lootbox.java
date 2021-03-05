package ethos.runehub.loot;

import com.google.common.base.Preconditions;
import ethos.model.items.ItemDefinition;
import ethos.model.players.Player;
import org.rhd.api.io.loader.LootContainerContextProducer;
import org.rhd.api.model.*;

import java.util.*;

public class Lootbox extends LootTableContainer {

    private static final int INTERFACE_ID = 47000;
    private static final int ITEM_FRAME = 47101;

    private final Player player;

    public static boolean isLootbox(int id) {
        final int[] lootboxes = {
                11739
        };
        return Arrays.stream(lootboxes).anyMatch(value -> value == id);
    }

    public Lootbox(LootTableContainer container, Player player) {
        super(container.getId(), container.getLootTables());
        this.player = player;
    }

    public void open() {
        try {
            Preconditions.checkArgument(!player.getAttributes().isUsingLootBox(),"Please finish your current spin.");
            Preconditions.checkArgument(player.getItems().playerHasItem(11739),"You're out of lootboxes.");
            player.getItems().deleteItem(this.getId(), 1);
            this.spin();
        } catch (IllegalArgumentException e) {
            player.sendMessage(e.getMessage());
        }
    }

    private List<Loot> spin() {
        this.openInterface();
        player.boxCurrentlyUsing = this.getId();
        player.sendMessage(":spin");
        player.getAttributes().setUsingLootBox(true);
        return this.selectPrize();
    }

    private List<Loot> selectPrize() {
        final Loot loot = this.roll().roll().get(0);
        final List<Loot> items = new ArrayList<>();
        final List<PotentialItem> potentialLoot = new ArrayList<>();

        this.getLootTables().getMap().values().forEach(table -> {
            potentialLoot.addAll(table.getPotentialItems());
        });

        items.add(loot);
        for (int i=0; i<66; i++) {
            final int previousId = potentialLoot.get(new Random().nextInt(potentialLoot.size())).getItemId();
            sendItem(i, 55, loot.getItemId(), previousId,1);
        }
        final String name = ItemDefinition.forId(loot.getItemId()).getName();
        final Tier tier = Tier.getRarityForValue(potentialLoot.stream().filter(potentialItem ->
                potentialItem.getItemId() == loot.getItemId()).findAny().orElseThrow(() -> new NullPointerException("Error")).getRoll());
        final Timer timer = new Timer();
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (name.endsWith("s")) {
                    player.sendMessage("Congratulations, you have won " + "[" + tier + "] " + name + "@bla@!");
                }
                else {
                    player.sendMessage("Congratulations, you have won a " + "[" + tier + "] "  + name + "@bla@!");
                }
                player.getItems().addItem(loot.getItemId(),loot.getAmount());
                player.getAttributes().setUsingLootBox(false);
            }
        };

        timer.schedule(timerTask,12000);
        return items;
    }

    private void clearWheel() {
        player.sendMessage(":resetBox");
        for (int i=0; i<66; i++){
            this.prizeWheelInterface(-1, 1, ITEM_FRAME, i);
        }
    }

    private void sendItem(int i, int prizeSlot, int prizeId, int NOT_PRIZE_ID, int amount) {
        if (i == prizeSlot) {
            this.prizeWheelInterface(prizeId, amount, ITEM_FRAME, i);
        }
        else {
            this.prizeWheelInterface(NOT_PRIZE_ID, amount, ITEM_FRAME, i);
        }
    }

    private void openInterface() {
        // Reset interface
        this.clearWheel();
        // Open
        player.getPA().sendString(LootContainerContextProducer.getInstance(LootContainerType.ITEM).get(this.getId()).getName(), 47002);
        player.getPA().showInterface(INTERFACE_ID);
    }

    public void prizeWheelInterface(int item, int amount , int frame, int slot) {
        if (player.getOutStream() != null) {
            player.getOutStream().createFrameVarSizeWord(34);
            player.getOutStream().writeWord(frame);
            player.getOutStream().writeByte(slot);
            player.getOutStream().writeWord(item + 1);
            player.getOutStream().writeByte(255);
            player.getOutStream().writeDWord(amount);
            player.getOutStream().endFrameVarSizeWord();
        }
    }

    @Override
    public LootTable roll() {
        return this.roll(0.0D);
    }

    @Override
    public LootTable roll(double modifier) {
        return this.getLootTables().next(0.0D);
    }
}
