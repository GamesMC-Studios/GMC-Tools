package pl.refertv.tools.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.Tools;

import java.util.HashMap;
import java.util.Map;

public class TimeCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.timeset")) {
            if (args.length == 0)
                p.sendTitle(Tools.gmc, Tools.arg, 10, 50, 10);
            else if (args[0].equalsIgnoreCase("day") || (args[0].equalsIgnoreCase("dzień")))
                p.getWorld().setTime(6000);
            else if (args[0].equalsIgnoreCase("night") || (args[0].equalsIgnoreCase("noc")))
                p.getWorld().setTime(18000);
            return false;
        }
        p.sendMessage(Tools.noperms);
        return false;
    }
}