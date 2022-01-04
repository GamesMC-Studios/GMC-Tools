package pl.refertv.tools.cmds;

import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import pl.refertv.tools.MessageManager;

public class HatCommand extends CommandBase implements Listener {

    public boolean onCommand(Player p, Command command, String alias, String[] args) {
        PlayerInventory inv = p.getInventory();
        ItemStack helm = inv.getHelmet();
        ItemStack held = inv.getItemInMainHand();
            if (this.checkValidHat(p, held)) {
                inv.setItemInMainHand(helm);
                inv.setHelmet(held);
                p.updateInventory();
                p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("hat_was_set", p.getName())).toComponent()), 20, 60, 20);
            }
        return true;
        }

    private boolean checkValidHat(Player p, ItemStack held) {
        if (!p.hasPermission("gamesmc.hat." + held.getType().name()) && (p.isPermissionSet("gamesmc.hat." + held.getType().name()) || (!p.hasPermission("gamesmc.hat.blocks") || !held.getType().isBlock()) && (!p.hasPermission("gamesmc.hat.items") || held.getType().isBlock()))) {
            MessageManager.sendMessage(p, "error_no_permission");
        } else {
            if (held.getAmount() == 1 || held.getType() == Material.AIR) {
                p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("cannot_be_air")).toComponent()), 20, 60, 20);
                return true;
            }
            p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("cannot_be_more_than_one")).toComponent()), 20, 60, 20);
        }
        return false;
    }

    // Listener
    @EventHandler
    public void onClickInHelmetSlot(InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.CRAFTING && event.getRawSlot() == 5 && event.getWhoClicked().getItemOnCursor().getType() != Material.AIR && event.getWhoClicked().getItemOnCursor().getType().getEquipmentSlot() != EquipmentSlot.HEAD) {
            Player p = (Player)event.getWhoClicked();
            ItemStack cursorItem = p.getItemOnCursor();
            ItemStack hatItem = p.getInventory().getHelmet();
            if (this.checkValidHat(p, cursorItem)) {
                p.setItemOnCursor((ItemStack)null);
                p.getInventory().setHelmet((ItemStack)null);
            }
        }

    }
}