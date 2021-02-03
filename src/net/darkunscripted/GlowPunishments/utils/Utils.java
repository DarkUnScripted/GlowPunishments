package net.darkunscripted.GlowPunishments.utils;

import org.bukkit.ChatColor;

public class Utils {

    public static String chat(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String strip(String message){
        return ChatColor.stripColor(message);
    }
}
