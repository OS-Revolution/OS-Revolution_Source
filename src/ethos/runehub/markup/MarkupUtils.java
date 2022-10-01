package ethos.runehub.markup;

public class MarkupUtils {

    public static final String GREEN = "@gre@";
    public static final String RED = "@red@";
    public static final String YELLOW = "@yel@";

    public static String highlightText(String highlight, String text) {
        if (text.startsWith("@"))
            removeHighlightFromText(text);
        return highlight + text;
    }

    public static String removeHighlightFromText(String text) {
        if (!text.isEmpty()) {
            final int endIndex = text.substring(1).lastIndexOf('@') + 2;
            if (text.startsWith("@"))
                return text.substring(endIndex);
        }
        return text;
    }
}
