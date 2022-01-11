package pl.refertv.tools.cmds;

import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import pl.refertv.tools.Config.Settings;
import pl.refertv.tools.MessageManager;
import pl.refertv.tools.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainCommand extends CommandBase implements TabCompleter {

    private static final Tools plugin = Tools.getInstance();

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            p.sendMessage("§eThis server using GamesMC Tools plugin.\n(C) by ReferTV\n§f\n§ahttps://github.com/GamesMC-Studios/GMC-Tools\n§eAvaliable subcommands:\n§8⌊ §areload");
            return true;
        }
        if (p.hasPermission("gamesmc.admin")) {
            switch (args[0]) {
                case "reload" -> {
                        Tools.getSettings().reload();
                        MessageManager.loadMessages(Tools.getSettings().getLanguage());
                        plugin.getLogger().info("§aReloaded config and message files.");
                        p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown("All messages and configs have been reloaded.").toComponent()));
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
        final static String[] ARGS = {"reload"};

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