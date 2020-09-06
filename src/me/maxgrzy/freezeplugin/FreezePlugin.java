package me.maxgrzy.freezeplugin;


import me.maxgrzy.freezeplugin.commands.FreezeCommand;
import me.maxgrzy.freezeplugin.commands.UnFreezeCommand;
import me.maxgrzy.freezeplugin.events.freezeListenerVersions.FreezePluginListener_1_8;
import me.maxgrzy.freezeplugin.events.freezeListenerVersions.FreezePluginListener_1_15;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class FreezePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        if (!FreezePluginUtils.isSupportedVersion()) {
            getLogger().warning("Plugin Might Not Work Properly: Unknown Server Version (Maybe Outdated Plugin or Unsupported Server Version?).\n Contact developers if you can't fix this error. Your Server Version: " + Bukkit.getBukkitVersion());
        } else {
            getLogger().info("Running Plugin on Supported Server Version!");
        }
        registerEvents();
        registerCommands();
    }
    private void registerEvents() {
        switch (FreezePluginUtils.getServerVersion()) {
            case "8":
            case "9":
            case "10":
            case "11":
            case "12":
            case "13":
            case "14":
                getServer().getPluginManager().registerEvents(new FreezePluginListener_1_8(), this);
                break;
            case "15":
            case "16":
                getServer().getPluginManager().registerEvents(new FreezePluginListener_1_15(), this);
            default:
                getServer().getPluginManager().registerEvents(new FreezePluginListener_1_8(), this);
                break;
        }
    }
    public void registerCommands() {
        getCommand("freeze").setExecutor(new FreezeCommand());
        getCommand("freeze").setTabCompleter(new FreezeCommand());
        getCommand("unfreeze").setExecutor(new UnFreezeCommand());
        getCommand("unfreeze").setTabCompleter(new UnFreezeCommand());
    }
}
