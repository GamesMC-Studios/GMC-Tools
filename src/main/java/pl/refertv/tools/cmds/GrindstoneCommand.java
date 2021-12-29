package pl.refertv.tools.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.MessageManager;

public class GrindstoneCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.grindstone")) {
            if (args.length == 0) {
                p.openGrindstone(null, true);
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 2F, 2F);
            }
            if (p.hasPermission("gamesmc.grindstone.others")) {
                if (args.length == 1) {
                    Player gracz = Bukkit.getPlayer(args[0]);
                    if (gracz == null) {
                        MessageManager.sendMessage(p, "player_offline");
                        return false;
                    }
                    gracz.openGrindstone(null, true);
                    gracz.playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 2F, 2F);
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
