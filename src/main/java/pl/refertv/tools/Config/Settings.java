package pl.refertv.tools.Config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Settings {

    private final Plugin plugin;
    private String language;
    private boolean join_listener;
    private boolean quit_listener;

    public Settings(Plugin plugin) {
        this.plugin = plugin;
    }

    public void reload() {
        plugin.reloadConfig();
        reloadFromFile(plugin.getConfig());
    }

    public void reloadFromFile(FileConfiguration config) {
        try {
            this.language = config.getString("general.language", "pl");
            this.join_listener = config.getBoolean("general.join_listener", true);
            this.quit_listener = config.getBoolean("general.quit_listener", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getLanguage() {
        return language;
    }
    public boolean getJoinListener() { return join_listener; }
    public boolean getQuitListener() { return quit_listener; }
}