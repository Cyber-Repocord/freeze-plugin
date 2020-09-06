package me.maxgrzy.freezeplugin.events.freezeListenerVersions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

import static me.maxgrzy.freezeplugin.FreezePluginUtils.frozenPlayers;

public class FreezePluginListener_1_8 implements Listener {
    @EventHandler
    public void playerChatEvent(AsyncPlayerChatEvent event) {
        if (frozenPlayers.contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        if (frozenPlayers.contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerHungerEvent(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (frozenPlayers.contains(player.getUniqueId())) {
                event.setCancelled(true);
            }
        }

    }
    @EventHandler
    public void onPlayerTakeDamageEvent(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (frozenPlayers.contains(player.getUniqueId())) {
                event.setCancelled(true);
            }
        }

    }
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        if (frozenPlayers.contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent event) {
        if (frozenPlayers.contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerCommandPreProcessEvent(PlayerCommandPreprocessEvent event) {
        if (frozenPlayers.contains(event.getPlayer().getUniqueId())) {
            Player player = event.getPlayer();
            if (!(event.getMessage().startsWith("/unfreeze") && player.hasPermission("freezeplugin.unfreeze"))) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You can't use commands while frozen!");
            }
        }
    }
    @EventHandler
    public void onItemDropEvent(PlayerDropItemEvent event) {
        if (frozenPlayers.contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onItemConsumeEvent(PlayerItemConsumeEvent event) {
        if (frozenPlayers.contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerPickupItemEvent(PlayerPickupItemEvent event) {
            Player player = event.getPlayer();
            if (frozenPlayers.contains(player.getUniqueId())) {
                event.setCancelled(true);
                player.updateInventory();
            }
    }
    @EventHandler
    public void onPlayerHealEvent(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (frozenPlayers.contains(player.getUniqueId())) {
                event.setCancelled(true);
                player.updateInventory();
            }
        }
    }
}
