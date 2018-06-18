package ru.bristol.prefixsystem.utils;

import org.bukkit.command.CommandSender;

public class MessageUtil {

    public static void sendMessage(CommandSender sender, String message) {
        if(message != null && !message.equals("")) sender.sendMessage(message);
    }

}
