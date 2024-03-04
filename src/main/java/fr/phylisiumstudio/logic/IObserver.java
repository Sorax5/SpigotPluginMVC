package fr.phylisiumstudio.logic;

import fr.phylisiumstudio.logic.models.CraftPlayer;

/**
 * Interface for the observer pattern
 */
public interface IObserver {
    /**
     * Called when the player level changes
     * @param level the new level of the player
     * @param player the player that changed level
     */
    void playerLevelChange(CraftPlayer player, int level);

    /**
     * Called when the player experience changes
     * @param experience the new experience of the player
     * @param player the player that changed experience
     */
    void playerExperienceChange(CraftPlayer player, double experience);
}
