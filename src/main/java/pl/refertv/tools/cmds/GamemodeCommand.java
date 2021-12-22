package pl.refertv.tools.cmds;

import org.bukkit.GameMode;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import pl.refertv.tools.Tools;

public class GamemodeCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.gamemode")) {
            if (args.length == 0)
                p.sendTitle(Tools.gmc, "§eDodaj argument do polecenia.", 10, 30, 10);
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("0") || (args[0].equalsIgnoreCase("survival"))) {
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendTitle(Tools.gmc, "§eZmieniłeś tryb gry na przetrwanie", 10, 30, 10);

                } else if (args[0].equalsIgnoreCase("1") || (args[0].equalsIgnoreCase("creative"))) {
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendTitle(Tools.gmc, "§eZmieniłeś tryb gry na kreatywny", 10, 30, 10);

                } else if (args[0].equalsIgnoreCase("2") || (args[0].equalsIgnoreCase("adventure"))) {
                    p.setGameMode(GameMode.ADVENTURE);
                    p.sendTitle(Tools.gmc, "§eZmieniłeś tryb gry na przygodowy", 10, 30, 10);

                } else if (args[0].equalsIgnoreCase("3") || (args[0].equalsIgnoreCase("spectator"))) {
                    p.setGameMode(GameMode.SPECTATOR);
                    p.sendTitle(Tools.gmc, "§eZmieniłeś tryb gry na obserwatora", 10, 30, 10);
                }
            }
        }
        return false;
    }
}


