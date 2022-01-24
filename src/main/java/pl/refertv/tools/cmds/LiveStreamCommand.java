package pl.refertv.tools.cmds;

import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.Config.Settings;
import pl.refertv.tools.MessageManager;
import pl.refertv.tools.Tools;
import pl.refertv.tools.utils.CenterMessages;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.bukkit.Bukkit.getOnlinePlayers;

public class LiveStreamCommand extends CommandBase {


    private boolean message;

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
            if (p.hasPermission("gamesmc.livestream")) {
                if (args.length == 0) {
                    MessageManager.sendMessage(p, "invalid_syntax", cmd.getUsage());
                } else if (Tools.getSettings().getLiveLinks().stream().anyMatch(s -> args[0].contains(s))) {
                    getOnlinePlayers().forEach(player -> player.spigot().sendMessage(new MineDown(CenterMessages.generate(MessageManager.getRawMessage("live", player.getName(), args[0]))).toComponent()));
                    p.spigot().sendMessage(new MineDown("&aPomyślnie wysłałeś wiadomość o transmisji live.").toComponent());
                    p.sendTitle(TextComponent.toLegacyText(new MineDown("&a").toComponent()), TextComponent.toLegacyText(new MineDown("&aWiadomość została wysłana").toComponent()));
                } else {
                    p.spigot().sendMessage(new MineDown("&cParametr musi być linkiem.").toComponent());
                }
            } else {
                MessageManager.sendMessage(p, "error_no_permission");
            }
        return true;
    }
}
