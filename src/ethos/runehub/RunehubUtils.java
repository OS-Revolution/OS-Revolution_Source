package ethos.runehub;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ethos.runehub.db.PlayerCharacterContextDataAccessObject;
import ethos.util.Misc;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.runehub.api.io.file.Extension;
import org.runehub.api.io.file.FileRequest;
import org.runehub.api.io.file.impl.APIFileSystem;
import org.runehub.api.util.SkillDictionary;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class RunehubUtils {

    public static String getBooleanAsYesOrNo(boolean value) {
        return value ? "Yes" : "No";
    }

    public static String getBooleanAsOnOrOff(boolean value) {
        return value ? "On" : "Off";
    }

    public static int getMobRespawn(JsonObject mob) {

        try {
            Document document = Jsoup.connect(mob.get("wiki_url").getAsString()).get();
            Element tr = document.select("td[data-attr-param=respawn]").get(0);
            return Integer.parseInt(tr.text().split(" ")[0]);
        } catch (IOException | IndexOutOfBoundsException e) {
            Logger.getGlobal().severe("Failed to load respawn time");
        }
        return 5;
    }

    public static int getMobRespawn(int id) {
        final JsonObject mob = getMob(id);

        try {
            Document document = Jsoup.connect(mob.get("wiki_url").getAsString()).get();
            Element tr = document.select("td[data-attr-param=respawn]").get(0);
           return Integer.parseInt(tr.text().split(" ")[0]);
        } catch (IOException | IndexOutOfBoundsException e) {
            Logger.getGlobal().severe("Failed to load respawn time for: " + id);
        }
        return 5;
    }

    public static JsonObject getMob(int id) {
        final JsonObject allMobs = getMonsters();
        final String idAsString = String.valueOf(id);
        if (allMobs.entrySet().stream().anyMatch(stringJsonElementEntry -> stringJsonElementEntry.getKey().equalsIgnoreCase(idAsString))) {
            return allMobs.get(idAsString).getAsJsonObject();
        }
        return null;
    }

    public static JsonObject getMonsters() {
        final FileRequest allMonstersFileRequest =  APIFileSystem.getInstance()
                .buildFileRequest()
                .inDirectory(APIFileSystem.APP_DIRECTORY)
                .inDirectory(APIFileSystem.COMMON_RESOURCE_DIRECTORY)
                .inDirectory(APIFileSystem.COMMON_DB_RESOURCE)
                .withFileName("monsters-complete")
                .withExtension(Extension.JSON)
                .build();
        try (BufferedReader reader = new BufferedReader(new FileReader(allMonstersFileRequest.getFile()))) {
            final JsonParser parser = new JsonParser();
            String json = readAll(reader);
            return parser.parse(json).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static boolean beginsWithVowel(String text) {
        try {
            return text.charAt(0) == 'a' || text.charAt(0) == 'e' || text.charAt(0) == 'i' || text.charAt(0) == 'o' || text.charAt(0) == 'u';
        } catch (IndexOutOfBoundsException e) {

        }
        return false;
    }

    public static int getRegionId(int x, int y) {
        int regionX = x >> 3;
        int regionY = y >> 3;
        int regionId = ((regionX / 8) << 8) + (regionY / 8);
        return regionId;
    }

    public static String getSkillName(int id) {
        if (id >= 0 && id <= 22) {
            return Misc.capitalize(SkillDictionary.getSkillNameFromId(id).toLowerCase());
        }
        return "Sailing";
    }

    public static String getIndefiniteArticle(String noun) {
        return beginsWithVowel(noun) ? "an" : "a";
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    public static Set<Long> getPlayPassHiscores() {
        final Map<Long,Integer> scoreMap = new HashMap<>();

        PlayerCharacterContextDataAccessObject.getInstance().getAllEntries().forEach(ctx -> {
            scoreMap.put(ctx.getId(),ctx.getPlayerSaveData().getPlayPassXp());
        });

        return sortByValue(scoreMap).keySet();
    }

}
