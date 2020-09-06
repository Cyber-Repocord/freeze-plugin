package me.maxgrzy.freezeplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class FreezePluginUtils {
    public static ArrayList<UUID> frozenPlayers = new ArrayList<UUID>();
    public static boolean isSupportedVersion() {
        switch (getServerVersion()) {
            case "8":
            case "9":
            case "10":
            case "11":
            case "12":
            case "13":
            case "14":
            case "15":
            case "16":
                return true;
            default:
                return false;
        }
    }
    public static String getServerVersion() {
        String version = Bukkit.getBukkitVersion();
        if (version.length() == 17) {
            version = version.substring(2, 3);
        } else if (version.length() == 18) {
            version = version.substring(2, 4);
        } else if (version.length() == 19) {
            version = version.substring(2, 3);
        } else if (version.length() == 20) {
            version = version.substring(2, 4);
        } else if (version.length() == 12) {
            version = version.substring(2, 3);
        } else if (version.length() == 13) {
            version = version.substring(2, 4);
        } else if (version.length() == 7) {
            version = version.substring(2, 3);
        } else if (version.length() == 8) {
            version = version.substring(2, 4);
        }
        return version;
    }
    public static boolean freezePlayer(Player player) {
        if (!frozenPlayers.contains(player.getUniqueId())) {
            frozenPlayers.add(player.getUniqueId());
            player.sendMessage(ChatColor.AQUA + "You got frozen!");
            return true;
        } else {
            return false;
        }
    }
    public static boolean unFreezePlayer(Player player) {
        if (frozenPlayers.contains(player.getUniqueId())) {
            frozenPlayers.remove(player.getUniqueId());
            player.sendMessage(ChatColor.GREEN + "You got unfrozen!");
            return true;
        } else {
            return false;
        }
    }
}
