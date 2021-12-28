package pl.refertv.tools.cmds;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.Config.MessagesManager;

public class MainCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            p.sendMessage("§fThis server using GamesMC Tools\n(C) by ReferTV\n§f\n§ahttps://github.com/GamesMC-Studios/GMC-Tools\n§fAvaliable subcommands:\n§8⌊ §areload");
            return true;
        }
        if (p.hasPermission("gamesmc.admin")) {
            if (args[0].equalsIgnoreCase("reload")) {
                p.sendTitle(MessagesManager.get().getString("title"), "Przeładowałeś pliki konfiguracyjne.");
                p.sendMessage("§7Przeładowałeś pliki konfiguracyjne.");
                MessagesManager.reload();
            }
        } else {
            p.sendMessage(MessagesManager.get().getString("noperms"));
        }
        return false;
    }
}