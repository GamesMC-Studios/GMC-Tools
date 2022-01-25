package pl.refertv.tools.cmds;

import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import pl.refertv.tools.MessageManager;

import java.util.regex.Pattern;


public class HeadCommand extends CommandBase {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z0-9_]+$");

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {

        if (p.hasPermission("gamesmc.head")) {
            if (args.length == 0) {
                p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("provide_player_name")).toComponent()));
                return true;
            }
            if (args.length == 1) {
                if (!NAME_PATTERN.matcher(args[0]).matches()) {
                    MessageManager.sendMessage(p, "invaild_nickname");
                } else {
                    ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1, (short) SkullType.PLAYER.ordinal());
                    SkullMeta head = (SkullMeta) skull.getItemMeta();

                    head.setOwner(args[0]);
                    head.setDisplayName("§eGłowa " + args[0]);
                    skull.setItemMeta(head);

                    p.getPlayer().getInventory().addItem(skull);
                    p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("get_player_head", args[0])).toComponent()));
                    MessageManager.sendMessage(p, "get_player_head", args[0]);
                }
            }
        } else {
            MessageManager.sendMessage(p, "error_no_permission");
        }
        return false;
    }
}