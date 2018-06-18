package ru.bristol.prefixsystem.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public interface FileConfig {

    void init(Plugin plugin, String name, boolean isResource, boolean isReplace);

    FileConfiguration getFileConfiguration();

    File getFile();

    void saveFileConfiguration();

}
