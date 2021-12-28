package pl.refertv.tools;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.refertv.tools.Config.Config;
import pl.refertv.tools.Config.MessagesManager;
import pl.refertv.tools.cmds.*;
import pl.refertv.tools.listeners.Join;

public final class Tools extends JavaPlugin {

    private static Tools instance;
    public static Tools getInstance() {
        return instance;
    }

    public static String gmc = "§8•● §6☆ Games§fMC§e.pl §6☆ §8●•";
    public static String arg = "§cPodałeś niepoprawny argument";
    public static String mbp = "§cMusisz być graczem aby wykonać to polecenie";
    public static String noperms = "§cNie masz uprawnień do wykonania tego polecenia, lub takie polecenie nie istnieje.";
    public static String error = "§cWystąpił nieoczekiwany błąd.";

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

        instance = this;

        registerCommands();
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Config.setup();
        Config.get().addDefault("lang", "en");
        MessagesManager.setup();
        MessagesManager.get().addDefault("title", "§8•● §6☆ Games§fMC§e.pl §6☆ §8●•");
        MessagesManager.get().addDefault("noperms", "§cNie masz uprawnień do wykonania tego polecenia, lub takie polecenie nie istnieje.");
        MessagesManager.get().addDefault("error", "§cWystąpił niespodziewany błąd.");
        MessagesManager.get().addDefault("mbp", "§cMusisz być graczem aby wykonać to polecenie");
        MessagesManager.get().addDefault("arg", "§cPodałeś niepoprawny argument");
        MessagesManager.get().options().copyDefaults(true);
        MessagesManager.save();


        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(new Join(), this);;
            getLogger().info("Ładuję listenery...");
        } else {
            getLogger().info("Nie mogę znaleźć PlaceholderAPI, zainstaluj ten plugin na serwerze.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    private void registerCommands() {
        getLogger().info("Ładowanie komend...");
        this.getCommand("gamemode").setExecutor(new GamemodeCommand());
        this.getCommand("fly").setExecutor(new FlyCommand());
        this.getCommand("online").setExecutor(new OnlineCommand());
        this.getCommand("head").setExecutor(new HeadCommand());
        this.getCommand("rename").setExecutor(new RenameCommand());
        this.getCommand("lore").setExecutor(new LoreCommand());
        this.getCommand("speed").setExecutor(new FlySpeedCommand());
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
        this.getCommand("smitchingtable").setExecutor(new SmitchingTableCommand());
        this.getCommand("stonecutter").setExecutor(new StonecutterCommand());
        this.getCommand("clear").setExecutor(new ClearCommand());
        this.getCommand("getpos").setExecutor(new GetPosCommand());

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