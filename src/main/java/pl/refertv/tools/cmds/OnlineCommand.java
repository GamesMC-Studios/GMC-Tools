package pl.refertv.tools.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.MessageManager;
import pl.refertv.tools.Tools;

import java.util.Collection;

public class OnlineCommand extends CommandBase {

    @Override
    public boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.online")) {
            StringBuilder online = new StringBuilder();
            final Collection<? extends Player> players = Bukkit.getOnlinePlayers();
            for (Player player : players) {
                // If a player is hidden from the sender don't show them in the list
                if (p instanceof Player && !((Player) p).canSee(player))
                    continue;
                if (online.length() > 0) {
                    online.append(", ");
                }
                online.append(player.getDisplayName());
            }
            p.sendMessage("§fAktualnie na serwerze jest §e" + players.size() + "/" + Bukkit.getMaxPlayers() + " §fgraczy online.§7\n" + online.toString());
            p.sendTitle(Tools.gmc, "§eAktualnie na serwerze jest: " + players.size() + "/" + Bukkit.getMaxPlayers(), 10, 50, 10);
            return true;
        } else {
            MessageManager.sendMessage(p, "error_no_permission");
        }
        return false;
    }
}
