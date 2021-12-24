package pl.refertv.tools.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.refertv.tools.Tools;


public class Join implements Listener {

    public String joinText = "&8* %luckperms_suffix_element_highest_on_track_serwerowe% &6%player_name% &fdołączył do gry!";

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        p.sendTitle(Tools.gmc, "§fWitaj na serwerze §6" + p.getName(), 10, 50, 10);
        joinText = PlaceholderAPI.setPlaceholders(event.getPlayer(), joinText);
        event.joinMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(joinText));

    }
}
