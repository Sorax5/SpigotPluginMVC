package fr.phylisiumstudio.spigotpluginmvc.listeners;

import fr.mrmicky.fastboard.FastBoard;
import fr.phylisiumstudio.logic.IObserver;
import fr.phylisiumstudio.logic.controllers.CraftPlayerController;
import fr.phylisiumstudio.logic.models.CraftPlayer;
import fr.phylisiumstudio.spigotpluginmvc.SpigotPluginMVC;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

/**
 * The listener for the scoreboard.
 */
public class ScoreBoardListener implements Listener, IObserver {

    private final CraftPlayerController playerController;
    private final SpigotPluginMVC plugin;

    private HashMap<UUID, FastBoard> scoreboards;
    private HashMap<UUID, BossBar> bossBars;

    /**
     * Constructor of the listener.
     * @param plugin The plugin.
     * @param playerController The player controller.
     */
    public ScoreBoardListener(SpigotPluginMVC plugin, CraftPlayerController playerController) {
        this.plugin = plugin;
        this.playerController = playerController;
        playerController.addObserver(this);

        this.bossBars = new HashMap<>();
        this.scoreboards = new HashMap<>();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        CraftPlayer player = this.playerController.getPlayer(event.getPlayer().getUniqueId());
        if (player != null) {
            FastBoard board = createScoreBoard(player);
            this.scoreboards.put(player.getUuid(), board);

            BossBar bossBar = createBossBar(player);
            this.bossBars.put(player.getUuid(), bossBar);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        FastBoard board = this.scoreboards.get(uuid);
        if (board != null) {
            board.delete();
        }
        this.scoreboards.remove(uuid);

        BossBar bossBar = this.bossBars.get(uuid);
        if (bossBar != null) {
            bossBar.removeAll();
        }
        this.bossBars.remove(uuid);
    }


    @Override
    public void playerLevelChange(CraftPlayer player, int level) {
        updateScoreBoard(player);
        updateBossBar(player);

        Player p = Bukkit.getPlayer(player.getUuid());
        if(p == null) {
            return;
        }

        p.sendMessage("§6You are now level " + level + "!");
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        p.sendTitle("§6Level " + level, "§eCongratulations!", 10, 40, 10);
    }

    @Override
    public void playerExperienceChange(CraftPlayer player, double experience) {
        updateScoreBoard(player);
        updateBossBar(player);
    }

    private FastBoard createScoreBoard(CraftPlayer player) {
        Player p = Bukkit.getPlayer(player.getUuid());
        FastBoard board = new FastBoard(p);
        board.updateTitle("§6§lPhylisiumStudio");
        board.updateLines(
                "§7§m----------------",
                "§6Level: §e" + player.getLevel(),
                "§7§m----------------"
        );
        return board;
    }

    private void updateScoreBoard(CraftPlayer player) {
        FastBoard board = this.scoreboards.get(player.getUuid());
        if (board != null) {
            board.updateLines(
                    "§7§m----------------",
                    "§6Level: §e" + player.getLevel(),
                    "§7§m----------------"
            );
        }
    }

    private BossBar createBossBar(CraftPlayer player) {
        Player p = Bukkit.getPlayer(player.getUuid());
        Double progress = player.getExperience() / player.getExperienceToLevelUp();
        String title = "Level " + player.getLevel() + " - " + player.getExperience() + " / " + player.getExperienceToLevelUp();
        BossBar bossBar = Bukkit.createBossBar(title, BarColor.GREEN, BarStyle.SEGMENTED_12);
        bossBar.setProgress(progress);
        bossBar.addPlayer(p);
        return bossBar;
    }

    private void updateBossBar(CraftPlayer player) {
        BossBar bossBar = this.bossBars.get(player.getUuid());
        if (bossBar != null) {
            Double progress = player.getExperience() / player.getExperienceToLevelUp();
            String title = "Level " + player.getLevel() + " - " + player.getExperience() + " / " + player.getExperienceToLevelUp();
            bossBar.setTitle(title);
            bossBar.setProgress(progress);
        }
    }
}
