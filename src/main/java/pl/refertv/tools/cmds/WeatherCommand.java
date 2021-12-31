package pl.refertv.tools.cmds;

import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.MessageManager;
import pl.refertv.tools.Tools;

public class WeatherCommand extends CommandBase{

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.weather")) {
            if (args.length == 0) {
                p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("invaild_argument")).toComponent()), 20, 60, 20);
                return true;
            }
            switch (args[0]) {
                case "deszcz", "rain" -> {
                    p.getWorld().setStorm(true);
                    Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("weather_rain")).toComponent()), 20, 60, 20));
                }
                case "burza", "thunder" -> {
                    p.getWorld().setStorm(true);
                    p.getWorld().setThundering(true);
                    Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("weather_thunder")).toComponent()), 20, 60, 20));
                }
                case "wyczyść", "clear" -> {
                    Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("weather_clear")).toComponent()), 20, 60, 20));
                    p.getWorld().setClearWeatherDuration(10000);
                }
                default -> {
                    MessageManager.sendMessage(p, "invaild_argument");
                }
            }
        } else {
            MessageManager.sendMessage(p, "error_no_permission");
        }
        return false;
    }
}
