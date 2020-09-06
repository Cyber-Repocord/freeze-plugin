package me.maxgrzy.freezeplugin.commands;

import me.maxgrzy.freezeplugin.FreezePlugin;
import me.maxgrzy.freezeplugin.FreezePluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FreezeCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!FreezePluginUtils.isSupportedVersion()) {
            sender.sendMessage("This plugin is running on an unsupported server version so this might not work");
        }
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!FreezePluginUtils.freezePlayer(player)) {
                    sender.sendMessage(ChatColor.RED + "You are already frozen!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Specify a target!");
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("@a")) {
                int count = 0;
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (FreezePluginUtils.freezePlayer(player)) {
                        count++;
                    }
                }
                if (count == 1) {
                    sender.sendMessage(ChatColor.AQUA + "" + count + " player got frozen!");
                } else if (count == 0) {
                    sender.sendMessage(ChatColor.RED + "No players affected!");
                } else {
                    sender.sendMessage(ChatColor.AQUA + "" + count + " players got frozen!");
                }
            } else {
                Player player = null;
                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    if (player1.getName().equalsIgnoreCase(args[0])) {
                        player = player1;
                    }
                }

                if (player != null) {
                    if (sender instanceof Player) {
                        Player player1 = (Player) sender;
                        if (player1.getName().equalsIgnoreCase(player.getName())) {
                            if (!FreezePluginUtils.freezePlayer(player)) {
                                sender.sendMessage(ChatColor.RED + "You are already frozen!");
                            }
                        } else {
                            if (FreezePluginUtils.freezePlayer(player)) {
                                sender.sendMessage(ChatColor.AQUA + player.getName() + " got frozen!");
                            } else {
                                sender.sendMessage(ChatColor.RED + player.getName() +  " was already frozen!");
                            }
                        }
                    } else {
                        if (FreezePluginUtils.freezePlayer(player)) {
                            sender.sendMessage(ChatColor.AQUA + player.getName() + " got frozen!");
                        } else {
                            sender.sendMessage(ChatColor.RED + player.getName() +  " was already frozen!");
                        }
                    }

                } else {
                    sender.sendMessage(ChatColor.RED + "No online player like that!");
                }
            }

        } else {
            sender.sendMessage(ChatColor.RED + "You can provide only one argument!");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> possibleStringsList = new ArrayList<String>();
            possibleStringsList.add("@a");
            for (Player player : Bukkit.getOnlinePlayers()) {
                possibleStringsList.add(player.getName());
            }
            List<String> actualList = new ArrayList<String>();
            for (String arg : possibleStringsList) {
                if (arg.toUpperCase().startsWith(args[0].toUpperCase())) {
                    actualList.add(arg);
                }
            }
            return actualList;
        } else {
            return new ArrayList<String>();
        }
    }
}