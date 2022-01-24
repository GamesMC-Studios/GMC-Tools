package pl.refertv.tools.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.MessageManager;

public class StonecutterCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.stonecutter")) {
            if (args.length == 0) {
                p.openStonecutter(null, true);
            }
            if (args.length == 1) {
                if (p.hasPermission("gamesmc.stonecutter.others")) {
                    Player gracz = Bukkit.getPlayer(args[0]);
                    if (gracz == null) {
                        MessageManager.sendMessage(p, "player_offline");
                        return false;
                    }
                    gracz.openStonecutter(null, true);
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
