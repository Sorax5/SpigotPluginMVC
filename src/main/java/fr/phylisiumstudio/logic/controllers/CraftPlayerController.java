package fr.phylisiumstudio.logic.controllers;

import fr.phylisiumstudio.logic.ICraftPlayerService;
import fr.phylisiumstudio.logic.IObserver;
import fr.phylisiumstudio.logic.Observable;
import fr.phylisiumstudio.logic.models.CraftPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The controller of the players.
 */
public class CraftPlayerController extends Observable implements IObserver {
    private List<CraftPlayer> players;
    private ICraftPlayerService craftPlayerService;

    /**
     * The constructor of the class.
     */
    public CraftPlayerController(ICraftPlayerService craftPlayerService) {
        super();
        this.craftPlayerService = craftPlayerService;
        this.players = new ArrayList<>(this.craftPlayerService.list());

        for (CraftPlayer player : this.players) {
            player.addObserver(this);
        }
    }

    /**
     * Add a player to the controller.
     * @param player The player to add.
     */
    public void addPlayer(CraftPlayer player) {
        this.players.add(player);
        craftPlayerService.create(player);
        player.addObserver(this);
    }

    /**
     * Remove a player from the controller.
     * @param uuid The UUID of the player to remove.
     * @throws IllegalArgumentException If the player does not exist.
     */
    public void removePlayer(UUID uuid) {
        if (!playerExists(uuid)) {
            throw new IllegalArgumentException("The player with the UUID " + uuid + " does not exist.");
        }

        CraftPlayer player = getPlayer(uuid);
        player.removeObserver(this);

        this.players.remove(player);
        craftPlayerService.create(player);
    }

    /**
     * Get the list of players.
     * @param uuid The UUID of the player to get.
     * @param experience The experience to gain.
     * @throws IllegalArgumentException If the player does not exist.
     */
    public void gainExperience(UUID uuid, int experience) {
        CraftPlayer player = getPlayer(uuid);
        if(player == null) {
            throw new IllegalArgumentException("The player with the UUID " + uuid + " does not exist.");
        }

        player.setExperience(player.getExperience() + experience);
    }

    /**
     * Level up a player.
     * @param uuid The UUID of the player to level up.
     * @throws IllegalArgumentException If the player does not exist.
     */
    public void levelUpPlayer(UUID uuid) {
        CraftPlayer player = getPlayer(uuid);
        if(player == null) {
            throw new IllegalArgumentException("The player with the UUID " + uuid + " does not exist.");
        }

        double remainingExperience = player.getExperience() - player.getExperienceToLevelUp();
        player.setExperience(remainingExperience);

        player.setLevel(player.getLevel() + 1);
        player.setExperienceToLevelUp(player.getExperienceToLevelUp() + (player.getExperienceToLevelUp())*0.1);
    }

    /**
     * Get the player with the UUID.
     * @param uuid The UUID of the player to get.
     * @return The player with the UUID.
     */
    public CraftPlayer getPlayer(UUID uuid) {
        return this.players.stream().filter(player -> player.getUuid().equals(uuid)).findFirst().orElse(null);
    }

    /**
     * Check if a player exists.
     * @param uuid The UUID of the player to check.
     * @return True if the player exists, false otherwise.
     */
    public boolean playerExists(UUID uuid) {
        return this.players.stream().anyMatch(player -> player.getUuid().equals(uuid));
    }

    public void SaveAllPlayers() {
        for (CraftPlayer player : players) {
            craftPlayerService.create(player);
        }
    }

    @Override
    public void playerLevelChange(CraftPlayer player, int level) {
        NotifyPlayerLevelChanged(player, level);
    }

    @Override
    public void playerExperienceChange(CraftPlayer player, double experience) {
        if (player.getExperience() >= player.getExperienceToLevelUp()) {
            levelUpPlayer(player.getUuid());
        }

        NotifyPlayerExperienceChanged(player, experience);
    }
}
