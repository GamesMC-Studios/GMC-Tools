package pl.refertv.tools.cmds;

import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import pl.refertv.tools.MessageManager;
import pl.refertv.tools.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TimeCommand extends CommandBase implements TabCompleter {

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
                case "set", "ustaw" -> {
                    if (args.length == 2) {
                        p.getWorld().setTime(Integer.valueOf(args[1]));
                    } else {
                        MessageManager.sendMessage(p, "invaild_argument");
                    }
                    return true;
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
    final static String[] ARGS = {"dzień", "day", "noc", "zatrzymaj", "lock", "wznów", "unlock", "set", "ustaw"};

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        Player p = (Player) sender;
        if (command.getPermission() != null) {
            if (!p.hasPermission(command.getPermission())) {
                return Collections.emptyList();
            }
        }
        if (args.length == 1) {
            final List<String> TC = new ArrayList<>();
            StringUtil.copyPartialMatches(args[0], Arrays.asList(ARGS), TC);
            Collections.sort(TC);
            return TC;
        } else {
            return Collections.emptyList();
        }

    }
}