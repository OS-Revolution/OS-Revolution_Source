package ethos.runehub.economy;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.ItemContextDAO;
import org.runehub.api.io.data.impl.LootTableContainerDAO;
import org.runehub.api.io.data.impl.LootTableDAO;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.model.entity.item.loot.LootTable;
import org.runehub.api.model.entity.item.loot.LootTableContainer;
import org.runehub.api.model.entity.item.loot.LootTableContainerEntry;
import org.runehub.api.model.entity.item.loot.LootTableEntry;
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.model.math.impl.AdjustableLong;
import org.runehub.api.model.math.impl.IntegerRange;
import org.runehub.api.util.APILogger;
import org.runehub.api.util.IDManager;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.*;

public class EconomyEvaluator {

    private static void addDrops() {
        long tableId = IDManager.getUUID(); //Unique table ID for each table
        LootTable drops = new LootTable(tableId,List.of(  //list of all drops on table
                new LootTableEntry(1040,new IntegerRange(1,1),0.0004D) //Item ID, amount as a range 1-1 for 1, chance 1.0 is 100%
        ));
        long containerUUID = IDManager.getUUID();//unique container Id for each container
        LootTableContainer container = new LootTableContainer(containerUUID,100,List.of(//The unique container ID, the npc,object,or item ID of the container, list of tables in the container
                new LootTableContainerEntry(tableId,1.0d)
        ));


        //adds the table to the database
        LootTableDAO.getInstance().create(drops);
        //adds the container to the database
        LootTableContainerDAO.getInstance().create(container);



    }

    public static void main(String[] args) throws Exception {
        APILogger.debug_level = RunehubConstants.DEBUG_LEVEL;
        APILogger.initialize();
        File saveDir = new File("./Characters");
        File[] saves = saveDir.listFiles();
        for (int i = 0; i < saves.length; i++) {
            String contents = Files.readString(Path.of(saves[i].toURI()), Charset.defaultCharset());
            readSave(contents, saves[i].getName());
        }
        System.out.println("\n");
        items.keySet().forEach(itemId -> {
            System.out.println("Item: " + (getItemName(itemId)) + " Amount in game: " + NumberFormat.getInstance().format(items.get(itemId).value()));
        });

        System.out.println("\n");
        long totalEconomicValue = items.keySet().stream().mapToLong(itemId -> getBaseValue(itemId) * items.get(itemId).value()).sum();
        long totalEconomicExchangeValue = items.keySet().stream().mapToLong(itemId -> getExchangeValue(itemId) * items.get(itemId).value()).sum();
        System.out.println("Total Economic Value: " + NumberFormat.getInstance().format(totalEconomicValue));
        System.out.println("Total Economic Exchange Value: " + NumberFormat.getInstance().format(totalEconomicExchangeValue));
        System.out.println("The most abundant item is " + getItemName(getMaxId()) + " with a total of " + NumberFormat.getInstance().format(getMax()));
        System.out.println("[Value] The wealthiest player is " + getWealthiest().getPlayerName() + " with a total wealth of " + NumberFormat.getInstance().format(getWealthiest().getValue()));
        System.out.println("[Exchange] The wealthiest player is " + getWealthiestExchange().getPlayerName() + " with a total wealth of " + NumberFormat.getInstance().format(getWealthiestExchange().getExchangeValue()));
        System.out.println("\n");
        holders.forEach(economyHolder -> {
            System.out.println("[Value]" +economyHolder.getPlayerName() + " has total wealth: " + NumberFormat.getInstance().format(economyHolder.getValue()));
            System.out.println("[Exchange]" +economyHolder.getPlayerName() + " has total wealth: " + NumberFormat.getInstance().format(economyHolder.getExchangeValue()));
            System.out.println(economyHolder.getPlayerName() + " controls " + (getPercent(economyHolder.getValue(), totalEconomicValue)) + "% of the economic value.");
            System.out.println(economyHolder.getPlayerName() + "'s most valuable item is: " + getItemName(economyHolder.getHighestValueItemId()) + " worth " + NumberFormat.getInstance().format(economyHolder.getHighestValueItemBaseValue()));
            System.out.println(economyHolder.getPlayerName() + "'s greatest wealth contributor is: " + getItemName(economyHolder.getGreatestWealthContributorId())
                    + " worth " + NumberFormat.getInstance().format(economyHolder.getGreatestWealthContributorValue())
                    + " which makes up " + (getPercent(economyHolder.getGreatestWealthContributorValue(), economyHolder.getValue())) + "% of their total wealth\n");

        });
    }

    private static String getItemName(int id) {
        try {
            return ItemIdContextLoader.getInstance().read(id).getName() + " (" + id + ")";
        } catch (NullPointerException e) {

        }
        return String.valueOf(id);
    }

