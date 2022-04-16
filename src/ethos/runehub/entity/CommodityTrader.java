package ethos.runehub.entity;

import ethos.Server;
import ethos.event.Event;
import ethos.model.npcs.NPC;
import ethos.model.npcs.NPCHandler;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.runehub.skill.Skill;
import ethos.world.objects.GlobalObject;
import ethos.world.objects.GlobalObjects;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.LootTable;
import org.runehub.api.model.math.impl.DoubleRange;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//npc 1328
public class CommodityTrader extends Event<Integer> {

    public static final int CURRENCY = 1459;
    public static final int SHOP_ID = 199;
    public static final int[] COMMODITIES = {7472, 2365};

    private final int MAX_STOCK = (int) (COMMODITIES.length + (this.getLootTable().getLootTableEntries().size() * 0.1f));
    private final long LOOT_TABLE_ID = 4225145936390343693L;
    private final int SPAWN_DURATION_TICKS = 50; //how long he stays spawned for
    private final int RESPAWN_COOLDOWN_TICKS = 60; //how long until he respawns

    private static CommodityTrader instance = null;

    public static CommodityTrader getInstance() {
        if (instance == null)
            instance = new CommodityTrader();
        return instance;
    }

    @Override
    public void execute() {
        System.out.println("Executed");
        despawn();
    }

    @Override
    public void initialize() {
        super.initialize();
        spawn();
        System.out.println("Initialized");
    }

    @Override
    public void update() {
        super.update();
        if (this.getElapsedTicks() % RESPAWN_COOLDOWN_TICKS == 0) {
            move();
        }

    }

    public void spawn() {
        final int x = 3075;
        final int y = 3248;
        this.initializeStock();
        NPCHandler.spawn(1328, x, y, 0, 0, 1000, 0, 0, 0, false);
        Server.getGlobalObjects().add(
                new GlobalObject(
                        10457,
                        3075,
                        3245,
                        0,
                        2,
                        10
                )
        );
        PlayerHandler.executeGlobalMessage("^Market $Trader $Stan has a special offer of $" + decimalFormat.format(100 * (1.0 - sale)) + " % off @" + saleItemId + " !");
    }

    private void move() {
        final int x = 3075;
        final int y = 3248;
        this.initializeStock();
        Server.getGlobalObjects().add(
                new GlobalObject(
                        10457,
                        3075,
                        3245,
                        0,
                        2,
                        10
                )
        );
        NPC npc = NPCHandler.getNpc(attachment);
        npc.absX = x;
        npc.absY = y;
        npc.heightLevel = 0;
        npc.forceChat("I've got new goods today!");
//        PlayerHandler.executeGlobalMessage("^Market $Trader $Stan is back at the market!");
        PlayerHandler.executeGlobalMessage("^Market $Trader $Stan has a special offer of $" + decimalFormat.format(100 * (1.0 - sale)) + " % off @" + saleItemId + " !");
    }

    private void despawn() {
        NPC npc = NPCHandler.getNpc(attachment);
        npc.startAnimation(714);
        npc.gfx0(308);
        npc.absX = 3075;
        npc.absY = 3245;
        npc.heightLevel = 1;
        Server.getGlobalObjects().add(
                new GlobalObject(
                        -1,
                        3075,
                        3245,
                        0,
                        2,
                        10
                )
        );
        PlayerHandler.getPlayers().stream().filter(player -> player.myShopId == SHOP_ID)
                .forEach(player -> player.getPA().closeAllWindows());
    }

    public String getAppraisalFor(int removeId) {
        if (Arrays.stream(COMMODITIES).anyMatch(itemId -> itemId == removeId)) {
            return "The Trader will pay #" + CommodityTrader.getInstance().getBuyStockPrice(removeId) + " @"
                    + CURRENCY + " for @" + removeId;
        }
        return "The Trader will not purchase this.";
    }

