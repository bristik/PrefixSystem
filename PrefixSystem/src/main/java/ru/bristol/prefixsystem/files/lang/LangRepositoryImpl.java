package ru.bristol.prefixsystem.files.lang;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;
import ru.bristol.prefixsystem.files.FileConfigImpl;
import ru.bristol.prefixsystem.utils.ColorUtil;
import ru.bristol.prefixsystem.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

public class LangRepositoryImpl extends FileConfigImpl implements LangRepository {

    private Map<String, String> messages = new HashMap<>();

    public LangRepositoryImpl(Plugin plugin) {
        super(plugin, "lang", true, false);
        init();
    }

    @Override
    public void init() {
        Configuration lang = getFileConfiguration();
        lang.getConfigurationSection("messages").getKeys(false).stream().forEach(section -> lang.getConfigurationSection("messages." + section).getKeys(false).stream().forEach(path -> messages.put(section + "." + path, ColorUtil.color(lang.getString("messages." + section + "." + path)))));
    }

    @Override
    public String getMessage(String path) {
        return messages.get(path);
    }

}
