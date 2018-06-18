package ru.bristol.prefixsystem.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class FileConfigImpl implements FileConfig {

    private FileConfiguration config;
    private File configFile;

    protected FileConfigImpl(Plugin plugin, String name, boolean isResource, boolean isReplace) {
         init(plugin, name + ".yml", isResource, isReplace);
    }

    @Override
    public void init(Plugin plugin, String name, boolean isResource, boolean isReplace) {
        try {
            configFile = new File(plugin.getDataFolder(), name);
            if(isResource) {
                plugin.saveResource(name, isReplace);
            } else {
                if(configFile.exists() && isReplace) configFile.delete();
                if(configFile.exists() && !isReplace) return;
                configFile.createNewFile();
            }
            config = YamlConfiguration.loadConfiguration(configFile);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public FileConfiguration getFileConfiguration() {
        return config;
    }

    @Override
    public File getFile() {
        return configFile;
    }

    @Override
    public void saveFileConfiguration() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    config.save(configFile);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

}
