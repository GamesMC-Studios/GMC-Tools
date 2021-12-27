package pl.refertv.tools.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.Tools;

public class ClearCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.clear")) {
            if (args.length == 0) {
                    p.getInventory().clear();
                    p.sendTitle(Tools.gmc, "Twój ekwipunek został wyczyszczony");
                }
                if (p.hasPermission("gamesmc.clear.others")) {
                    if (args.length == 1) {
                        Player gracz = Bukkit.getPlayer(args[0]);
                        if (gracz == null) {
                            p.sendMessage("§cGracz " + args[0] + " nie jest online!");
                            return false;
                        }
                        gracz.getInventory().clear();
                        gracz.sendTitle(Tools.gmc, "Twój ekwipunek został przez §6" + p.getName());
                        p.sendTitle(Tools.gmc, "Wyczysciłeś ekwipunek graczowi §6 " + gracz.getName());
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
