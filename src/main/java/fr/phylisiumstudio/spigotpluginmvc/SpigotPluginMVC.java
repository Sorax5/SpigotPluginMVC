package fr.phylisiumstudio.spigotpluginmvc;

import fr.phylisiumstudio.logic.ICraftPlayerService;
import fr.phylisiumstudio.logic.controllers.CraftPlayerController;
import fr.phylisiumstudio.spigotpluginmvc.listeners.PlayerListener;
import fr.phylisiumstudio.spigotpluginmvc.listeners.ScoreBoardListener;
import fr.phylisiumstudio.storage.JsonCraftPlayerService;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * The main class of the plugin.
 */
public final class SpigotPluginMVC extends JavaPlugin {
    private static SpigotPluginMVC instance;

    private CraftPlayerController playerController;

    private PlayerListener playerListener;
    private ScoreBoardListener scoreBoardListener;
    private ICraftPlayerService craftPlayerService;

    @Override
    public void onEnable() {
        instance = this;

        File config = new File(this.getDataFolder().getAbsolutePath());
        if (!config.exists()) {
            config.mkdir();
        }

        this.craftPlayerService = new JsonCraftPlayerService(config.getAbsolutePath() + "/craftPlayers/");
        this.playerController = new CraftPlayerController(craftPlayerService);
        this.playerListener = new PlayerListener(this, this.playerController);
        this.scoreBoardListener = new ScoreBoardListener(this, this.playerController);

        getServer().getPluginManager().registerEvents(this.playerListener, this);
        getServer().getPluginManager().registerEvents(this.scoreBoardListener, this);
    }

    @Override
    public void onDisable() {
        this.playerController.SaveAllPlayers();
    }

    /**
     * Get the instance of the plugin.
     * @return The instance of the plugin.
     */
    public static SpigotPluginMVC getInstance() {
        return instance;
    }
}
