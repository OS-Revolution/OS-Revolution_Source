package ethos.model.npcs.drops;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ethos.Server;
import ethos.model.content.godwars.Godwars;
import ethos.model.items.GameItem;
import ethos.model.items.Item;
import ethos.model.items.ItemAssistant;
import ethos.model.npcs.NPC;
import ethos.model.npcs.NPCDefinitions;
import ethos.model.npcs.NPCHandler;
import ethos.model.players.Boundary;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.model.players.Right;
import ethos.model.players.skills.slayer.SlayerMaster;
import ethos.model.players.skills.slayer.Task;
import ethos.net.discord.DiscordMessager;
import ethos.util.Location3D;
import ethos.util.Misc;

import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DropManager {


    public static int AMOUNT_OF_TABLES = 0;

    private static final Comparator<Integer> COMPARE_NAMES = (o1, o2) -> {
        String name1 = NPCDefinitions.get(o1).getNpcName();
        String name2 = NPCDefinitions.get(o2).getNpcName();
        return name1.compareToIgnoreCase(name2);
    };

    private Map<List<Integer>, TableGroup> groups = new HashMap<>();

    private List<Integer> ordered = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public void read() {
        JSONParser parser = new JSONParser();
        try {
            fileReader = new FileReader("./Data/json/npc_droptable.json");
            JSONArray data = (JSONArray) parser.parse(fileReader);
            for (Object aData : data) {
                JSONObject drop = (JSONObject) aData;
                List<Integer> npcIds = new ArrayList<>();
                if (drop.get("npc_id") instanceof JSONArray) {
                    JSONArray idArray = (JSONArray) drop.get("npc_id");
                    idArray.forEach(id -> npcIds.add(((Long) id).intValue()));
                } else {
                    npcIds.add(((Long) drop.get("npc_id")).intValue());
                }
                TableGroup group = new TableGroup(npcIds);
                for (TablePolicy policy : TablePolicy.POLICIES) {
                    if (!drop.containsKey(policy.name().toLowerCase())) {
                        continue;
                    }
                    JSONObject dropTable = (JSONObject) drop.get(policy.name().toLowerCase());
                    Table table = new Table(policy, ((Long) dropTable.get("accessibility")).intValue());
                    JSONArray tableItems = (JSONArray) dropTable.get("items");
                    for (Object tableItem : tableItems) {
                        JSONObject item = (JSONObject) tableItem;
                        int id = ((Long) item.get("item")).intValue();
                        int minimumAmount = ((Long) item.get("minimum")).intValue();
                        int maximumAmount = ((Long) item.get("maximum")).intValue();
                        table.add(new Drop(npcIds, id, minimumAmount, maximumAmount));
                    }
                    group.add(table);
                }
                groups.put(npcIds, group);
            }
            ordered.clear();

            for (TableGroup group : groups.values()) {
                if (group.getNpcIds().size() == 1) {
                    ordered.add(group.getNpcIds().get(0));
                    continue;
                }
                for (int id : group.getNpcIds()) {
                    String name = NPCDefinitions.get(id).getNpcName();
                    if (ordered.stream().noneMatch(i -> NPCDefinitions.get(i).getNpcName().equals(name))) {
                        ordered.add(id);
                    }
                }
            }

            ordered.sort(COMPARE_NAMES);
            Misc.println("Loaded " + ordered.size() + " drop tables.");
            AMOUNT_OF_TABLES = ordered.size();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Attempts to create a drop for a player after killing a non-playable character
     *
     * @param player the player receiving a possible drop
     * @param npc the npc dropping the items
     */
    static boolean test = false;

    static int[] bosses = {
            /* Misc bosses */
            6619, 6618, 6615, 6766, 963, 965, 5890, 6609, 319, 6610, 6611, 5779, 6342, 2205, 2215, 3129, 3162, 2054, 2265, 2266, 2267,
            7544, 7604, 7605, 7606,
            /* Godwars minions */
            2206, 2207, 2208, 3130, 3131, 3132, 2216, 2217, 2218, 3163, 3164, 3165
    };

    public void testOpen(Player player) {
        for (int i = 0; i < 100; i++) {
            player.getPA().sendFrame126("", (33008 + i));
        }
        for (int index = 0; index < ordered.size(); index++) {
            player.getPA().sendFrame126(StringUtils.capitalize(NPCDefinitions.get(ordered.get(index)).getNpcName().toLowerCase().replaceAll("_", " ")), 33008 + index);
        }

        player.getPA().showInterface(33000);
    }

//    public void create(Player player, Location3D location, Loot loot) {
//        Server.itemHandler.createGroundItem(player, loot.getItemId(), location.getX(), location.getY(),
//                location.getZ(), loot.getAmount(), player.getIndex());
//    }

    public void create(Player player, NPC npc, Location3D location, int repeats) {
        Optional<TableGroup> group = groups.values().stream().filter(g -> g.getNpcIds().contains(npc.npcType)).findFirst();

        group.ifPresent(g -> {
            double modifier = getModifier(player);
            List<GameItem> drops = g.access(player, modifier, repeats);

            for (GameItem item : drops) {
                if (item.getId() == 536) {
                    if (player.getRechargeItems().hasItem(13111)) {
                        item.changeDrop(537, item.getAmount());
                    }
                    if (player.getRechargeItems().hasItem(13103)) {
                        item.changeDrop(537, (int) (item.getAmount() * 2.0));
                    }
                }
                if (item.getId() == 526) {
                    if (player.getRechargeItems().hasItem(13103)) {
                        item.changeDrop(527, (int) (item.getAmount() * 2.0));
                    }
                }
                if (item.getId() == 530) {
                    if (player.getRechargeItems().hasItem(13103)) {
                        item.changeDrop(531, (int) (item.getAmount() * 2.0));
                    }
                }
                if (item.getId() == 532) {
                    if (player.getRechargeItems().hasItem(13103)) {
                        item.changeDrop(533, (int) (item.getAmount() * 2.0));
                    }
                }
                if (item.getId() == 534) {
                    if (player.getRechargeItems().hasItem(13103)) {
                        item.changeDrop(535, (int) (item.getAmount() * 2.0));
                    }
                }
                if (item.getId() == 2859) {
                    if (player.getRechargeItems().hasItem(13103)) {
                        item.changeDrop(2860, (int) (item.getAmount() * 2.0));
                    }
                }
                if (item.getId() == 3125) {
                    if (player.getRechargeItems().hasItem(13103)) {
                        item.changeDrop(3126, (int) (item.getAmount() * 2.0));
                    }
                }
                if (item.getId() == 4812) {
                    if (player.getRechargeItems().hasItem(13103)) {
                        item.changeDrop(4813, (int) (item.getAmount() * 2.0));
                    }
                }
                if (item.getId() == 4834) {
                    if (player.getRechargeItems().hasItem(13103)) {
                        item.changeDrop(4835, (int) (item.getAmount() * 2.0));
                    }
                }
                if (item.getId() == 6729) {
                    if (player.getRechargeItems().hasItem(13103)) {
                        item.changeDrop(6730, (int) (item.getAmount() * 2.0));
                    }
                }
                if (item.getId() == 6812) {
                    if (player.getRechargeItems().hasItem(13103)) {
                        item.changeDrop(6816, (int) (item.getAmount() * 2.0));
                    }
                }
                if (item.getId() == 11943) {
                    if (player.getRechargeItems().hasItem(13103)) {
                        item.changeDrop(11944, (int) (item.getAmount() * 2.0));
                    }
                }
                if (item.getId() == 6529) {
                    if (player.getRechargeItems().hasItem(11136)) {
                        item.changeDrop(6529, (int) (item.getAmount() * 1.20));
                    }
                    if (player.getRechargeItems().hasItem(11138)) {
                        item.changeDrop(6529, (int) (item.getAmount() * 1.50));
                    }
                    if (player.getRechargeItems().hasItem(11140)) {
                        item.changeDrop(6529, (int) (item.getAmount() * 1.70));
                    }
                    if (player.getRechargeItems().hasItem(13103)) {
                        item.changeDrop(6529, (int) (item.getAmount() * 2.0));
                    }
                }
                if (item.getId() == 6729 && player.getRechargeItems().hasItem(13132)) {
                    item.changeDrop(6730, item.getAmount());
                }
                if (item.getId() == 13233 && !Boundary.isIn(player, Boundary.CERBERUS_BOSSROOMS)) {
                    player.sendMessage("@red@Something hot drops from the body of your vanquished foe");
                }

                if (IntStream.of(bosses).anyMatch(id -> id == npc.npcType)) {
                    PlayerHandler.nonNullStream()
                            .filter(p -> p.distanceToPoint(player.absX, player.absY) < 10 && p.heightLevel == player.heightLevel)
                            .forEach(p -> {
                                if (item.getAmount() > 1)
                                    p.sendMessage("@red@" + Misc.formatPlayerName(player.playerName) + " received a drop: " + Misc.format(item.getAmount()) + " x " + Item.getItemName(item.getId()) + ".");
                                else
                                    p.sendMessage("@red@" + Misc.formatPlayerName(player.playerName) + " received a drop: " + Item.getItemName(item.getId()) + ".");
                            });
                }
                if (player.getItems().isWearingItem(6040) || Boundary.isIn(player, Boundary.NECRO)) {
                    player.getItems().addItemToBank(item.getId(), item.getAmount());
                } else {
                    Server.itemHandler.createGroundItem(player, item.getId(), location.getX(), location.getY(),
                            location.getZ(), item.getAmount(), player.getIndex());
                }
            }

            /**
             * Looting bag and rune pouch
             */
            if (npc.inWild()) {
                switch (Misc.random(60)) {
                    case 2:
                        if (player.getItems().getItemCount(11941, true) < 1) {
                            Server.itemHandler.createGroundItem(player, 11941, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                            player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                            player.sendAudio(436);
                        }
                        break;

                    case 8:
                        if (player.getItems().getItemCount(12791, true) < 1) {
                            Server.itemHandler.createGroundItem(player, 12791, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                            player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                            player.sendAudio(436);
                        }
                        break;
                }
                switch (Misc.random(600)) {
                    case 1:
                        Server.itemHandler.createGroundItem(player, 21807, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                        player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                        player.sendMessage("@red@A Relic level one has dropped from your foe!");
                        player.sendAudio(436);
                }
                switch (Misc.random(800)) {
                    case 1:
                        Server.itemHandler.createGroundItem(player, 21810, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                        player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                        player.sendMessage("@red@A Relic level two has dropped from your foe!");
                        player.sendAudio(436);
                }
                switch (Misc.random(1000)) {
                    case 1:
                        Server.itemHandler.createGroundItem(player, 21813, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                        player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                        player.sendMessage("@red@A Relic level three has dropped from your foe!");
                        player.sendAudio(436);
                }
                switch (Misc.random(200)) {
                    case 1:
                        Server.itemHandler.createGroundItem(player, 12746, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                        player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                        player.sendMessage("@red@A mysterious emblem has dropped from your foe!");
                        player.sendAudio(436);
                }
                switch (Misc.random(300)) {
                    case 1:
                        Server.itemHandler.createGroundItem(player, 12748, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                        player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                        player.sendMessage("@red@A mysterious emblem has dropped from your foe!");
                        player.sendAudio(436);
                }
                switch (Misc.random(400)) {
                    case 1:
                        Server.itemHandler.createGroundItem(player, 12749, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                        player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                        player.sendMessage("@red@A mysterious emblem has dropped from your foe!");
                        player.sendAudio(436);
                }
                switch (Misc.random(500)) {
                    case 1:
                        Server.itemHandler.createGroundItem(player, 12750, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                        player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                        player.sendMessage("@red@A mysterious emblem has dropped from your foe!");
                        player.sendAudio(436);
                }
                switch (Misc.random(600)) {
                    case 1:
                        Server.itemHandler.createGroundItem(player, 12751, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                        player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                        player.sendMessage("@red@A mysterious emblem has dropped from your foe!");
                        player.sendAudio(436);
                }
            }
            /**
             * Slayer's staff enchantment and Emblems
             */
            Optional<Task> task = player.getSlayer().getTask();
            Optional<SlayerMaster> myMaster = SlayerMaster.get(player.getSlayer().getMaster());
            task.ifPresent(t -> {
                String name = npc.getDefinition().getNpcName().toLowerCase().replaceAll("_", " ");

                if (name.equals(t.getPrimaryName()) || ArrayUtils.contains(t.getNames(), name)) {
                    myMaster.ifPresent(m -> {
                        if (npc.inWild() && m.getId() == 7663) {
                            int slayerChance = 650;
                            int emblemChance1 = 200;
                            int emblemChance2 = 250;
                            int emblemChance3 = 300;
                            int emblemChance4 = 350;
                            int emblemChance5 = 400;
                            int relicLevel1 = 400;
                            int relicLevel2 = 600;
                            int relicLevel3 = 800;

                            if (Misc.random(relicLevel1) == 1) {
                                Server.itemHandler.createGroundItem(player, 21807, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                                player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                                player.sendMessage("@red@A Relic level one has dropped from your foe!");
                                player.sendAudio(436);
                            }
                            if (Misc.random(relicLevel2) == 1) {
                                Server.itemHandler.createGroundItem(player, 21807, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                                player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                                player.sendMessage("@red@A Relic level two has dropped from your foe!");
                                player.sendAudio(436);
                            }
                            if (Misc.random(relicLevel3) == 1) {
                                Server.itemHandler.createGroundItem(player, 21807, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                                player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                                player.sendMessage("@red@A Relic level three has dropped from your foe!");
                                player.sendAudio(436);
                            }
                            if (Misc.random(emblemChance1) == 1) {
                                Server.itemHandler.createGroundItem(player, 12746, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                                player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                                player.sendMessage("@red@A mysterious emblem has dropped from your foe!");
                                player.sendAudio(436);
                            }
                            if (Misc.random(emblemChance2) == 1) {
                                Server.itemHandler.createGroundItem(player, 12748, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                                player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                                player.sendMessage("@red@A mysterious emblem has dropped from your foe!");
                                player.sendAudio(436);
                            }
                            if (Misc.random(emblemChance3) == 1) {
                                Server.itemHandler.createGroundItem(player, 12749, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                                player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                                player.sendMessage("@red@A mysterious emblem has dropped from your foe!");
                                player.sendAudio(436);
                            }
                            if (Misc.random(emblemChance4) == 1) {
                                Server.itemHandler.createGroundItem(player, 12750, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                                player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                                player.sendMessage("@red@A mysterious emblem has dropped from your foe!");
                                player.sendAudio(436);
                            }
                            if (Misc.random(emblemChance5) == 1) {
                                Server.itemHandler.createGroundItem(player, 12751, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                                player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                                player.sendMessage("@red@A mysterious emblem has dropped from your foe!");
                                player.sendAudio(436);
                            }
                            if (Misc.random(slayerChance) == 1) {
                                Server.itemHandler.createGroundItem(player, 21257, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                                player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                                player.sendMessage("@red@A slayer's enchantment has dropped from your foe!");
                                player.sendAudio(436);
                                PlayerHandler.executeGlobalMessage(
                                        "@red@" + Misc.capitalize(player.playerName) + " received a drop: Slayer's Enchantment.</col>.");
                                PlayerHandler.executeGlobalMessage("<col=FF0000>[Lootations] @cr19@ </col><col=255>" + Misc.capitalize(player.playerName) + "</col> received a <col=255>Slayer's Enchantment</col>.");
                                DiscordMessager.sendLootations("[Lootations] " + Misc.capitalize(player.playerName) + " received a Slayer's Enchantment.");
                            }
                        }
                    });
                }
            });

            /**
             * Clue scrolls
             */
            int chance = player.getRechargeItems().hasItem(13118) ? 115 : player.getRechargeItems().hasItem(13119) ? 110 : player.getRechargeItems().hasItem(13120) ? 100 : 120;
            if (Misc.random(chance) == 1) {
                player.sendMessage("You sense a @red@clue scroll @bla@being dropped to the ground.");
                if (npc.getDefinition().getNpcCombat() > 0 && npc.getDefinition().getNpcCombat() <= 70) {
                    Server.itemHandler.createGroundItem(player, 2677, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                    player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                    player.sendAudio(436);
                }
                if (npc.getDefinition().getNpcCombat() > 70 && npc.getDefinition().getNpcCombat() <= 110) {
                    Server.itemHandler.createGroundItem(player, 2801, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                    player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                    player.sendAudio(436);
                }
                if (npc.getDefinition().getNpcCombat() > 110) {
                    Server.itemHandler.createGroundItem(player, 2722, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                    player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                    player.sendAudio(436);
                }
            }


            /**
             * Runecrafting pouches
             */
            if (Misc.random(100) == 10) {
                if (npc.getDefinition().getNpcCombat() >= 70 && npc.getDefinition().getNpcCombat() <= 100 && player.getItems().getItemCount(5509, true) == 1 && player.getItems().getItemCount(5510, true) != 1) {
                    Server.itemHandler.createGroundItem(player, 5510, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                    player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                    player.sendAudio(436);
                } else if (npc.getDefinition().getNpcCombat() > 100 && player.getItems().getItemCount(5510, true) == 1 && player.getItems().getItemCount(5512, true) != 1) {
                    Server.itemHandler.createGroundItem(player, 5512, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                    player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                    player.sendAudio(436);
                }
            }

            /**
             * Crystal keys
             */
            if (Misc.random(115) == 1) {
                player.sendMessage("@bla@You sense a @red@crystal key @bla@being dropped to the ground.");
                Server.itemHandler.createGroundItem(player, 989, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                player.sendAudio(436);
            }

            /**
             * Ecumenical Keys
             */
            if (Boundary.isIn(npc, Boundary.WILDERNESS_GOD_WARS_BOUNDARY)) {
                if (Misc.random(60 + 10 * player.getItems().getItemCount(Godwars.KEY_ID, true)) == 1) {
                    /**
                     * Key will not drop if player owns more than 3 keys already
                     */
                    int key_amount = player.getDiaryManager().getWildernessDiary().hasCompleted("ELITE") ? 6 : 3;
                    if (player.getItems().getItemCount(Godwars.KEY_ID, true) > key_amount) {
                        return;
                    }
                    Server.itemHandler.createGroundItem(player, Godwars.KEY_ID, npc.getX(), npc.getY(), player.heightLevel, 1, player.getIndex());
                    player.sendMessage("@pur@An Ecumenical Key drops from your foe.");
                }
            }

            /**
             * Dark Light
             */
            if (Boundary.isIn(npc, Boundary.CATACOMBS)) {
                if (Misc.random(1000) == 1) {

                    //PlayerHandler.executeGlobalMessage("<col=FF0000>[Lootations] @cr19@ </col><col=255>"+ Misc.capitalize(player.playerName) + "</col> received a <col=255>Darklight!</col>.");
                    Server.itemHandler.createGroundItem(player, 6746, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                    player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                    player.sendAudio(436);
                }
            }

            /**
             * Dark totem Pieces
             */
            if (Boundary.isIn(npc, Boundary.CATACOMBS)) {
                switch (Misc.random(25)) {
                    case 1:
                        if (player.getItems().getItemCount(19679, false) < 1) {
                            Server.itemHandler.createGroundItem(player, 19679, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                            player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                            player.sendMessage("@red@A surge of dark energy fills your body as you notice something on the ground.");
                            player.sendAudio(436);
                        }
                        break;

                    case 2:
                        if (player.getItems().getItemCount(19681, false) < 1) {
                            Server.itemHandler.createGroundItem(player, 19681, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                            player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                            player.sendMessage("@red@A surge of dark energy fills your body as you notice something on the ground.");
                            player.sendAudio(436);
                        }
                        break;


                    case 3:
                        if (player.getItems().getItemCount(19683, false) < 1) {
                            Server.itemHandler.createGroundItem(player, 19683, location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                            player.getPA().createPlayersStillGfx(385, location.getX(), location.getY(), location.getZ(), player.getIndex());
                            player.sendMessage("@red@A surge of dark energy fills your body as you notice something on the ground.");
                            player.sendAudio(436);
                        }
                        break;
                }
            }
        });
    }

    private double getModifier(Player player) {
        double modifier = 1.0;
//        if (player.getMode().isOsrs()) {
//            modifier -= .15;
//        }
        if (player.prestigePoints >= 1 && player.prestigePoints < 10) {
            modifier -= .0075;
        } else if (player.prestigePoints >= 10 && player.prestigePoints < 25) {
            modifier -= .01;
        } else if (player.prestigePoints >= 25 && player.prestigePoints < 50) {
            modifier -= .015;
        } else if (player.prestigePoints >= 50 && player.prestigePoints < 100) {
            modifier -= .02;
        } else if (player.prestigePoints >= 100 && player.prestigePoints < 150) {
            modifier -= .0275;
        } else if (player.prestigePoints >= 150 && player.prestigePoints < 200) {
            modifier -= .035;
        } else if (player.prestigePoints >= 200 && player.prestigePoints < 250) {
            modifier -= .0425;
        } else if (player.prestigePoints >= 250 && player.prestigePoints < 300) {
            modifier -= .05;
        } else if (player.prestigePoints >= 300 && player.prestigePoints < 350) {
            modifier -= .0575;
        } else if (player.prestigePoints >= 350 && player.prestigePoints < 400) {
            modifier -= .065;
        } else if (player.prestigePoints >= 400 && player.prestigePoints < 450) {
            modifier -= .0725;
        } else if (player.prestigePoints >= 450 && player.prestigePoints < 500) {
            modifier -= .08;
        } else if (player.prestigePoints >= 500 && player.prestigePoints < 550) {
            modifier -= .0875;
        } else if (player.prestigePoints >= 550 && player.prestigePoints < 600) {
            modifier -= .095;
        } else if (player.prestigePoints >= 600 && player.prestigePoints < 650) {
            modifier -= .1025;
        } else if (player.prestigePoints >= 650 && player.prestigePoints < 700) {
            modifier -= .11;
        } else if (player.prestigePoints >= 700 && player.prestigePoints < 750) {
            modifier -= .1175;
        } else if (player.prestigePoints >= 750 && player.prestigePoints < 800) {
            modifier -= .125;
        } else if (player.prestigePoints >= 800 && player.prestigePoints < 850) {
            modifier -= .1325;
        } else if (player.prestigePoints >= 850 && player.prestigePoints < 900) {
            modifier -= .14;
        } else if (player.prestigePoints >= 900 && player.prestigePoints < 1500) {
            modifier -= .1475;
        }
        if (player.getItems().isWearingItem(2572)) {
            modifier -= .05;
        } else if (player.getItems().isWearingItem(12785)) {
            modifier -= .15;
        } else if (player.getItems().isWearingItem(773)) {
            modifier -= .32;
        }
        if (player.summonId == 9959) {
            modifier -= .5;
        } else if (player.summonId == 13326) {
            modifier -= .15;
        }
        if (player.getRightGroup().contains(Right.MAX_DONATOR)) {
            modifier -= .15;
        } else if (player.getRightGroup().contains(Right.UBER_DONATOR)) {
            modifier -= .12;
        } else if (player.getRightGroup().contains(Right.LEGENDARY)) {
            modifier -= .10;
        } else if (player.getRightGroup().contains(Right.EXTREME_DONATOR)) {
            modifier -= .08;
        } else if (player.getRightGroup().contains(Right.SUPER_DONATOR)) {
            modifier -= .07;
        } else if (player.getRightGroup().contains(Right.DONATOR)) {
            modifier -= .06;
        } else if (player.getRightGroup().contains(Right.SUPPORTER)) {
            modifier -= .05;
        } else if (player.getRightGroup().contains(Right.SPONSOR)) {
            modifier -= .04;
        } else if (player.getRightGroup().contains(Right.CONTRIBUTOR)) {
            modifier -= .03;
        }
        return modifier;
    }

    public static double getModifier1(Player player) {
        int modifier = 0;
        if (player.prestigePoints >= 1 && player.prestigePoints < 10) {
            modifier += 0.75;
        } else if (player.prestigePoints >= 10 && player.prestigePoints < 25) {
            modifier += 1;
        } else if (player.prestigePoints >= 25 && player.prestigePoints < 50) {
            modifier += 1.5;
        } else if (player.prestigePoints >= 50 && player.prestigePoints < 100) {
            modifier += 2;
        } else if (player.prestigePoints >= 100 && player.prestigePoints < 150) {
            modifier += 2.75;
        } else if (player.prestigePoints >= 150 && player.prestigePoints < 200) {
            modifier += 3.5;
        } else if (player.prestigePoints >= 200 && player.prestigePoints < 250) {
            modifier += 4.25;
        } else if (player.prestigePoints >= 250 && player.prestigePoints < 300) {
            modifier += 5;
        } else if (player.prestigePoints >= 300 && player.prestigePoints < 350) {
            modifier += 5.75;
        } else if (player.prestigePoints >= 350 && player.prestigePoints < 400) {
            modifier += 6.5;
        } else if (player.prestigePoints >= 400 && player.prestigePoints < 450) {
            modifier += 7.25;
        } else if (player.prestigePoints >= 450 && player.prestigePoints < 500) {
            modifier += 8;
        } else if (player.prestigePoints >= 500 && player.prestigePoints < 550) {
            modifier += 8.75;
        } else if (player.prestigePoints >= 550 && player.prestigePoints < 600) {
            modifier += 9.5;
        } else if (player.prestigePoints >= 600 && player.prestigePoints < 650) {
            modifier += 10.25;
        } else if (player.prestigePoints >= 650 && player.prestigePoints < 700) {
            modifier += 11;
        } else if (player.prestigePoints >= 700 && player.prestigePoints < 750) {
            modifier += 11.75;
        } else if (player.prestigePoints >= 750 && player.prestigePoints < 800) {
            modifier += 12.5;
        } else if (player.prestigePoints >= 800 && player.prestigePoints < 850) {
            modifier += 13.25;
        } else if (player.prestigePoints >= 850 && player.prestigePoints < 900) {
            modifier += 14;
        } else if (player.prestigePoints >= 900 && player.prestigePoints < 1500) {
            modifier += 14.75;
        }
        if (player.getItems().isWearingItem(2572)) {
            modifier += 5;
        } else if (player.getItems().isWearingItem(12785)) {
            modifier += 15;
        } else if (player.getItems().isWearingItem(773)) {
            modifier += 32;
        }
        if (player.getMode().isOsrs()) {
            modifier += 15;
        }
        if (player.summonId == 9959) {
            modifier += 5;
        } else if (player.summonId == 13326) {
            modifier += 15;
        }
        if (player.getRightGroup().contains(Right.MAX_DONATOR)) {
            modifier += 15;
        } else if (player.getRightGroup().contains(Right.UBER_DONATOR)) {
            modifier += 12;
        } else if (player.getRightGroup().contains(Right.LEGENDARY)) {
            modifier += 10;
        } else if (player.getRightGroup().contains(Right.EXTREME_DONATOR)) {
            modifier += 8;
        } else if (player.getRightGroup().contains(Right.SUPER_DONATOR)) {
            modifier += 7;
        } else if (player.getRightGroup().contains(Right.DONATOR)) {
            modifier += 6;
        } else if (player.getRightGroup().contains(Right.SUPPORTER)) {
            modifier += 5;
        } else if (player.getRightGroup().contains(Right.SPONSOR)) {
            modifier += 4;
        } else if (player.getRightGroup().contains(Right.CONTRIBUTOR)) {
            modifier += 3;
        }
        return modifier;
    }

    /**
     * Clears the interface of all parts.
     * <p>
     * Used on searching and initial load.
     *
     * @param player
     */
    public void clear(Player player) {
        for (int i = 0; i < 150; i++) {
            player.getPA().sendFrame126("", 33008 + i);
        }

        player.getPA().sendFrame126("", 43110);
        player.getPA().sendFrame126("", 43111);
        player.getPA().sendFrame126("", 43112);
        player.getPA().sendFrame126("", 43113);

        for (int i = 0; i < 80; i++) {
            player.getPA().itemOnInterface(-1, 0, 34010 + i, 0);
            player.getPA().sendString("", 34200 + i);
            player.getPA().sendString("", 34300 + i);
            player.getPA().sendString("", 34100 + i);
            player.getPA().sendString("", 34400 + i);
        }
        player.searchList.clear();
    }

    public void open2(Player player) {
        clear(player);

        for (int index = 0; index < ordered.size(); index++) {
            player.getPA().sendFrame126(StringUtils.capitalize(NPCDefinitions.get(ordered.get(index)).getNpcName().toLowerCase().replaceAll("_", " ")), 33008 + index);
        }

        player.getPA().showInterface(33000);
    }

    /**
     * Searchers after the player inputs a npc name
     *
     * @param player
     * @param name
     */
    public void search(Player player, String name) {
        if (name.matches("^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]+$")) {
            player.sendMessage("You may not search for alphabetical and numerical combinations.");
            return;
        }
        if (System.currentTimeMillis() - player.lastDropTableSearch < TimeUnit.SECONDS.toMillis(5)) {
            player.sendMessage("You can only do this once every 5 seconds.");
            return;
        }
        player.lastDropTableSearch = System.currentTimeMillis();

        clear(player);

        List<Integer> definitions = ordered.stream().filter(Objects::nonNull).filter(def -> NPCDefinitions.get(def).getNpcName() != null).filter(def -> NPCDefinitions.get(def).getNpcName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
//
//		if(definitions.isEmpty()) {
//			definitions = ordered.stream().filter(Objects::nonNull).collect(Collectors.toList());
//			List<Integer> npcs = new ArrayList<>();
//			int count = 0;
//			for(Integer index : definitions) {
//				Optional<TableGroup> group = groups.values().stream().filter(g -> g.getNpcIds().contains(NPCDefinitions.get(index).getNpcId())).findFirst();
//				if(group.isPresent()) {
//					TableGroup g = group.get();
//
//					for(TablePolicy policy : TablePolicy.values()) {
//						Optional<Table> table = g.stream().filter(t -> t.getPolicy() == policy).findFirst();
//						if(table.isPresent()) {
//							for(Drop drop : table.get()) {
//								if(drop == null) {
//									continue;
//								}
//
//								if(ItemAssistant.getItemName(drop.getItemId()).toLowerCase().contains(name.toLowerCase())) {
//									npcs.add(index);
//									player.getPA().sendFrame126(StringUtils.capitalize(NPCDefinitions.get(NPCDefinitions.get(index).getNpcId()).getNpcName().toLowerCase().replaceAll("_", " ")), 33008 + count);
//									count++;
//								}
//							}
//						}
//					}
//				}
//
//			}

//			player.searchList = npcs;
//			return;

//		}

        for (int index = 0; index < definitions.size(); index++) {
            if (index >= 150) {
                break;
            }
            player.getPA().sendFrame126(StringUtils.capitalize(NPCDefinitions.get(definitions.get(index)).getNpcName().toLowerCase().replaceAll("_", " ")), 33008 + index);
        }

        player.searchList = definitions;
    }

    /**
     * Loads the selected npc choosen by the player to view their drops
     *
     * @param player
     * @param button
     */
    public void select(Player player, int button) {
        int listIndex;
        if (button <= 128255) {
            listIndex = button - 128240;
        } else {
            listIndex = (128255 - 128240) + 1 + button - 129000;
        }

        if (listIndex < 0 || listIndex > ordered.size() - 1) {
            return;
        }

        //Finding NPC ID
        int npcId = player.searchList.isEmpty() ? ordered.get(listIndex) : player.searchList.get(listIndex);

//		Optional<TableGroup> group = groups.values().stream().filter(g -> g.getNpcIds().contains(npcId)).findFirst();

        //If the group in the search area contains this NPC
//		group.ifPresent(g -> {
//			if (System.currentTimeMillis() - player.lastDropTableSelected < TimeUnit.SECONDS.toMillis(5)) {
//				player.sendMessage("You can only do this once every 5 seconds.");
//				return;
//			}

        //Loads the definition and maxhit/aggressiveness to display
        NPCDefinitions npcDef = NPCDefinitions.get(npcId);

        player.getPA().sendFrame126("Health: @whi@" + npcDef.getNpcHealth(), 43110);
        player.getPA().sendFrame126("Combat Level: @whi@" + npcDef.getNpcCombat(), 43111);
        if (NPCHandler.getNpc(npcId) != null) {
            player.getPA().sendFrame126("Max Hit: @whi@" + NPCHandler.getNpc(npcId).maxHit, 43112);
        } else {
            player.getPA().sendFrame126("Max Hit: @whi@?", 43112);
        }
        player.getPA().sendFrame126("Aggressive: @whi@" + (Server.npcHandler.isAggressive(npcId, true) ? "true" : "false"), 43113);

        player.lastDropTableSelected = System.currentTimeMillis();

        double modifier = getModifier(player);
//
//        LootTableContainer container = LootContainerLoader.getInstance().getLootContainer(npcId, LootContainerType.NPC);
//        List<PotentialItem> potentialItems = new ArrayList<>();
//        container.getLootTables().getMap().values().forEach(lootTable -> potentialItems.addAll(lootTable.getPotentialItems()));
//        this.updateAmounts(player, potentialItems);
        //Iterates through all 5 drop table's (Found in TablePolicy -> Enum)
//			for (TablePolicy policy : TablePolicy.POLICIES) {
//				Optional<Table> table = g.stream().filter(t -> t.getPolicy() == policy).findFirst();
//				if (table.isPresent()) {
//					double chance = (1.0 /(table.get().getAccessibility() * modifier)) * 100D;
//					int in_kills = (int) (100 / chance);
//					if (chance > 100.0) {
//						chance = 100.0;
//					}
//					if (in_kills == 0) {
//						in_kills = 1;
//					}
//
//					//Updates the interface with all new information
//					updateAmounts(player, policy, table.get(), in_kills);
//				} else {
//					updateAmounts(player, policy, new ArrayList<>(), -10);
//				}
//			}

        //If the game has displayed all drops and there are empty slots that haven't been filled, clear them
        if (player.dropSize < 80) {
            for (int i = player.dropSize; i < 80; i++) {
                player.getPA().sendString("", 34200 + i);
                player.getPA().itemOnInterface(-1, 0, 34010 + i, 0);
                player.getPA().sendString("", 34300 + i);
                player.getPA().sendString("", 34100 + i);
                player.getPA().sendString("", 34400 + i);
            }
        }
        player.dropSize = 0;
//    });
    }


//    private void updateAmounts(Player player, List<PotentialItem> drops) {
//        DecimalFormat df = new DecimalFormat("#.#####");
//        df.setRoundingMode(RoundingMode.CEILING);
//        //Iterates through all drops in that catagory
//        for (int index = 0; index < drops.size(); index++) {
//            PotentialItem potentialItem = drops.get(index);
//            try {
//                int minimum = (int) potentialItem.getPotentialAmount().getMin();
//                int maximum = (int) potentialItem.getPotentialAmount().getMax();
//                String tierName = Tier.getRarityForValue(potentialItem.getRoll()).toString();
//                int frame = (34200 + player.dropSize + index);//collumnOffset + (index * 2);
//
//                //if max = min, just send the max
//                if (minimum == maximum) {
//                    player.getPA().sendString(Misc.getValueWithoutRepresentation(maximum), frame);
//                } else {
//                    player.getPA().sendString(Misc.getValueWithoutRepresentation(minimum) + " - " + Misc.getValueWithoutRepresentation(maximum), frame);
//                }
//                player.getPA().itemOnInterface(potentialItem.getItemId(), 1, 34010 + player.dropSize + index, 0);
//                player.getPA().sendString(Misc.optimizeText(tierName.toLowerCase()), 34300 + player.dropSize + index);
//                player.getPA().sendString(Server.itemHandler.getItemList(potentialItem.getItemId()).itemName, 34100 + player.dropSize + index);
//                player.getPA().sendString(df.format(potentialItem.getRoll()) + "%", 34400 + player.dropSize + index);
//            } catch (NullPointerException e) {
//                System.out.println("Missing Drop Data for: " + potentialItem.getItemId());
//            }
//        }
//
//        player.dropSize += drops.size();
//    }

    /**
     * Updates the interface for the selected NPC
     *
     * @param player
     * @param policy
     * @param drops
     * @param kills
     */
    private void updateAmounts(Player player, TablePolicy policy, List<Drop> drops, int kills) {

        //Iterates through all drops in that catagory
        for (int index = 0; index < drops.size(); index++) {
            Drop drop = drops.get(index);
            int minimum = drop.getMinimumAmount();
            int maximum = drop.getMaximumAmount();
            int frame = (34200 + player.dropSize + index);//collumnOffset + (index * 2);

            //if max = min, just send the max
            if (minimum == maximum) {
                player.getPA().sendString(Misc.getValueWithoutRepresentation(drop.getMaximumAmount()), frame);
            } else {
                player.getPA().sendString(Misc.getValueWithoutRepresentation(drop.getMinimumAmount()) + " - " + Misc.getValueWithoutRepresentation(drop.getMaximumAmount()), frame);
            }
            player.getPA().itemOnInterface(drop.getItemId(), 1, 34010 + player.dropSize + index, 0);
            player.getPA().sendString(Misc.optimizeText(policy.name().toLowerCase()), 34300 + player.dropSize + index);
            player.getPA().sendString(Server.itemHandler.getItemList(drop.getItemId()).itemName, 34100 + player.dropSize + index);
            if (kills == -10) {
                player.getPA().sendString(1 + "/?", 34400 + player.dropSize + index);
            } else {
                player.getPA().sendString(1 + "/" + kills, 34400 + player.dropSize + index);
            }
        }

        player.dropSize += drops.size();
    }

    static int amountt = 0;

    private FileReader fileReader;

    /**
     * Testing droptables of chosen npcId
     *
     * @param player The player who is testing the droptable
     * @param npcId  The npc who of which the player is testing the droptable from
     * @param amount The amount of times the player want to grab a drop from the npc droptable
     */
    public void test(Player player, int npcId, int amount) {
        Optional<TableGroup> group = groups.values().stream().filter(g -> g.getNpcIds().contains(npcId)).findFirst();

        amountt = amount;

        while (amount-- > 0) {
            group.ifPresent(g -> {
                List<GameItem> drops = g.access(player, 1.0, 1);

                for (GameItem item : drops) {
                    player.getItems().addItemToBank(item.getId(), item.getAmount());
                }
            });
        }
        player.sendMessage("Completed " + amountt + " drops from " + Server.npcHandler.getNpcName(npcId) + ".");
    }


}
