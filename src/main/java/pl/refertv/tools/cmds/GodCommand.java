package pl.refertv.tools.cmds;

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

public class GodCommand extends CommandBase implements TabCompleter {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.god")) {
            if (args.length == 0) {
                MessageManager.sendMessage(p, "invalid_syntax", cmd.getUsage());
                return true;
            }
            switch (args[0]) {
                case "on" -> {
                    p.setInvulnerable(true);
                    MessageManager.sendMessage(p, "god_comamnd", MessageManager.getRawMessage("enable"));
                }
                case "off" -> {
                    p.setInvulnerable(false);
                    MessageManager.sendMessage(p, "god_comamnd", MessageManager.getRawMessage("disable"));
                }
                default -> {
                    MessageManager.sendMessage(p, "invalid_syntax", cmd.getUsage());
                }

            }
        } else {
            MessageManager.sendMessage(p, "error_no_permission");
        }
        return false;
    }

    final static String[] ARGS = {"on", "off"};

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
