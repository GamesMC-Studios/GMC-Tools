package pl.refertv.tools.cmds;

import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.MessageManager;

import java.util.Collection;

public class OnlineCommand extends CommandBase {

    @Override
    public boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.online")) {
            StringBuilder online = new StringBuilder();
            final Collection<? extends Player> players = Bukkit.getOnlinePlayers();
            for (Player player : players) {
                if (p instanceof Player && !((Player) p).canSee(player))
                    continue;
                if (online.length() > 0) {
                    online.append(", ");
                }
                online.append(player.getDisplayName());
            }
            Integer playersonline = players.size();
            Integer maxplayers = Bukkit.getMaxPlayers();
            MessageManager.sendMessage(p, "online_list", playersonline.toString(), maxplayers.toString(), online.toString());
            p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("online", playersonline.toString(), maxplayers.toString())).toComponent()));
            return true;
        } else {
            MessageManager.sendMessage(p, "error_no_permission");
        }
        return false;
    }
}
