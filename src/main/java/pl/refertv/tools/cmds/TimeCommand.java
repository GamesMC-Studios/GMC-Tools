package pl.refertv.tools.cmds;

import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.MessageManager;
import pl.refertv.tools.Tools;

public class TimeCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.czas")) {
            if (args.length == 0) {
                p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("invaild_argument")).toComponent()), 20, 60, 20);
                return true;
            }
            switch (args[0]) {
                case "dzień", "day" -> {
                    p.getWorld().setTime(6000);
                    Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("time_day")).toComponent()), 20, 60, 20));
                }
                case "noc", "night" -> {
                    p.getWorld().setTime(18000);
                    Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("time_night")).toComponent()), 20, 60, 20));
                }
                case "zatrzymaj", "lock" -> {
                    Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("time_lock")).toComponent()), 20, 60, 20));
                    p.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                }
                case "wznów", "unlock" -> {
                    Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("time_unlock")).toComponent()), 20, 60, 20));
                    p.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
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