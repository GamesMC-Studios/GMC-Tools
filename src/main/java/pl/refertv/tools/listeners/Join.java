package pl.refertv.tools.listeners;

import de.themoep.minedown.MineDown;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.refertv.tools.MessageManager;
import pl.refertv.tools.Tools;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (Tools.getSettings().getJoinListener()) {
            final Player p = e.getPlayer();
            if (p.hasPermission("gamesmc.sponsor")) {
                p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("welcome_in_server", p.getName())).toComponent()), 20, 60, 20);
                String joinsponsor = MessageManager.getRawMessage("join_message_sponsor");
                e.joinMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(PlaceholderAPI.setPlaceholders(e.getPlayer(), joinsponsor)));
            } else if (p.hasPermission("gamesmc.rank")) {
                p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("welcome_in_server", p.getName())).toComponent()), 20, 60, 20);
                String join = MessageManager.getRawMessage("join_message_ranks");
                e.joinMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(PlaceholderAPI.setPlaceholders(e.getPlayer(), join)));
            } else {
                e.joinMessage(null);
            }
        }
    }
}