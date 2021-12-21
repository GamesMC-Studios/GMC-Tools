package pl.refertv.tools.cmds;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class FlyCommand extends CommandBase {

    @Override
    protected void onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.fly")) {
                if (p.getAllowFlight() == true) {
                    p.setAllowFlight(false);
                }
                else {
                if (p.getAllowFlight() == false){
                        p.setAllowFlight(true);
                    }
                }
            }
        }
    }