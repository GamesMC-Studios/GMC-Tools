package pl.refertv.tools.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.MessageManager;

public class CartographyTableCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.cartographytable")) {
            if (args.length == 0) {
                p.openCartographyTable(null, true);
                p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 2F, 1F);
            }
            if (p.hasPermission("gamesmc.cartographytable.others")) {
                if (args.length == 1) {
                    Player gracz = Bukkit.getPlayer(args[0]);
                    if (gracz == null) {
                        MessageManager.sendMessage(p, "player_offline");
                        return false;
                    }
                    gracz.openCartographyTable(null, true);
                    gracz.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 2F, 1F);
                }
            } else {
                MessageManager.sendMessage(p, "error_no_permission");
            }
        } else {
            MessageManager.sendMessage(p, "error_no_permission");
        }
        return false;
    }
}

