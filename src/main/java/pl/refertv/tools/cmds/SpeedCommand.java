package pl.refertv.tools.cmds;

import de.themoep.minedown.MineDown;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.MessageManager;

public class SpeedCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (args.length < 1) {
            p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("not_enough_arguments")).toComponent()));
            return true;
        }
        try {
            Integer.parseInt(args[0]);
            double x = Integer.valueOf(args[0]);
            if (p.isFlying() == true) {
                setFlySpeed(p, x);
            } else if (p.isFlying() == false) {
                setWalkSpeed(p, x);
            }
        } catch (Exception e) {
            MessageManager.sendMessage(p, "provide_int");
        }
        return false;
    }
        public void setFlySpeed (Player p, double speed){
            if (speed > 10) {
                double b = speed - 10;
                speed -= b;
            }
            if (speed < 0) {
                speed -= speed;
            }
            double a = speed / 10;
            float value = (float) a;
            p.setFlySpeed(value);
            p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("fly_speed", String.valueOf(value))).toComponent()));
        }
        public void setWalkSpeed (Player p, double speed){
            if (speed > 10) {
                double b = speed - 10;
                speed -= b;
            }
            if (speed < 0) {
                speed -= speed;
            }
            double a = speed / 10;
            float value = (float) a;
            p.setWalkSpeed(value);
            MessageManager.sendMessage(p, "walk_speed", String.valueOf(value));
            p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("walk_speed", String.valueOf(value))).toComponent()));
    }
}
