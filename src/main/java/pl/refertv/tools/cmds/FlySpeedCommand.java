package pl.refertv.tools.cmds;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.MessageManager;

public class FlySpeedCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (!p.hasPermission("gamesmc.flyspeed")) {
            if (args.length == 1) {
                if (p == null) {
                    return true;
                }
                try {
                    float speed = Float.parseFloat(args[0]) / 100f;
                    if (speed > 1f) {
                        speed = 1f;
                    }
                    if (speed < 0f) {
                        speed = 0f;
                    }
                    p.setFlySpeed(speed);
                    p.sendMessage("Szybkość latania ustawiona");
                } catch (Exception e) {
                    p.sendMessage("Użyj /speed [speed]");
                }
            }
            return true;
        } else {
            MessageManager.sendMessage(p, "error_no_permission");
        }
        return false;
    }
}