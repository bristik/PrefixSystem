package ru.bristol.prefixsystem.files.config;

import org.bukkit.plugin.Plugin;
import ru.bristol.prefixsystem.files.FileConfigImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfigRepositoryImpl extends FileConfigImpl implements ConfigRepository {

    private List<String> blocked_prefixes = new ArrayList<>();

    public ConfigRepositoryImpl(Plugin plugin) {
        super(plugin, "config", true, false);
        init();
    }

    @Override
    public void init() {
        blocked_prefixes = getFileConfiguration().getStringList("blocked-prefixes");
    }

    @Override
    public boolean isBlockedPrefix(String path) {
        return blocked_prefixes.contains(path);
    }

}
