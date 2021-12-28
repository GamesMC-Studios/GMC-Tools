package pl.refertv.tools.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.Config.MessagesManager;
import pl.refertv.tools.Tools;

public class WeatherCommand extends CommandBase{

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.weather")) {
            if (args.length == 0) {
                p.sendTitle(Tools.gmc, Tools.arg);
                return true; }
            if (args[0].equalsIgnoreCase("deszcz") || (args[0].equalsIgnoreCase("rain"))); {
                p.getWorld().setStorm(true);
                Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(Tools.gmc, "Pogoda została zmieniona na deszczową przez " + p.getName()));
            } if (args[0].equalsIgnoreCase("burza") || (args[0].equalsIgnoreCase("thunder"))); {
                p.getWorld().setThundering(true);
                Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(Tools.gmc, "Pogoda została zmieniona na burzę przez " + p.getName()));
            } if (args[0].equalsIgnoreCase("clear") || (args[0].equalsIgnoreCase("wyczyść"))); {
                p.getWorld().setClearWeatherDuration(10000);
                Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(Tools.gmc, "Pogoda została ustawiona na bezchmurną przez " + p.getName()));
        }
        }
        else {
            p.sendMessage(MessagesManager.get().getString("noperms"));
        }
        return false;
    }
}
