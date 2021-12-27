package pl.refertv.tools.cmds;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.Tools;

public class TimeCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.czas")) {
            if (args.length == 0) {
                p.sendTitle(Tools.gmc, Tools.arg);
                return true; }
            { if (args[0].equalsIgnoreCase("dzień") || (args[0].equalsIgnoreCase("day"))) {
                p.getWorld().setTime(6000);
                Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(Tools.gmc, " Czas został zmieniony na §edzień §fprzez administratora §6" + p.getName()));
            } if (args[0].equalsIgnoreCase("noc") || args[0].equalsIgnoreCase("night")) {
                p.getWorld().setTime(18000);
                Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(Tools.gmc, "Czas został zmieniony na §8noc §fprzez administratora §6" + p.getName()));
            } if (args[0].equalsIgnoreCase("zatrzymaj") || (args[0].equalsIgnoreCase("lock"))) {
                p.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(Tools.gmc, "Czas został zatrzymany §fprzez administratora §6" + p.getName()));
            } if (args[0].equalsIgnoreCase("wznów") || (args[0].equalsIgnoreCase("unlock"))) {
                p.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
                Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(Tools.gmc, "Czas został wznowiony §fprzez administratora §6" + p.getName()));
            }
            }
        } else {
            p.sendMessage(Tools.noperms);
        }
        return false;
    }
}