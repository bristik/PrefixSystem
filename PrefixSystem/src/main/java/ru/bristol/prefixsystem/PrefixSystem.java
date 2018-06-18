package ru.bristol.prefixsystem;

import org.bukkit.plugin.java.JavaPlugin;
import ru.bristol.prefixsystem.commands.AdminPrefixCommand;
import ru.bristol.prefixsystem.commands.PrefixCommand;
import ru.bristol.prefixsystem.files.config.ConfigRepository;
import ru.bristol.prefixsystem.files.config.ConfigRepositoryImpl;
import ru.bristol.prefixsystem.files.lang.LangRepository;
import ru.bristol.prefixsystem.files.lang.LangRepositoryImpl;
import ru.bristol.prefixsystem.managers.VaultManager;
import ru.bristol.prefixsystem.managers.VaultManagerImpl;

public class PrefixSystem extends JavaPlugin {

    private LangRepository langRepository;
    private ConfigRepository configRepository;
    private VaultManager vaultManager;

    @Override
    public void onEnable() {
        configRepository = new ConfigRepositoryImpl(this);
        langRepository = new LangRepositoryImpl(this);
        vaultManager = new VaultManagerImpl();

        getCommand("prefix").setExecutor(new PrefixCommand(langRepository, configRepository, vaultManager));
        getCommand("aprefix").setExecutor(new AdminPrefixCommand(langRepository, configRepository, vaultManager));
    }


}
