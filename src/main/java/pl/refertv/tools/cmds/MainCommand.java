package pl.refertv.tools.cmds;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.Config.Settings;
import pl.refertv.tools.MessageManager;

public class MainCommand extends CommandBase {

    private static Settings settings;
    public static Settings getSettings() {
        return settings;
    }

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            p.sendMessage("§fThis server using GamesMC Tools\n(C) by ReferTV\n§f\n§ahttps://github.com/GamesMC-Studios/GMC-Tools\n§fAvaliable subcommands:\n§8⌊ §areload");
            return true;
        }
        if (p.hasPermission("gamesmc.admin")) {
            if (args[0].equalsIgnoreCase("reload")) {
                p.sendTitle("e", "Przeładowałeś pliki konfiguracyjne.");
                p.sendMessage("§7Przeładowałeś pliki konfiguracyjne.");
                settings.reload();
            }
        } else {
            MessageManager.sendMessage(p, "error_no_permission");
        }
        return false;
    }
}