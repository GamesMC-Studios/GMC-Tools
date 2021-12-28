package pl.refertv.tools.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.MessageManager;
import pl.refertv.tools.Tools;

public class AnvilCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.anvil")) {
            if (args.length == 0) {
                p.openAnvil(null, true);
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 2F, 2F);
            }
            if (p.hasPermission("gamesmc.anvil.others")) {
                if (args.length == 1) {
                    Player gracz = Bukkit.getPlayer(args[0]);
                    if (gracz == null) {
                        p.sendMessage("§cGracz " + args[0] + " nie jest online!");
                        return false;
                    }
                    gracz.openAnvil(null, true);
                    gracz.playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 2F, 2F);
                }
            } else {
                p.sendMessage(Tools.noperms);
            }
        } else {
            MessageManager.sendMessage(p, "error_no_permission");
        }
        return false;
    }
}
