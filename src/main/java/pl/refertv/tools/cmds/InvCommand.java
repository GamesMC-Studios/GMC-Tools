package pl.refertv.tools.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.Tools;

public class InvCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.invsee")) {
            if (args.length == 1) {
                Player gracz = Bukkit.getPlayer(args[0]);
                if (gracz == null) {
                    p.sendMessage("§cGracz " + args[0] + " nie jest online!");
                    return false;
                }
                p.openInventory(gracz.getInventory());
                p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 2F, 1F);
            }
            if (args.length == 0) {
                p.openInventory(p.getInventory());
                p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 2F, 1F);
            }
        }
        p.sendMessage(Tools.noperms);
        return false;
    }

}
