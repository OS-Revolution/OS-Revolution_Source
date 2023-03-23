package ethos.runehub.entity.mob;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ethos.runehub.entity.mob.hostile.HostileMobContext;
import org.runehub.api.io.load.JsonSerializer;
import org.runehub.api.model.entity.item.Drop;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MobContextJsonSerializer extends JsonSerializer<MobContextJsonSerializer.NpcContextList> {

    public static class NpcContextList {

        private final List<HostileMobContext> npcContexts;

        public NpcContextList(List<HostileMobContext> npcContexts) {
            this.npcContexts = npcContexts;
        }

        public List<HostileMobContext> getContexts() {
            return npcContexts;
        }
    }

    public MobContextJsonSerializer() {
        super(NpcContextList.class);
    }

    @Override
    public NpcContextList deserialize(String json) {
////        Logger.getGlobal().info("Deserializing Npc: " + json);
//        final JsonElement element = new JsonParser().parse(json);
//        final JsonObject jsonObject = element.getAsJsonObject();
//        final List<HostileMobContext> npcContexts = new ArrayList<>();
//        if (jsonObject.entrySet().stream().anyMatch(stringJsonElementEntry -> stringJsonElementEntry.getKey().contains("_items"))) {
//            element.getAsJsonObject().get("_items").getAsJsonArray().forEach(jsonElement -> {
//                Logger.getGlobal().info("Parsed NPC from Array: " + this.parse(jsonElement));
//                npcContexts.add(this.parse(jsonElement));
//            });
//        } else {
//            Logger.getGlobal().info("Parsed NPC: " + this.parse(element));
//            npcContexts.add(this.parse(element));
//        }
//
//        return new NpcContextList(npcContexts);
        return null;
    }

    private List<Drop> parseDrops(JsonArray jsonArray) {
        final List<Drop> drops = new ArrayList<>();
        jsonArray.forEach(element -> {
            final JsonObject jsonObject = element.getAsJsonObject();
            final Parser parser = new Parser(jsonObject);
            final int id = parser.getInt("id");
            final String name = parser.getString("name");
            final boolean members = parser.getBoolean("members");
            final boolean noted = parser.getBoolean("noted");
            final String quantity = parser.getString("quantity");
            final double rarity = jsonObject.get("rarity").getAsDouble();
            final int rolls = parser.getInt("rolls");
            if (drops.stream().noneMatch(item -> (item.getItemId() == id && item.getRarity() == rarity))) {
//                            if (!noted) {
                drops.add(new Drop(id, quantity, rolls, rarity, members, noted, name));
//                                drops.add(new Drop(id, name, new RangeStringConverter().fromString(quantity), rarity, members, noted,name));
//                            } else {
//                                drops.add(new Drop(id + 1, name, new RangeStringConverter().fromString(quantity), rarity, members, noted,name));
//                            }
            }
        });
        return drops;
    }

//    private HostileMobContext parse(JsonElement element) {
//        final JsonObject jsonObject = element.getAsJsonObject();
//        final Parser parser = new Parser(jsonObject);
//        return new HostileMobContext.NpcContextBuilder(parser.getInt("id"), parser.getString("name"))
//                .setAggressive(parser.getBoolean("aggressive"))
//                .setPoisonous(parser.getBoolean("poisonous"))
//                .setVenomous(parser.getBoolean("venomous"))
//                .setPoisonImmune(parser.getBoolean("immune_poison"))
//                .setVenomImmune(parser.getBoolean("immune_venom"))
//                .setMembers(parser.getBoolean("members"))
//                .setDuplicate(parser.getBoolean("duplicate"))
//                .setSlayerMonster(parser.getBoolean("slayer_monster"))
//                .setCombatLevel(parser.getInt("combat_level"))
//                .setExamine(parser.getString("examine"))
//                .setMaxHit(parser.getInt("max_hit"))
//                .setHitpoints(parser.getInt("hitpoints"))
//                .setSize(parser.getInt("size"))
//                .setReleaseDate(parser.getString("release_date"))
//                .setSlayerLevel(parser.getInt("slayer_level"))
//                .setAttackTypes(parser.getListOfStrings("attack_type"))
//                .setAttributes(parser.getListOfStrings("attributes"))
//                .setCategories(parser.getListOfStrings("category"))
//                .setWiki(parser.getString("wiki_url"))
//                .setDefenceLevel(parser.getInt("defence_level"))
//                .setDefenceCrush(parser.getInt("defence_crush"))
//                .setDefenceMage(parser.getInt("defence_magic"))
//                .setDefenceRanged(parser.getInt("defence_ranged"))
//                .setDefenceSlash(parser.getInt("defence_slash"))
//                .setDefenceStab(parser.getInt("defence_stab"))
//                .setDrops(this.parseDrops(jsonObject.get("drops").getAsJsonArray()))
//                .build();
//    }

    private static class Parser {
        private final JsonObject jsonObject;

        public Parser(JsonObject jsonObject) {
            this.jsonObject = jsonObject;
        }

        private boolean getBoolean(String key) {
            return jsonObject.get(key).getAsBoolean();
        }

        private int getInt(String key) {
            return jsonObject.get(key).getAsInt();
        }

        private String getString(String key) {
            return jsonObject.get(key).getAsString();
        }

        private List<String> getListOfStrings(String key) {
            final List<String> strings = new ArrayList<>();
            jsonObject.get(key).getAsJsonArray().forEach(element -> strings.add(element.toString()));
            return strings;
        }
    }
}
