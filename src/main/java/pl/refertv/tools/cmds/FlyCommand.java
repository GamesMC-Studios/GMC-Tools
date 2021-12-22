package pl.refertv.tools.cmds;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.Tools;

public class FlyCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.fly")) {
                if (p.getAllowFlight() == true) {
                    p.setAllowFlight(false);
                    p.sendTitle(Tools.gmc, "§fLatanie zostało §cwyłączone", 10, 30, 10);
                }
                else {
                if (p.getAllowFlight() == false){
                        p.setAllowFlight(true);
                    p.sendTitle(Tools.gmc, "§fLatanie zostało §awłączone", 10, 30, 10);
                    }
                }
            }
        return false;
    }
    }