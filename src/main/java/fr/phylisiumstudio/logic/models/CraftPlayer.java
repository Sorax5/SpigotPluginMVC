package fr.phylisiumstudio.logic;

import java.io.Serializable;
import java.util.UUID;

/**
 * Class representing a player in the game
 */
public class CraftPlayer extends Observable implements Serializable {
    private int level;
    private double experience;
    private final String name;
    private final UUID uuid;

    /**
     * Constructor of the CraftPlayer
     * @param name the name of the player
     * @param uuid the UUID of the player
     */
    public CraftPlayer(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    /**
     * Set the level of the player
     * @param level the new level of the player
     */
    public void setLevel(int level) {
        this.level = level;
        this.NotifyPlayerLevelChanged(level);
    }

    /**
     * Set the experience of the player
     * @param experience the new experience of the player
     */
    public void setExperience(double experience) {
        this.experience = experience;
        this.NotifyPlayerExperienceChanged(experience);
    }

    /**
     * Get the name of the player
     * @return the name of the player
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Get the UUID of the player
     * @return the UUID of the player
     */
    public double getExperience() {
        return this.experience;
    }
}
