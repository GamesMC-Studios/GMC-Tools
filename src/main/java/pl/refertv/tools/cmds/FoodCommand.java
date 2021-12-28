package pl.refertv.tools.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.MessageManager;

public class FoodCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.food")) {
            if (args.length == 1) {
                Player gracz = Bukkit.getPlayer(args[0]);
                if (gracz == null) {
                    p.sendMessage("§cGracz " + args[0] + " nie jest online!");
                    return false;
                }
                gracz.setFoodLevel(20);
                gracz.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 2F, 1F);
            }
            if (args.length == 0) {
                p.setFoodLevel(20);
                p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 2F, 1F);
            }
        } else {
            MessageManager.sendMessage(p, "error_no_permission");
        }
        return false;
    }

}
