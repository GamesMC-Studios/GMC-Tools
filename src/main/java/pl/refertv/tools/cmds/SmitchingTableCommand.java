package pl.refertv.tools.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.MessageManager;

public class SmitchingTableCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.smitchningtable")) {
            if (args.length == 0) {
                p.openSmithingTable(null, true);
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 2F, 2F);
            }
            if (p.hasPermission("gamesmc.smitchingtable.others")) {
                if (args.length == 1) {
                    Player gracz = Bukkit.getPlayer(args[0]);
                    if (gracz == null) {
                        p.sendMessage("§cGracz " + args[0] + " nie jest online!");
                        return false;
                    }
                    gracz.openSmithingTable(null, true);
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
