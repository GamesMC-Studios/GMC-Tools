package pl.refertv.tools.utils;

import net.md_5.bungee.api.ChatColor;
import pl.refertv.tools.Tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.md_5.bungee.api.ChatColor;

public class Colors {
    public static final Pattern HEX = Pattern.compile("&#(\\w{5}[0-9a-f])");
    Tools main;

    public static String colorHex(String text) {
        if (text == null) {
            return text;
        } else {
            Matcher matcher = HEX.matcher(text);
            StringBuffer buffer = new StringBuffer();

            while(matcher.find()) {
                matcher.appendReplacement(buffer, ChatColor.of("#" + matcher.group(1)).toString());
            }

            return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(buffer).toString());
        }
    }

    public static String color(String text) {
        return text == null ? text : org.bukkit.ChatColor.translateAlternateColorCodes('&', text);
    }
}
