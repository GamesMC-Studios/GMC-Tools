package pl.refertv.tools.cmds;

import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import pl.refertv.tools.MessageManager;

import java.io.*;
import java.util.Collection;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class SlotsCommand extends CommandBase {

    private static final Pattern SLOTS_PATTERN = Pattern.compile("[1-9][0-9]*");

    @Override
    public boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("gamesmc.slots")) {
            if (args.length == 1) {
                if (!SLOTS_PATTERN.matcher(args[0]).matches()) {
                    MessageManager.sendMessage(p, "argument_must_be_int");
                } try {
                        Bukkit.getServer().setMaxPlayers(Integer.valueOf(args[0]));
                        MessageManager.sendMessage(p, "set_max_players", String.valueOf(Bukkit.getServer().getMaxPlayers()).toString());
                        Properties properties = new Properties();
                        BufferedReader bReader = new BufferedReader(new FileReader("server.properties"));
                        properties.load(bReader);
                        bReader.close();
                        properties.setProperty("max-players", Integer.valueOf(args[0]).toString());
                        BufferedWriter bWriter = new BufferedWriter(new FileWriter("server.properties"));
                        properties.store(bWriter, "");
                        bWriter.close();
                        p.sendTitle(TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("title")).toComponent()), TextComponent.toLegacyText(new MineDown(MessageManager.getRawMessage("set_max_players", String.valueOf(Bukkit.getServer().getMaxPlayers()).toString())).toComponent()));
                    } catch (IOException IOE) {
                        Logger.getLogger("Can't update server.properties!");
                    }
                } else {
                    MessageManager.sendMessage(p, "invaild_argument");
                }
            } else {
                MessageManager.sendMessage(p, "error_no_permission");
            }
            return false;
        }
    }