    public void openShop(Player player) {
        player.getItems().resetItems(3823);
        player.isShopping = true;
        player.myShopId = SHOP_ID;
        player.getPA().sendFrame248(64000, 3822);
        player.getPA().sendFrame126("Commodity Trader's Jewel Shop", 64003);
        player.getPA().sendFrame171(1, 64017);
        player.getOutStream().createFrameVarSizeWord(53);
        player.getOutStream().writeWord(64016);
        player.getOutStream().writeWord(MAX_STOCK);
        stock.forEach(itemId -> {
            System.out.println("Writing Item: " + itemId);
            player.getOutStream().writeByte(1);
            player.getOutStream().writeWordBigEndianA(itemId + 1);
        });
        player.getOutStream().writeWordBigEndianA(0);
        player.getOutStream().endFrameVarSizeWord();
        player.flushOutStream();
    }

    public boolean buyItem(int itemId, int amount, int slot, Player player) {
        final int price = this.getBuyStockPrice(itemId) * amount;
        if (player.getItems().playerHasItem(itemId, amount)) {
            if (player.getItems().freeSlots() > (ItemIdContextLoader.getInstance().read(itemId).isStackable() ? 0 : amount)) {
                player.getItems().deleteItem(itemId, slot, amount);
                player.getItems().addItem(CURRENCY, price);
                player.getItems().resetItems(3823);
                player.sendMessage("The Trader happily takes your #" + amount + " @" + itemId + " in exchange for #"
                        + price + " @" + CURRENCY);
                return true;
            } else {
                player.sendMessage("The Trader shakes his head... 'How can you trade with no inventory space?'");
            }
        } else {
            player.sendMessage("You can't sell what you don't have.");
        }
        return false;
    }

    public boolean sellItem(int itemId, int amount, int slot, Player player) {
        final int price = this.getSellStockPrice(itemId) * amount;
        if (player.getItems().playerHasItem(CURRENCY, price)) {
            if (player.getItems().freeSlots() > (ItemIdContextLoader.getInstance().read(itemId).isStackable() ? 0 : amount)) {
                player.getItems().deleteItem(CURRENCY, price);
                player.getItems().addItem(itemId, amount);
                player.getItems().resetItems(3823);
                player.sendMessage("The Trader hands over #" + amount + " @" + itemId + " for #" + price + " @" + CURRENCY);
                return true;
            } else {
                player.sendMessage("The Trader shakes his head... 'How can you trade with no inventory space?'");
            }
        } else {
            player.sendMessage("Come back when you're a little bit...richer!");
        }
        return false;
    }


    private void initializeStock() {
        final float BASE_MAGIC_FIND = 1.0f;
        stock.clear();
        System.out.println("Max Stock: " + MAX_STOCK);
        this.getLootTable().roll(MAX_STOCK, BASE_MAGIC_FIND).forEach(loot -> {
            if (stock.size() < MAX_STOCK && !stock.contains((int) loot.getId()))
                stock.add((int) loot.getId());
        });
        this.saleItemId = this.getSaleItemId();
        this.sale = this.getSale();
    }

    private double getSale() {
        return new DoubleRange(0.5, 0.9).getRandomValue();
    }

    private int getSaleItemId() {
        int itemId = stock.get(Skill.SKILL_RANDOM.nextInt(stock.size()));
        if (Arrays.stream(COMMODITIES).anyMatch(commodity -> commodity == itemId)) {
            return getSaleItemId();
        }
        return itemId;
    }

    public int getSellStockPrice(int itemId) {
        return itemId == saleItemId ? (int) (ItemIdContextLoader.getInstance().read(itemId).getValue() * sale) :
                ItemIdContextLoader.getInstance().read(itemId).getValue();
    }

    public int getBuyStockPrice(int itemId) {
        return ItemIdContextLoader.getInstance().read(itemId).getValue();
    }

    private LootTable getLootTable() {
        return LootTableLoader.getInstance().read(LOOT_TABLE_ID);
    }

    private CommodityTrader() {
        super(1328, 50);
        this.stock = new ArrayList<>();
    }

    private final List<Integer> stock;
    private final DecimalFormat decimalFormat = new DecimalFormat("##");
    private int saleItemId;
    private double sale;
}
