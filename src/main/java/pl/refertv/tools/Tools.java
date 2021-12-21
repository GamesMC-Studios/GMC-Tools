package pl.refertv.tools;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Tools extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Uruchamiam plugin" + this.getDescription().getName() + " wersja " + this.getDescription().getVersion());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
