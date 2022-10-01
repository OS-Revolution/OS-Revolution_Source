package ethos.runehub.loot;

import com.google.common.base.Preconditions;
import ethos.model.items.ItemDefinition;
import ethos.model.players.Player;
import ethos.util.PreconditionUtils;
import org.runehub.api.io.data.impl.LootTableContainerDAO;
import org.runehub.api.io.load.impl.LootTableContainerDefinitionLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.*;

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

    private static LootTableContainer getLootTableContainer(int containerId) {
        return LootTableContainerDAO.getInstance().getAllEntries().stream().filter(lootTableContainer -> lootTableContainer.getContainerId() == containerId)
                .findAny().orElseThrow();
    }

    public Lootbox(int containerId, Player player) {
        super(getLootTableContainer(containerId).getId(), containerId, getLootTableContainer(containerId).getLootTables());
        this.player = player;
        this.lootTable = new ArrayList<>(this.roll(player.getAttributes().getMagicFind())).get(0);
        this.prizePool = new ArrayList<>();
    }

    private void initPrizePool() {
        prizePool.clear();
        this.getLootTables().forEach(table -> {
            LootTableLoader.getInstance().read(table.getId()).getLootTableEntries().forEach(lootTableEntry -> {
                prizePool.add(new Loot(lootTableEntry.getId(), lootTableEntry.getAmount().getRandomValue(), lootTableEntry.getChance(), table.getId()));
            });
        });
        prize = this.getPrize();
    }

    public void open() {
        try {
            Preconditions.checkArgument(PreconditionUtils.isNull(player.getAttributes().getActiveLootBox()), "Please finish your current spin.");
            Preconditions.checkArgument(player.getItems().playerHasItem(this.getContainerId()), "You're out of lootboxes.");
            this.initPrizePool();
            this.openInterface();
            this.drawPrizes();
            player.getAttributes().setActiveLootBox(this);

//            this.spin();
        } catch (IllegalArgumentException e) {
            player.sendMessage(e.getMessage());
        }
    }

    public void close() {
        player.getPA().closeAllWindows();
        player.getAttributes().setActiveLootBox(null);
    }

    public List<Loot> spin() {
//        this.openInterface();
        player.boxCurrentlyUsing = this.getContainerId();
        player.sendPlainMessage(":spin");
//        player.getAttributes().setActiveLootBox(this);
        player.getItems().deleteItem(this.getContainerId(), 1);
        return this.selectPrize(prize);
    }

    private Loot getPrize() {
//        new ArrayList<>(lootable.roll(player.getAttributes().getMagicFind())).get(0);
        final Loot loot = new ArrayList<>(LootTableLoader.getInstance().read(lootTable.getId()).roll(player.getAttributes().getMagicFind())).get(0);
        return loot;
    }

    private Tier getPrizeTier(Loot loot) {
        return Tier.getTier(LootTableLoader.getInstance().read(lootTable.getId()).getLootTableEntries().stream().filter(potentialItem ->
                potentialItem.getId() == loot.getId()).findAny().orElseThrow(() -> new NullPointerException("Error")).getChance());
    }

    private void drawPrizes() {

        int prizeSlot = 55;
        for (int i = 0; i < 66; i++) {
//            LootTableEntry entry = potentialLoot.get(random.nextInt(potentialLoot.size()));
//            Loot item = new Loot(entry.getId(),entry.getAmount().getRandomValue(),entry.getChance(),lootTable.getSourceId());
//            final int previousId = (int) item.getId();
            int prizeIndex = random.nextInt(prizePool.size());
            if (i != prizeSlot)
                sendItem(i, prizeSlot, (int) prize.getId(), (int) prizePool.get(prizeIndex).getId(), (int) prizePool.get(prizeIndex).getAmount());
            else
                sendItem(i, prizeSlot, (int) prize.getId(), (int) prizePool.get(prizeIndex).getId(), (int) prize.getAmount());
        }
    }

    private List<Loot> selectPrize(Loot loot) {

//        final Loot lootTable = new ArrayList<>(this.roll(player.getAttributes().getMagicFind())).get(0);
//        final Loot loot = new ArrayList<>(LootTableLoader.getInstance().read(lootTable.getId()).roll(player.getAttributes().getMagicFind())).get(0);
        final List<Loot> items = new ArrayList<>();
//        final List<LootTableEntry> potentialLoot = new ArrayList<>();
//
//        this.getLootTables().forEach(table -> {
//            potentialLoot.addAll(LootTableLoader.getInstance().read(table.getId()).getLootTableEntries());
//        });

        items.add(loot);
//        for (int i=0; i<66; i++) {
//            final int previousId = potentialLoot.get(new Random().nextInt(potentialLoot.size())).getId();
//            sendItem(i, 55, (int) loot.getId(), previousId,1);
//        }

        final String name = ItemDefinition.forId((int) loot.getId()).getName();
        final Tier tier = getPrizeTier(loot);
        final long amount = loot.getAmount();
        final Timer timer = new Timer();
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (name.endsWith("s") || amount > 1) {
                    player.sendMessage("Congratulations, you have won " + "[" + tier + "] " + name + " x" + amount + "@bla@!");
                } else {
                    player.sendMessage("Congratulations, you have won a " + "[" + tier + "] " + name + " x" + amount + "@bla@!");
                }
                player.getItems().addItem((int) loot.getId(), (int) loot.getAmount());
                player.getItems().updateItems();
                player.getAttributes().setActiveLootBox(null);
                player.getPA().closeAllWindows();
            }
        };

        timer.schedule(timerTask, 12000);
        return items;
    }

    private void clearWheel( ) {
        player.sendPlainMessage(":resetBox");
        for (int i = 0; i < 66; i++) {
            this.prizeWheelInterface(-1, 1, ITEM_FRAME, i);
        }
    }

    private void sendItem(int i, int prizeSlot, int prizeId, int NOT_PRIZE_ID, int amount) {
        if (i == prizeSlot) {
            this.prizeWheelInterface(prizeId, amount, ITEM_FRAME, i);
        } else {
            this.prizeWheelInterface(NOT_PRIZE_ID, amount, ITEM_FRAME, i);
        }
    }

    private void openInterface() {
        // Reset interface
        this.clearWheel();
        // Open
        player.getPA().sendString(LootTableContainerDefinitionLoader.getInstance().read(this.getId()).getName(), 47002);
        player.getPA().showInterface(INTERFACE_ID);
    }

    public void prizeWheelInterface(int item, int amount, int frame, int slot) {
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

    private final Loot lootTable;
    private Loot prize;
    private final Random random = new Random();
    private List<Loot> prizePool;
}
