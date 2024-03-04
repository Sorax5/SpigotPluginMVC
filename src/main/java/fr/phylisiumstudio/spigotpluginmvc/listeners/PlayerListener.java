package fr.phylisiumstudio.spigotpluginmvc.listeners;

import fr.phylisiumstudio.logic.controllers.CraftPlayerController;
import fr.phylisiumstudio.logic.models.CraftPlayer;
import fr.phylisiumstudio.spigotpluginmvc.SpigotPluginMVC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

/**
 * The listener for the player events.
 */
public class PlayerListener implements Listener {
    private final CraftPlayerController playerController;
    private final SpigotPluginMVC plugin;

    /**
     * Constructor of the listener.
     * @param plugin The plugin.
     * @param playerController The player controller.
     */
    public PlayerListener(SpigotPluginMVC plugin, CraftPlayerController playerController) {
        this.plugin = plugin;
        this.playerController = playerController;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        String name = event.getPlayer().getName();

        if(this.playerController.playerExists(uuid)){
            return;
        }

        CraftPlayer player = new CraftPlayer(name, uuid);
        this.playerController.addPlayer(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        this.playerController.removePlayer(uuid);
    }

    @EventHandler
    public void onPlayerKill(EntityDeathEvent event){
        if (event.getEntity().getKiller() == null) {
            return;
        }

        UUID uuid = event.getEntity().getKiller().getUniqueId();

        if(!this.playerController.playerExists(uuid)){
            return;
        }

        this.playerController.gainExperience(uuid, 10);
    }

}
