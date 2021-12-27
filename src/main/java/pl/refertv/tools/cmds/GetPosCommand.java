package pl.refertv.tools.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.Tools;

public class GetPosCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.getpos")) {
            if (args.length == 0) {
                p.sendMessage("§eTwoja lokalizacja: \n§7X: §b" + p.getLocation().getX() + "\n§7Y: §b" + p.getLocation().getY() + "\n§7Z: §b" + +p.getLocation().getZ());
            }
            if (p.hasPermission("gamesmc.getpos.others")) {
                if (args.length == 1) {
                    Player gracz = Bukkit.getPlayer(args[0]);
                    if (gracz == null) {
                        p.sendMessage("§cGracz " + args[0] + " nie jest online!");
                        return false;
                    }
                    p.sendMessage("§eLokalizacja gracza" + gracz.getName() + "\n§7X: §b" + gracz.getLocation().getX() + "\n§7Y: §b" + gracz.getLocation().getY() + "\n§7Z: §b" + +gracz.getLocation().getZ());

                }
            } else {
                p.sendMessage(Tools.noperms);
            }
        } else {
            p.sendMessage(Tools.noperms);
        }
        return false;
    }
}
