package pl.refertv.tools.cmds;

import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.Config.Settings;
import pl.refertv.tools.MessageManager;
import pl.refertv.tools.Tools;

public class MainCommand extends CommandBase {

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
                plugin.getLogger().info("Reloaded config and message files.");
                p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown("All messages and configs have been reloaded.").toComponent()));
            } default -> {
                MessageManager.sendMessage(p, "invaild_argument");
                }
            }
        } else {
            MessageManager.sendMessage(p, "error_no_permission");
        }
        return false;
    }
}