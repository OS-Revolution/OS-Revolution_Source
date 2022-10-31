package ethos.runehub.markup;

import ethos.runehub.RunehubUtils;

import java.util.Arrays;

public class MarkupParser {

    public static RSString parseMarkup(String text) {
        final String[] words = text.split(" ");
        final RSString.RSStringBuilder builder = new RSString.RSStringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i].replaceAll("_", " ");
            try {
                if (word.startsWith("@")) {
                    builder
                            .insertItem(Integer.parseInt(word.substring(1)))
                            .addText(" ");
                } else if (word.startsWith("#")) {
                    builder
                            .insertFormattedQuantity(Integer.parseInt(word.substring(1)))
                            .addText(" ");
                } else if (word.startsWith("$")) {
                    builder
                            .highlight(word.substring(1))
                            .addText(" ");
                } else if(word.startsWith("^")) {
                    builder
                            .insertHeader(word.substring(1))
                            .addText(" ");
                } else if(word.equalsIgnoreCase("&&")) {
                    builder
                            .insertIndefiniteArticle(RunehubUtils.beginsWithVowel(words[i + 1]) ? words[i + 1] : words[i + 1].substring(1));
                } else {
                    builder.addText(word)
                            .addText(" ");
                }
            } catch (NumberFormatException e) {
                builder.addText(word)
                        .addText(" ");
            }
        }
        return builder.build();
    }
}
