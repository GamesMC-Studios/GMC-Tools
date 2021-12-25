package pl.refertv.tools.cmds;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import pl.refertv.tools.Tools;

public class HeadCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.head")) {
            if (args.length == 0) {
                p.sendMessage("§7Musisz podać nazwę gracza.");
                p.sendTitle("§f", "§fMusisz podać nazwę gracza", 10, 30, 10);
                return true;

            } else if (args.length == 1) {
                ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1, (short) SkullType.PLAYER.ordinal());
                SkullMeta head = (SkullMeta) skull.getItemMeta();

                head.setOwner(args[0]);
                head.setDisplayName("§eGłowa " + args[0]);
                skull.setItemMeta(head);

                p.getPlayer().getInventory().addItem(skull);
                p.getPlayer().sendTitle(Tools.gmc, "§fOtrzymałeś głowę gracza §e" + args[0], 10, 30, 10);
            }
            return true;
        } else {
            p.sendMessage(Tools.noperms);
        }
        return false;
    }
}