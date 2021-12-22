package pl.refertv.tools.cmds;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.Tools;

import java.util.List;
import java.util.stream.Collectors;

public class OnlineCommand extends CommandBase {

    @Override
    protected void onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.online")) {
            if(cmd.getName().equalsIgnoreCase("online")){
                p.sendMessage("Aktualnie na serwerze jest " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + " graczy online");
                p.sendTitle(Tools.gmc, "§eAktualnie na serwerze jest: " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers(), 10, 50, 10);
            }
        }
    }
}
