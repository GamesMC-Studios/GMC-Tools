package pl.refertv.tools;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.refertv.tools.Config.Settings;
import pl.refertv.tools.cmds.*;
import pl.refertv.tools.listeners.Join;
import pl.refertv.tools.listeners.Leave;
import pl.refertv.tools.utils.CooldownManager;

public final class Tools extends JavaPlugin {

    private static Settings settings;
    private static Tools instance;

    public static Settings getSettings() {
        return settings;
    }

    public static Tools getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
        settings = new Settings(this);
    }

    @Override
    public void onEnable() {
        long startTime = System.currentTimeMillis();
        getLogger().info("\n" +
                "§f  ____                               __  __   ____   _____                _      \n" +
                " / ___|  __ _  _ __ ___    ___  ___ |  \\/  | / ___| |_   _|  ___    ___  | | ___ \n" +
                "| |  _  / _` || '_ ` _ \\  / _ \\/ __|| |\\/| || |       | |   / _ \\  / _ \\ | |/ __|\n" +
                "| |_| || (_| || | | | | ||  __/\\__ \\| |  | || |___    | |  | (_) || (_) || |\\__ \\\n" +
                " \\____| \\__,_||_| |_| |_| \\___||___/|_|  |_| \\____|   |_|   \\___/  \\___/ |_||___/\n" +
                "                                                        §fby " + this.getDescription().getAuthors() + " §a" + this.getDescription().getVersion() + "\n§7Plugin z narzędziami dla administratorów\n§7Załadowałem plugin w §a" + (System.currentTimeMillis() - startTime) + "ms§7.\n§7Wykryta wersja serwera: " + Bukkit.getVersion().split("-")[1]);

        instance = this;

        registerCommands();

        CooldownManager.setupCooldown();

        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        settings.reload();

        MessageManager.loadMessages(Tools.getSettings().getLanguage());

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(new Join(), this);
            Bukkit.getPluginManager().registerEvents(new Leave(), this);
            getLogger().info("Ładuję listenery...");
        } else {
            getLogger().info("Nie mogę znaleźć PlaceholderAPI, zainstaluj ten plugin na serwerze.\nPlaceholdery w niektórych wiadomościach mogą nie działać.");
        }
    }

    private void registerCommands() {
        getLogger().info("Ładowanie komend...");
        this.getCommand("gamemode").setExecutor(new GamemodeCommand());
        this.getCommand("fly").setExecutor(new FlyCommand());
        this.getCommand("online").setExecutor(new OnlineCommand());
        this.getCommand("head").setExecutor(new HeadCommand());
        this.getCommand("item").setExecutor(new ItemCommand());
        this.getCommand("speed").setExecutor(new SpeedCommand());
        this.getCommand("heal").setExecutor(new HealCommand());
        this.getCommand("food").setExecutor(new FoodCommand());
        this.getCommand("time").setExecutor(new TimeCommand());
        this.getCommand("pogoda").setExecutor(new WeatherCommand());
        this.getCommand("inv").setExecutor(new InvCommand());
        this.getCommand("crafting").setExecutor(new CraftingCommand());
        this.getCommand("enderchest").setExecutor(new EnderChestCommand());
        this.getCommand("anvil").setExecutor(new AnvilCommand());
        this.getCommand("grindstone").setExecutor(new GrindstoneCommand());
        this.getCommand("loom").setExecutor(new LoomCommand());
        this.getCommand("cartographytable").setExecutor(new CartographyTableCommand());
        this.getCommand("smithingtable").setExecutor(new SmithingTableCommand());
        this.getCommand("stonecutter").setExecutor(new StonecutterCommand());
        this.getCommand("clear").setExecutor(new ClearCommand());
        this.getCommand("getpos").setExecutor(new GetPosCommand());
        this.getCommand("hat").setExecutor(new HatCommand());
        this.getCommand("tools").setExecutor(new MainCommand());
        this.getCommand("slots").setExecutor(new SlotsCommand());
        this.getCommand("live").setExecutor(new LiveStreamCommand());
        this.getCommand("repair").setExecutor(new RepairCommand());
        this.getCommand("god").setExecutor(new GodCommand());
        this.getCommand("broadcast").setExecutor(new BroadCastCommand());

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
    }
}