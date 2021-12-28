package pl.refertv.tools.Config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Settings {

    private final Plugin plugin;
    private String language;

    public Settings(Plugin plugin) {
        this.plugin = plugin;
    }

    // (Re-)Load the config file
    public void reload() {
        plugin.reloadConfig();
        reloadFromFile(plugin.getConfig());
    }

    public void reloadFromFile(FileConfiguration config) {
        try {
            this.language = config.getString("language", "pl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getLanguage() {
        return language;
    }
}