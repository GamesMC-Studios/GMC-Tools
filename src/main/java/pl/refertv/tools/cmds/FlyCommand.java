package pl.refertv.tools.cmds;

import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.MessageManager;

public class FlyCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.fly")) {
            if (args.length > 1) {
                p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("invaild_argument")).toComponent()), 20, 60, 20);
            }
            if (args.length == 0) {
                if (p.getAllowFlight() == true) {
                    p.setAllowFlight(false);
                    p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("fly", MessageManager.getRawMessage("disable"))).toComponent()), 20, 60, 20);
                } else {
                        p.setAllowFlight(true);
                        p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("fly", MessageManager.getRawMessage("enable"))).toComponent()), 20, 60, 20);
                }
            }
            if (p.hasPermission("gamesmc.fly.others")) {
                if (args.length == 1) {
                    Player gracz = Bukkit.getPlayer(args[0]);
                    if (gracz == null) {
                        MessageManager.sendMessage(p, "player_offline");
                        return false;
                    }
                    if (gracz.getAllowFlight() == true) {
                        gracz.setAllowFlight(false);
                        p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("fly_change_by", p.getName())).toComponent()), 20, 60, 20);
                        p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("player_change_fly", MessageManager.getRawMessage("disable"), p.getName())).toComponent()), 20, 60, 20);
                    } else {
                            gracz.setAllowFlight(true);
                            gracz.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("fly_change_by", p.getName())).toComponent()), 20, 60, 20);
                            p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("player_change_fly", MessageManager.getRawMessage("enable"), p.getName())).toComponent()), 20, 60, 20);
                    }
                }
            } else {
                MessageManager.sendMessage(p, "error_no_permission");
            }
        } else {
            MessageManager.sendMessage(p, "error_no_permission");
        }
        return false;
    }
}