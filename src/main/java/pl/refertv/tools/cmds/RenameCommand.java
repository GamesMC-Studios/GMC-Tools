package pl.refertv.tools.cmds;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.refertv.tools.Tools;

import java.util.Collections;

public class RenameCommand extends CommandBase {
    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.rename")) {
            if (args.length == 0)
                p.sendTitle(Tools.gmc, "Wpisz argument z nazwą przedmiotu", 10, 10, 10);
            if (args.length > 0) {
                try {
                    ItemStack item = p.getItemInHand();
                    ItemMeta itemmeta = item.getItemMeta();
                    itemmeta.setDisplayName(args[0].replace("&", "§").replace("-", " "));
                    item.setItemMeta(itemmeta);
                    p.sendTitle(Tools.gmc, "§eZmieniłeś nazwę przedmiotu w ręku", 10, 30, 10);

                    if (p.getInventory().getItemInMainHand().getType() == Material.AIR)
                        p.sendTitle(Tools.gmc, "§fMusisz trzymać jakiś przedmiot w ręku", 10, 30, 10);
                    return true;
                } catch (Exception e) {
                    p.sendTitle(Tools.gmc, "§cNiepoprawne użycie tej komendy", 10, 30, 10);

                }

                return true;
            }
        }
        p.sendMessage(Tools.noperms);
        return false;
    }
}