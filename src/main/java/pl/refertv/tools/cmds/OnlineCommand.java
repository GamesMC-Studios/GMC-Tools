package pl.refertv.tools.cmds;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class OnlineCommand extends CommandBase {

    @Override
    protected void onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.online")) {
            if(cmd.getName().equalsIgnoreCase("online")){
                p.sendMessage("Aktualnie na serwerze jest " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
                p.sendTitle("§8•● §6☆ Games§fMC§e.pl §6☆ §8●•", "§e" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers(), 10, 50, 10);
            }
        }
    }
}
