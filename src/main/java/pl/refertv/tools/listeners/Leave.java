package pl.refertv.tools.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.refertv.tools.Tools;


public class Leave implements Listener {

    @EventHandler
    public void onJoin(PlayerQuitEvent e) {
        if (Tools.getSettings().getQuitListener()) {
            e.quitMessage(null);
        }
    }
}
