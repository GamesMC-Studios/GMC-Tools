package pl.refertv.tools.cmds;

import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.StringUtil;
import pl.refertv.tools.MessageManager;
import pl.refertv.tools.utils.Colors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemCommand extends CommandBase implements TabCompleter {

    final static String[] ARGS = {"setname", "setlore", "reset", "clear"};

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.item")) {
            if (args.length >= 1) {
                if (args.length == 1) {
                    MessageManager.sendMessage(p, "invalid_syntax", cmd.getUsage());
                } else {
                    StringBuilder str = new StringBuilder();
                    for (int i = 1; i < args.length; ++i) {
                        str.append(args[i] + " ");
                    }
                    ItemStack item = p.getItemInHand();
                    ItemMeta itemmeta = item.getItemMeta();
                    String name = Colors.colorHex(str.toString());

                    if (p.getInventory().getItemInMainHand().getType() == Material.AIR) {
                        MessageManager.sendMessage(p, "item_in_hand");
                        return true;
                    }

                    switch (args[0]) {
                        case "setname" -> {
                            itemmeta.setDisplayName(ChatColor.RESET + name);
                            item.setItemMeta(itemmeta);
                            p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("item_name", name)).toComponent()), 20, 60, 20);
                        }
                        case "setlore" -> {
                            itemmeta = item.getItemMeta();
                            List<String> lore = new ArrayList();
                            Collections.addAll(lore, name.split("%nl%"));
                            itemmeta.setLore(lore);
                            item.setItemMeta(itemmeta);
                            p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("item_lore", name)).toComponent()), 20, 60, 20);
                        }
                        case "reset", "clear" -> {
                            itemmeta = item.getItemMeta();
                            itemmeta.setDisplayName(null);
                            itemmeta.setLore(null);
                            item.setItemMeta(itemmeta);
                        }
                        default -> {
                            MessageManager.sendMessage(p, "invalid_syntax", cmd.getUsage());
                        }
                    }
                }
            } else {
                MessageManager.sendMessage(p, "invalid_syntax", cmd.getUsage());
            }
        }
        return true;
    }

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