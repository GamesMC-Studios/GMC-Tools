package pl.refertv.tools.Config;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.refertv.tools.Tools;

import java.util.List;

public class Messages {

    final Tools main;

    final String
            MSG_GAMESMCTOOLS_TITLE,
            MSG_GAMESMCTOOLS_ARG;

    Messages(Tools main) {
        this.main=main;

        MSG_GAMESMCTOOLS_TITLE = getMsg("title","GamesMC");
        MSG_GAMESMCTOOLS_ARG = getMsg("arg", "niepoprawny arg")

        main.getConfig().addDefault("blacklist-title","----- &cBlacklist&r -----");


    }

    String getMsgs(String prefix, String path, String defaultMessage) {
        final int maxLength = 30;
        //List<String> msgs = main.getConfig().getStringList(prefix+"-"+path);
        //if(msgs==null && msgs.size()!=0) return translateAlternateColorCodes(msgs.toArray(new String[0]));
        String msg = main.getConfig().getString(prefix+"-"+path);
        if(msg==null) {
            return WordUtils.wrap(ChatColor.translateAlternateColorCodes('&',defaultMessage),maxLength);
        }
        msg = WordUtils.wrap(ChatColor.translateAlternateColorCodes('&',msg),maxLength);
        return msg;

    }

    String getMsg(String path, String defaultMessage) {
        return ChatColor.translateAlternateColorCodes('&',main.getConfig().getString("message-"+path,defaultMessage));
    }
    String getMsg(String prefix, String path, String defaultMessage) {
        return ChatColor.translateAlternateColorCodes('&',main.getConfig().getString(prefix+"-"+path,defaultMessage));
    }

    static String[] translateAlternateColorCodes(String[] strings) {
        for(int i=0;i<strings.length;i++) {
            strings[i]=ChatColor.translateAlternateColorCodes('&',strings[i]);
        }
        return strings;
    }

    public static void sendMessage(CommandSender player, String message) {
        if(message == null || message.equals("")) {
            return;
        }
        player.sendMessage(message);
    }

}