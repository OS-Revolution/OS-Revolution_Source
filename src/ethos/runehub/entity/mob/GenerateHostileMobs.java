package ethos.runehub.entity.mob;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.mob.hostile.HostileMobContext;
import ethos.runehub.entity.mob.hostile.HostileMobContextDAO;

import java.util.*;

public class GenerateHostileMobs {

    public static void main(String[] args) {
        Set<Map.Entry<String,JsonElement>> elements = RunehubUtils.getMonsters().entrySet();
        elements.forEach(entry -> {
            String mobIdString = entry.getKey();
            JsonObject mobJson = entry.getValue().getAsJsonObject();
            System.out.println("Generating: " + mobIdString);
            int mobId = mobJson.get("id").getAsInt();
            String name = getString(Objects.requireNonNull(getElement("name", mobJson)));
            boolean members = getBoolean(Objects.requireNonNull(getElement("members", mobJson)));
            int combatLevel = getInt(Objects.requireNonNull(getElement("combat_level", mobJson)));
            int size = getInt(Objects.requireNonNull(getElement("size", mobJson)));
            int hp = getInt(Objects.requireNonNull(getElement("hitpoints", mobJson)));
            if (hp != 0 ) {
                int mh =  getInt(Objects.requireNonNull(getElement("max_hit", mobJson)));
                List<String> attackTypes = getListOfStrings("attack_type", mobJson);
                String examine = getString(Objects.requireNonNull(getElement("examine", mobJson)));
                int attackSpeed = getInt(Objects.requireNonNull(getElement("attack_speed", mobJson)));
                boolean aggressive = getBoolean(Objects.requireNonNull(getElement("aggressive", mobJson)));
                boolean poisonous = getBoolean(Objects.requireNonNull(getElement("poisonous", mobJson)));
                boolean venomous = getBoolean(Objects.requireNonNull(getElement("venomous", mobJson)));
                boolean poisonImmune = getBoolean(Objects.requireNonNull(getElement("immune_poison", mobJson)));
                boolean venomImmune = getBoolean(Objects.requireNonNull(getElement("immune_venom", mobJson)));
                List<String> attributes = getListOfStrings("attributes", mobJson);
                List<String> category = getListOfStrings("category", mobJson);
                String wikiUrl = mobJson.get("wiki_url").getAsString();
                int attackLevel = getInt(Objects.requireNonNull(getElement("attack_level", mobJson)));
                int strengthLevel = getInt(Objects.requireNonNull(getElement("strength_level", mobJson)));
                int defenceLevel = getInt(Objects.requireNonNull(getElement("defence_level", mobJson)));
                int magicLevel = getInt(Objects.requireNonNull(getElement("magic_level", mobJson)));
                int rangedLevel = getInt(Objects.requireNonNull(getElement("ranged_level", mobJson)));
                int attackBonus = getInt(Objects.requireNonNull(getElement("attack_bonus", mobJson)));
                int strengthBonus = getInt(Objects.requireNonNull(getElement("strength_bonus", mobJson)));
                int attackMagic = getInt(Objects.requireNonNull(getElement("attack_magic", mobJson)));
                int magicBonus = getInt(Objects.requireNonNull(getElement("magic_bonus", mobJson)));
                int attackRanged = getInt(Objects.requireNonNull(getElement("attack_ranged", mobJson)));
                int rangedBonus = getInt(Objects.requireNonNull(getElement("ranged_bonus", mobJson)));
                int defenceStab = getInt(Objects.requireNonNull(getElement("defence_stab", mobJson)));
                int defenceSlash = getInt(Objects.requireNonNull(getElement("defence_slash", mobJson)));
                int defenceCrush = getInt(Objects.requireNonNull(getElement("defence_crush", mobJson)));
                int defenceMagic = getInt(Objects.requireNonNull(getElement("defence_magic", mobJson)));
                int defenceRanged = getInt(Objects.requireNonNull(getElement("defence_ranged", mobJson)));
                int slayerLevel = getInt(Objects.requireNonNull(getElement("slayer_level", mobJson)));
                String release = getString(Objects.requireNonNull(getElement("release_date", mobJson)));
                boolean slayerMonster = getBoolean(Objects.requireNonNull(getElement("slayer_monster", mobJson)));
                int respawn = RunehubUtils.getMobRespawn(mobJson);
                HostileMobContext context = new HostileMobContext(
                        mobId,
                        combatLevel,
                        size,
                        hp,
                        mh,
                        slayerLevel,
                        name,
                        release,
                        wikiUrl,
                        examine,
                        members,
                        aggressive,
                        poisonous,
                        venomous,
                        poisonImmune,
                        venomImmune,
                        slayerMonster,
                        false,
                        List.of(),
                        attackTypes,
                        attributes,
                        category,
                        defenceLevel,
                        defenceSlash,
                        defenceCrush,
                        defenceStab,
                        defenceRanged,
                        defenceMagic,
                        attackLevel,
                        strengthLevel,
                        magicLevel,
                        rangedLevel,
                        attackBonus,
                        strengthBonus,
                        attackMagic,
                        magicBonus,
                        attackRanged,
                        rangedBonus,
                        respawn,
                        attackSpeed
                );
                HostileMobContextDAO.getInstance().create(context);
            }
        });


    }

    private static String getString(JsonElement element) {
        return element.isJsonNull() ? "N/A" : element.getAsString();
    }
    private static boolean getBoolean(JsonElement element) {
        return !element.isJsonNull() && element.getAsBoolean();
    }

    private static int getInt(JsonElement element) {
        return element.isJsonNull() ? 0 : element.getAsInt();
    }

    private static JsonElement getElement(String name, JsonObject jsonObject) {
        return jsonObject.has(name) ? jsonObject.get(name) : null;
    }

    private static List<String> getListOfStrings(String key, JsonObject jsonObject) {
        final List<String> strings = new ArrayList<>();
        jsonObject.get(key).getAsJsonArray().forEach(element -> strings.add(element.toString()));
        return strings;
    }

    private static boolean hasKey(JsonObject jsonObject,String key) {
        return jsonObject.has(key);
    }
}
