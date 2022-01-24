package pl.refertv.tools.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.MessageManager;

public class InvCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.invsee")) {
            if (args.length == 0) {
                p.openInventory(p.getInventory());
            }
            if (args.length == 1) {
                if (p.hasPermission("gamesmc.invsee.others")) {
                    Player gracz = Bukkit.getPlayer(args[0]);
                    if (gracz == null) {
                        MessageManager.sendMessage(p, "player_offline");
                        return false;
                    }
                    p.openInventory(gracz.getInventory());
                } else {
                    MessageManager.sendMessage(p, "error_no_permission");
                }
            }
            if (args.length > 1) {
                MessageManager.sendMessage(p, "too_many_arguments");
            }
        } else {
            MessageManager.sendMessage(p, "error_no_permission");
        }
        return false;
    }
}
