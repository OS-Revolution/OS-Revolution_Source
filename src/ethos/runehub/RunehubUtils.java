package ethos.runehub;

import ethos.runehub.db.PlayerCharacterContextDataAccessObject;
import ethos.util.Misc;
import org.runehub.api.util.SkillDictionary;

import java.util.*;

public class RunehubUtils {

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
