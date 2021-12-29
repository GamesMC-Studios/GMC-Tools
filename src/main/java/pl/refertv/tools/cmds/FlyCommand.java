package pl.refertv.tools.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.MessageManager;
import pl.refertv.tools.Tools;

public class FlyCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.fly")) {
            if (args.length > 1) {
                p.sendTitle("e", "e", 10, 10, 10);
            }
            if (args.length == 0) {
                if (p.getAllowFlight() == true) {
                    p.setAllowFlight(false);
                    p.sendTitle(Tools.gmc, "§fLatanie zostało §cwyłączone", 10, 30, 10);
                } else {
                    if (p.getAllowFlight() == false) {
                        p.setAllowFlight(true);
                        p.sendTitle(Tools.gmc, "§fLatanie zostało §awłączone", 10, 30, 10);
                    }
                }
            }
            if (p.hasPermission("gamesmc.fly.others")) {
                if (args.length == 1) {
                    Player gracz = Bukkit.getPlayer(args[0]);
                    if (gracz == null) {
                        MessageManager.sendMessage(p, "player_offline");
                        return false;
                    }
                    if (gracz.getAllowFlight() == true) {
                        gracz.setAllowFlight(false);
                        gracz.sendTitle(Tools.gmc, "§fLatanie zostało §cwyłączone §fprzez §6" + p.getName(), 10, 30, 10);
                        p.sendTitle(Tools.gmc, "§cWyłączyłeś §flatanie dla użytkownika §6" + args[0], 10, 30, 10);
                    } else {
                        if (gracz.getAllowFlight() == false) {
                            gracz.setAllowFlight(true);
                            gracz.sendTitle(Tools.gmc, "§fLatanie zostało §ayłączone §fprzez §6" + p.getName(), 10, 30, 10);
                            p.sendTitle(Tools.gmc, "§aWłączyłeś §flatanie dla użytkownika §6" + args[0], 10, 30, 10);
                        }
                    }
                }
            }
            else {
                MessageManager.sendMessage(p, "error_no_permission");
            }
        } else {
            MessageManager.sendMessage(p, "error_no_permission");
        }
        return false;
    }
}