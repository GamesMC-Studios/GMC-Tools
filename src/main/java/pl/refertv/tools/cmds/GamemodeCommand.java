package pl.refertv.tools.cmds;

import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import pl.refertv.tools.MessageManager;
import pl.refertv.tools.Tools;

import java.util.*;
import java.util.stream.Collectors;

public class GamemodeCommand extends CommandBase implements TabCompleter {

    private static final Tools plugin = Tools.getInstance();

    private Map<String, GameMode> gm = new HashMap<>();

    public GamemodeCommand() {
        gm.put("0", GameMode.SURVIVAL);
        gm.put("1", GameMode.CREATIVE);
        gm.put("2", GameMode.ADVENTURE);
        gm.put("3", GameMode.SPECTATOR);
        gm.put("survival", GameMode.SURVIVAL);
        gm.put("creative", GameMode.CREATIVE);
        gm.put("adventure", GameMode.ADVENTURE);
        gm.put("spectator", GameMode.SPECTATOR);
    }

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.gamemode")) {
            if (args.length == 0 || args.length > 2) {
                p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("not_enough_arguments")).toComponent()), 20, 60, 20);
                return true;
            } else {
                Player player;
                if (args.length == 1) {
                    if (!(p instanceof Player)) {
                        p.sendMessage("Nie możesz być konsolą");
                        return true;
                    }
                    player = ((Player) p);
                } else {
                    player = Bukkit.getPlayer(args[1]);
                    if (player == null) {
                        MessageManager.sendMessage(p, "player_offline");
                        return true;
                    }
                }
                if (!gm.containsKey(args[0])) {
                    p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("invaild_argument")).toComponent()), 20, 60, 20);
                    return true;
                }
                GameMode mode = gm.get(args[0]);
                player.setGameMode(mode);
                if (player != p) {
                    p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("gamemode_change_by" + p.getName(), mode.toString().toLowerCase())).toComponent()), 20, 60, 20);
                }
                p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("gamemode_change", mode.toString().toLowerCase())).toComponent()), 20, 60, 20);
            }
            return true;
        } else {
            MessageManager.sendMessage(p, "error_no_permission");
        }
        return false;
    }

    final static String[] ARGS = {"0", "1", "2", "3", "survival", "creative", "adventure", "spectator"};

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