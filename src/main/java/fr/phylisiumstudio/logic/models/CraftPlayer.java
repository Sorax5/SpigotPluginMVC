package fr.phylisiumstudio.logic.models;

import fr.phylisiumstudio.logic.Observable;

import java.io.Serializable;
import java.util.UUID;

/**
 * Class representing a player in the game
 */
public class CraftPlayer extends Observable implements Serializable {
    private int level;
    private double experience;
    private double experienceToLevelUp;
    private final String name;
    private final UUID uuid;

    /**
     * Constructor of the CraftPlayer
     * @param name the name of the player
     * @param uuid the UUID of the player
     */
    public CraftPlayer(String name, UUID uuid) {
        super();

        this.name = name;
        this.uuid = uuid;
        this.level = 1;
        this.experience = 0;
        this.experienceToLevelUp = 100;
    }

    /**
     * Set the level of the player
     * @param level the new level of the player
     */
    public void setLevel(int level) {
        this.level = level;
        this.NotifyPlayerLevelChanged(this, level);
    }

    /**
     * Set the experience of the player
     * @param experience the new experience of the player
     */
    public void setExperience(double experience) {
        this.experience = experience;
        this.NotifyPlayerExperienceChanged(this, experience);
    }

    /**
     * Set the experience to level up of the player
     * @param experienceToLevelUp the new experience to level up of the player
     */
    public void setExperienceToLevelUp(double experienceToLevelUp) {
        this.experienceToLevelUp = experienceToLevelUp;
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

    /**
     * Get the uuid of the player
     * @return the uuid of the player
     */
    public UUID getUuid() {
        return this.uuid;
    }

    /**
     * Get Experience to level up of the player
     * @return the experience to level up of the player
     */
    public double getExperienceToLevelUp() {
        return this.experienceToLevelUp;
    }
}
