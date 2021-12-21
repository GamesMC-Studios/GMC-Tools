package pl.refertv.tools.cmds;

import org.bukkit.GameMode;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GamemodeCommand extends CommandBase {

    @Override
    protected void onCommand(Player s, Command cmd, String label, String[] args) {
        Player p = s.getPlayer();
        p.setGameMode(GameMode.CREATIVE);
        s.sendTitle("GamesMC", "Gamemode został włączony", 10,30,10);
    }
}

