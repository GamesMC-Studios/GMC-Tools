package pl.refertv.tools.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CooldownManager {

    public static HashMap<UUID, Double> cooldowns;

    public static void setupCooldown() {
        cooldowns = new HashMap<>();
    }

    //setCooldown
    public static void setCooldown(Player player, int seconds) {
        double delay = System.currentTimeMillis() + (seconds * 1000);
        cooldowns.put(player.getUniqueId(), delay);
    }

    //getCooldown
    public static int getCooldown(Player player) {
        return Math.toIntExact(Math.round((cooldowns.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000));
    }

    //checkCooldown
    public static boolean checkCooldown(Player player) {
        if (!cooldowns.containsKey(player.getUniqueId()) || cooldowns.get(player.getUniqueId()) <= System.currentTimeMillis()) {
            return true;
        }
        return false;
    }


}