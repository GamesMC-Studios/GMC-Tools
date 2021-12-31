package pl.refertv.tools.cmds;

import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.refertv.tools.MessageManager;
import pl.refertv.tools.Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemCommand extends CommandBase {

    private static final Tools plugin = Tools.getInstance();

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
                        String name = str.toString().replace('&', '§');

                        if (p.getInventory().getItemInMainHand().getType() == Material.AIR) {
                            MessageManager.sendMessage(p, "item_in_hand");
                            return true;
                        }

                        switch(args[0]) {
                            case "setname" -> {
                            itemmeta.setDisplayName(name);
                            item.setItemMeta(itemmeta);
                            p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("item_name", name)).toComponent()), 20, 60, 20);
                        } case "setlore" -> {
                            itemmeta = item.getItemMeta();
                            List<String> lore = new ArrayList();
                            Collections.addAll(lore, name.split("%nl%"));
                            itemmeta.setLore(lore);
                            item.setItemMeta(itemmeta);
                            p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("item_lore", name)).toComponent()), 20, 60, 20);
                        } case "reset", "clear" -> {
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
    }