package ru.bristol.prefixsystem.utils;

import org.bukkit.ChatColor;

public class ColorUtil {

    public static String color(String message) {
        return message != null ? ChatColor.translateAlternateColorCodes('&', message) : "";
    }

}
