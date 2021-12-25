package pl.refertv.tools.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.refertv.tools.Tools;


public class Join implements Listener {

    @EventHandler(
            priority = EventPriority.HIGHEST,
            ignoreCancelled = true
    )
    public void onJoin(PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (p.hasPermission("gamesmc.rank")) {
            p.sendTitle(Tools.gmc, "§fWitaj na serwerze §6" + p.getName());
            String j = "&8* %vault_ranks% &6%player_name% &fdołączył do gry!";
            j = PlaceholderAPI.setPlaceholders(e.getPlayer(), j);
            e.joinMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(j));
        }
    }
}