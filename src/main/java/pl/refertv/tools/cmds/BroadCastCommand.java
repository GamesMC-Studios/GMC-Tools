package pl.refertv.tools.cmds;

import de.themoep.minedown.MineDown;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import pl.refertv.tools.MessageManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BroadCastCommand extends CommandBase implements TabCompleter {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.broadcast")) {
            if (args.length == 0) {
            MessageManager.sendMessage(p, "invalid_syntax", cmd.getUsage());
            return true;
            }

            StringBuilder str = new StringBuilder();
            for (int i = 1; i < args.length; ++i) {
                str.append(args[i] + " ");
            }

            switch (args[0]) {
                case "chat" -> {
                    if (args.length == 1) {
                        MessageManager.sendMessage(p, "needed_message");
                    } else {
                        Bukkit.broadcast(LegacyComponentSerializer.legacyAmpersand().deserialize("\n" + "&c&lSERWER &8» &7" + str + "\n"));
                        break;
                    }
                }
                case "title" -> {
                    if (args.length == 1) {
                        MessageManager.sendMessage(p, "needed_message");
                    } else {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(str.toString()).toComponent()), 20, 100, 20);
                        }
                    }
                    break;
                }
                case "actionbar" -> {
                    if (args.length == 1) {
                        MessageManager.sendMessage(p, "needed_message");
                    } else {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.sendActionBar(TextComponent.toLegacyText(new MineDown(str.toString()).toComponent()));
                        }
                    }
                    break;
                }
                default -> {
                    MessageManager.sendMessage(p, "invaild_argument");
                }
            }
        } else {
            MessageManager.sendMessage(p, "error_no_permission");
        }
        return true;
    }

    final static String[] ARGS = {"chat", "title", "actionbar"};

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        Player p = (Player) sender;
        if (command.getPermission() != null) {
            if (p.hasPermission(command.getPermission())) {
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