package ethos.runehub;

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

    public static String getIndefiniteArticle(String noun) {
        return beginsWithVowel(noun) ? "an" : "a";
    }

}