    private static EconomyHolder getWealthiest() {
        final AdjustableLong max = new AdjustableLong(0L);
        EconomyHolder holder = null;
        for (int i = 0; i < holders.size(); i++) {
            long val = holders.get(i).getValue();
            if (val > max.value()) {
                max.setValue(val);
                holder = holders.get(i);
            }
        }
        return holder;
    }

    private static EconomyHolder getWealthiestExchange() {
        final AdjustableLong max = new AdjustableLong(0L);
        EconomyHolder holder = null;
        for (int i = 0; i < holders.size(); i++) {
            long val = holders.get(i).getExchangeValue();
            if (val > max.value()) {
                max.setValue(val);
                holder = holders.get(i);
            }
        }
        return holder;
    }

    public static int getMaxId() {
        final AdjustableLong max = new AdjustableLong(0L);
        final AdjustableInteger maxID = new AdjustableInteger(0);
        items.keySet().stream().forEach(id -> {
            long val = items.get(id).value();
            if (val > max.value()) {
                max.setValue(val);
                maxID.setValue(id);
            }
        });
        return maxID.value();
    }

    public static long getMax() {
        final AdjustableLong max = new AdjustableLong(0L);
        final AdjustableInteger maxID = new AdjustableInteger(0);
        items.keySet().stream().forEach(id -> {
            long val = items.get(id).value();
            if (val > max.value()) {
                max.setValue(val);
                maxID.setValue(id);
            }
        });
        return max.value();
    }

    private static double getPercent(long a, long b) {
        return ((double) a / (double) b) * 100.0D;
    }

    public static int getBaseValue(int itemId) {
        if (itemId > 0) {
            try {
                return ItemIdContextLoader.getInstance().read(itemId).getValue();
            } catch (NullPointerException e) {

            }
        }
        return 0;
    }

    public static int getExchangeValue(int itemId) {
        if (itemId > 0) {
            try {
                return ItemIdContextLoader.getInstance().read(itemId).getExchange();
            } catch (NullPointerException e) {

            }
        }
        return 0;
    }

    private static void readSave(String data, String name) {
        String line = "";
        String token = "";
        String token2 = "";
        String[] token3 = new String[3];
        String[] lines = data.split("\n");
        AdjustableInteger lineId = new AdjustableInteger(0);
        final Map<Integer, AdjustableLong> playerItems = new HashMap<>();
        for (int i = 0; i < lines.length; i++) {
            line = lines[lineId.value()].trim();
//            System.out.println("Reading: " + line);
            int spot = line.indexOf("=");
            if (spot > -1) {
                token = line.substring(0, spot);
                token = token.trim();
                token2 = line.substring(spot + 1);
                token2 = token2.trim();
                token3 = token2.split("\t");

                if (token.equals("character-equip")) {
                    int equipmentId = Integer.parseInt(token3[1]);
                    int equipmentAmount = Integer.parseInt(token3[2]);
                    addItem(equipmentId, equipmentAmount);
                    addItem(equipmentId, equipmentAmount, playerItems);
                }

                if (token.equals("character-item")) {
                    int inventoryItemId = Integer.parseInt(token3[1]) - 1;
                    int inventoryItemAmount = Integer.parseInt(token3[2]);
                    addItem(inventoryItemId, inventoryItemAmount);
                    addItem(inventoryItemId, inventoryItemAmount, playerItems);
                }

                if (token.equals("bag-item")) {
                    int lootingBagItemId = Integer.parseInt(token3[1]);
                    int lootingBagItemAmount = Integer.parseInt(token3[2]);
                    addItem(lootingBagItemId, lootingBagItemAmount);
                    addItem(lootingBagItemId, lootingBagItemAmount, playerItems);
                }

                if (token.equals("character-bank")) {
                    int bankItemId = Integer.parseInt(token3[1]) - 1;
                    int bankItemAmount = Integer.parseInt(token3[2]);
                    addItem(bankItemId, bankItemAmount);
                    addItem(bankItemId, bankItemAmount, playerItems);
                } else if (token.equals("bank-tab")) {
                    int itemId = Integer.parseInt(token3[1]) - 1;
                    int itemAmount = Integer.parseInt(token3[2]);
                    addItem(itemId, itemAmount);
                    addItem(itemId, itemAmount, playerItems);
                }
//                if (line.equals("[EOF]")) {
//                    EndOfFile = true;
//                }

            }
            lineId.increment();
        }
        holders.add(new EconomyHolder(name, playerItems));
    }

    private static void addItem(int id, int amount) {
        if (items.containsKey(id)) {
            items.get(id).add((long) amount);
        } else {
            items.put(id, new AdjustableLong((long) amount));
        }
    }

    private static void addItem(int id, int amount, Map<Integer, AdjustableLong> map) {
        if (map.containsKey(id)) {
            map.get(id).add((long) amount);
        } else {
            map.put(id, new AdjustableLong((long) amount));
        }
    }


    private static final Map<Integer, AdjustableLong> items = new HashMap<>();
    private static final List<EconomyHolder> holders = new ArrayList<>();

}
