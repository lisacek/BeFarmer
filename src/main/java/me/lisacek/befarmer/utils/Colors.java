package me.lisacek.befarmer.utils;

import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Colors {

    // Patterms
    public static final Pattern HEX_PATTERN = Pattern.compile("&#" + "([A-Fa-f0-9]{6})" + "");
    public static final char COLOR_CHAR = ChatColor.COLOR_CHAR;

    // Translator
    private String translateHexColorCodes(String message) {
        Matcher matcher = HEX_PATTERN.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find()) {
            String group = matcher.group(1);

            matcher.appendReplacement(buffer, COLOR_CHAR + "x"
                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );

        }
        return matcher.appendTail(buffer).toString();
    }

    public String translateColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', translateHexColorCodes(message));
    }

    public List<String> translateColors(List<String> list) {
        List<String> newColorizedList = new ArrayList<>();
        for (String line : list) {
            newColorizedList.add(translateColors(line));
        }
        return newColorizedList;
    }

    public List<String> translateColors(List<String> list, int price) {
        List<String> newColorizedList = new ArrayList<>();
        for (String line : list) {
            newColorizedList.add(translateColors(line
                    .replace("{Price}", String.valueOf(price))
            ));
        }
        return newColorizedList;
    }
}