package pl.refertv.tools.cmds;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import pl.refertv.tools.MessagesManager;
import pl.refertv.tools.Tools;

import java.util.HashMap;
import java.util.Map;

public class GamemodeCommand extends CommandBase {

    private Map<String, GameMode> gm = new HashMap<>();

    public GamemodeCommand() {
        gm.put("0", GameMode.SURVIVAL);
        gm.put("1", GameMode.CREATIVE);
        gm.put("2", GameMode.ADVENTURE);
        gm.put("3", GameMode.SPECTATOR);
        gm.put("survival", GameMode.SURVIVAL);
        gm.put("creative", GameMode.CREATIVE);
        gm.put("adventure", GameMode.ADVENTURE);
        gm.put("spectator", GameMode.SPECTATOR);
    }

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.gamemode")) {
            if (args.length == 0 || args.length > 2) {
                p.sendTitle(MessagesManager.get().getString("title"), MessagesManager.get().getString("arg"), 10, 10, 10);
            } else {
                Player player;
                if (args.length == 1) {
                    if (!(p instanceof Player)) {
                        p.sendMessage(Tools.mbp);
                        return true;
                    }
                    player = ((Player) p);
                } else {
                    player = Bukkit.getPlayer(args[1]);
                    if (player == null) {
                        p.sendTitle(Tools.gmc, "Ten gracz jest offline", 10, 10, 10);
                        return true;
                    }
                }
                if (!gm.containsKey(args[0])) {
                    p.sendTitle(Tools.gmc, Tools.arg, 10, 10, 10);
                    return true;
                }
                GameMode mode = gm.get(args[0]);
                player.setGameMode(mode);

                if (player != p) {
                    p.sendTitle(MessagesManager.get().getString("title"), "Zmieniłeś tryb gry dla §6" + player.getName() + " §fzostał zmieniony na §a" + mode.toString().toLowerCase(), 10, 10, 10);
                }
                player.sendTitle(MessagesManager.get().getString("title"), "Twój tryb gry został zmieniony na §a" + mode.toString().toLowerCase(), 10, 10, 10);
            }
            return true;
        } else {
            p.sendMessage(MessagesManager.get().getString("noperms"));
        }
        return false;
    }
}