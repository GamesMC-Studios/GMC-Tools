package pl.refertv.tools.cmds;

import org.bukkit.GameMode;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.refertv.tools.Tools;

import java.util.Objects;

public class GamemodeCommand extends CommandBase {

    @Override
    protected void onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.gamemode")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("0")) {
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendTitle(Tools.gmc, "§eZmieniłeś tryb gry na przetrwanie", 10, 30, 10);

                } else if (args[0].equalsIgnoreCase("1")) {
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendTitle(Tools.gmc, "§eZmieniłeś tryb gry na kreatywny", 10, 30, 10);

                } else if (args[0].equalsIgnoreCase("2")) {
                    p.setGameMode(GameMode.ADVENTURE);
                    p.sendTitle(Tools.gmc, "§eZmieniłeś tryb gry na przygodowy", 10, 30, 10);

                } else if (args[0].equalsIgnoreCase("3")) {
                    p.setGameMode(GameMode.SPECTATOR);
                    p.sendTitle(Tools.gmc, "§eZmieniłeś tryb gry na obserwatora", 10, 30, 10);
                }
            }
        }
    }
}


