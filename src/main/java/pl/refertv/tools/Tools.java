package pl.refertv.tools;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;
import pl.refertv.tools.cmds.*;

import java.util.logging.Logger;

public final class Tools extends JavaPlugin {

    private static Tools instance;
    public static Tools getInstance() {
        return instance;
    }

    public static String gmc = "§8•● §6☆ Games§fMC§e.pl §6☆ §8●•";

    @Override
    public void onEnable() {
        getLogger().info("\n" +
                "  ____                               __  __   ____   _____                _      \n" +
                " / ___|  __ _  _ __ ___    ___  ___ |  \\/  | / ___| |_   _|  ___    ___  | | ___ \n" +
                "| |  _  / _` || '_ ` _ \\  / _ \\/ __|| |\\/| || |       | |   / _ \\  / _ \\ | |/ __|\n" +
                "| |_| || (_| || | | | | ||  __/\\__ \\| |  | || |___    | |  | (_) || (_) || |\\__ \\\n" +
                " \\____| \\__,_||_| |_| |_| \\___||___/|_|  |_| \\____|   |_|   \\___/  \\___/ |_||___/\n" +
                "                                                        §fby " + this.getDescription().getAuthors() + " §a" + this.getDescription().getVersion());
        getLogger().info("Plugin z narzędziami dla administratorów");

        registerCommands();


    }

    private void registerCommands() {

        this.getCommand("gamemode").setExecutor(new GamemodeCommand());
        this.getCommand("fly").setExecutor(new FlyCommand());
        this.getCommand("online").setExecutor(new OnlineCommand());
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onDisable() {
        getLogger().info("Wyłączam plugin" + this.getDescription().getName() + " " + this.getDescription().getVersion());
        Bukkit.getServer().getScheduler().cancelTasks(this);
    }
}
