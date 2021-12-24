package pl.refertv.tools;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import pl.refertv.tools.cmds.*;
import pl.refertv.tools.listeners.Join;

public final class Tools extends JavaPlugin {

    public static Permission permission = null;;

    private static Tools instance;;

    public static Tools getInstance() {

        return instance;
    }

    public static String gmc = "§8•● §6☆ Games§fMC§e.pl §6☆ §8●•";
    public static String arg = "§cPodałeś niepoprawny argument";
    public static String mbp = "§cMusisz być graczem aby wykonać to polecenie";
    public static String noperms = "§cNie masz uprawnień na wykonanie tego polecenia.";

    private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

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

        if (!setupPermissions()) {
            this.getLogger().severe("Disabled due to no Vault dependency found!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        registerCommands();
        registerListeners();
        setupPermissions();
        this.setupPermissions();

        instance = this;

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(new Join(), this);;
            getLogger().info("Ładuję listenery");
        } else {
            getLogger().info("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }


    private void registerListeners() {
    }

    private void registerCommands() {
        this.getCommand("gamemode").setExecutor(new GamemodeCommand());
        this.getCommand("fly").setExecutor(new FlyCommand());
        this.getCommand("online").setExecutor(new OnlineCommand());
        this.getCommand("head").setExecutor(new HeadCommand());
        this.getCommand("rename").setExecutor(new RenameCommand());
        this.getCommand("lore").setExecutor(new LoreCommand());
        this.getCommand("speed").setExecutor(new FlySpeedCommand());
        this.getCommand("enderchest").setExecutor(new EnderChestCommand());
        this.getCommand("inv").setExecutor(new InvCommand());
        this.getCommand("heal").setExecutor(new HealCommand());
        this.getCommand("food").setExecutor(new FoodCommand());
        this.getCommand("time").setExecutor(new TimeCommand());
    }



    @Override
    public void onDisable() {
        getLogger().info("\n" +
                "  ____                               __  __   ____   _____                _      \n" +
                " / ___|  __ _  _ __ ___    ___  ___ |  \\/  | / ___| |_   _|  ___    ___  | | ___ \n" +
                "| |  _  / _` || '_ ` _ \\  / _ \\/ __|| |\\/| || |       | |   / _ \\  / _ \\ | |/ __|\n" +
                "| |_| || (_| || | | | | ||  __/\\__ \\| |  | || |___    | |  | (_) || (_) || |\\__ \\\n" +
                " \\____| \\__,_||_| |_| |_| \\___||___/|_|  |_| \\____|   |_|   \\___/  \\___/ |_||___/\n" +
                "                                                        §fby " + this.getDescription().getAuthors() + " §a" + this.getDescription().getVersion());
        getLogger().info("Dziękujemy za używanie naszego pluginu.\n" + "Wyłączam plugin...");
        Bukkit.getServer().getScheduler().cancelTasks(this);
    }
}
