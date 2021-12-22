package pl.refertv.tools.cmds;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.refertv.tools.Tools;

public class RenameCommand extends CommandBase {
    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.rename")) {
            if (args.length == 0)
                p.sendMessage(Tools.getInstance().getCommand("rename").getUsage());
        } if (args.length > 0) {
            ItemStack item = p.getItemInHand();
            ItemMeta itemmeta = item.getItemMeta();
            itemmeta.setDisplayName(args[0].replace("&", "§"));
            item.setItemMeta(itemmeta);
            p.sendTitle(Tools.gmc, "§eZmieniłeś nazwę przedmiotu w ręku", 10, 30, 10);
        }
        return false;
        }
    }