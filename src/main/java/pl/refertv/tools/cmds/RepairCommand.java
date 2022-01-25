package pl.refertv.tools.cmds;

import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.refertv.tools.MessageManager;
import pl.refertv.tools.Tools;
import pl.refertv.tools.utils.CooldownManager;

public class RepairCommand extends CommandBase {
    private final CooldownManager cooldownManager = new CooldownManager();

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.repair")) {
            if (CooldownManager.checkCooldown(p.getPlayer())) {
                CooldownManager.setCooldown(p.getPlayer(), Tools.getSettings().getGetCooldown());
                ItemStack item = p.getItemInHand();
                item.setDurability((short) 0);
                MessageManager.sendMessage(p, "item_repair");
                p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("item_repair")).toComponent()));
            } else {
                MessageManager.sendMessage(p, "cooldown_message", String.valueOf(CooldownManager.getCooldown(p.getPlayer())));
            }
        } else {
            MessageManager.sendMessage(p, "error_no_permission");
        }
        return true;
    }
}
