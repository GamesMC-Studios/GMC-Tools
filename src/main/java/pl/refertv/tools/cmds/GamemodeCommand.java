package pl.refertv.tools.cmds;

import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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

public class GamemodeCommand extends CommandBase implements TabCompleter {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (!p.hasPermission("gamesmc.gamemode")) {
            MessageManager.sendMessage(p, "error_no_permission");
            return true;
        }
        if (args.length == 0) {
            MessageManager.sendMessage(p, "invaild_argument");
            return true;
        }
        GameMode mode = null;
        try {
            mode = GameMode.getByValue(Integer.parseInt(args[0]));
        } catch (Exception e) {
            for (GameMode modes : GameMode.values()) {
                if (modes.name().startsWith(args[0].toUpperCase())) {
                    mode = modes;
                    break;
                }
            }
        }
        if (mode == null) {
            MessageManager.sendMessage(p, "invaild_argument");
            return true;
        }
        if (args.length == 2) {
            Player gracz = Bukkit.getPlayer(args[1]);
            if (gracz == null) {
                MessageManager.sendMessage(p, "player_offline");
                return false;
            }
            gracz.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("gamemode_change", mode.toString().toLowerCase())).toComponent()), 20, 60, 20);
            gracz.setGameMode(mode);
            return true;
        }
        p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("gamemode_change", mode.toString().toLowerCase())).toComponent()), 20, 60, 20);
        p.setGameMode(mode);
        return true;
    }

    final static String[] ARGS = {"0", "1", "2", "3", "survival", "creative", "adventure", "spectator"};

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