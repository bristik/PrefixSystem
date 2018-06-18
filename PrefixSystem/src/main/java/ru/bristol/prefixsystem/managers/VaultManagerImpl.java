package ru.bristol.prefixsystem.managers;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;

public class VaultManagerImpl implements VaultManager {

    private Chat chat;

    public VaultManagerImpl() {
        this.chat = Bukkit.getServicesManager().getRegistration(Chat.class).getProvider();
    }

    @Override
    public void setPrefix(String name, String prefix) {
        chat.setPlayerPrefix((String) null, name, prefix);
    }

}
