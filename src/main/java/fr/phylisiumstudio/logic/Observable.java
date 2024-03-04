package fr.phylisiumstudio.logic;

import fr.phylisiumstudio.logic.models.CraftPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for the observer pattern
 */
public abstract class Observable {

    private transient List<IObserver> observers;

    public Observable() {
        this.observers = new ArrayList<>();
    }

    /**
     * Add an observer to the list of observers
     * @param observer the observer to add
     */
    public void addObserver(IObserver observer){
        if (observers == null){
            this.observers = new ArrayList<>();
        }
        this.observers.add(observer);
    }

    /**
     * Remove an observer from the list of observers
     * @param observer the observer to remove
     */
    public void removeObserver(IObserver observer){
        if (observers == null){
            this.observers = new ArrayList<>();
        }
        this.observers.remove(observer);
    }

    /**
     * Notify all the observers that the player level has changed
     * @param level the new level of the player
     */
    protected void NotifyPlayerLevelChanged(CraftPlayer player, int level) {
        for (IObserver observer : this.observers) {
            observer.playerLevelChange(player, level);
        }
    }

    /**
     * Notify all the observers that the player experience has changed
     * @param experience the new experience of the player
     */
    protected void NotifyPlayerExperienceChanged(CraftPlayer player, double experience) {
        for (IObserver observer : this.observers) {
            observer.playerExperienceChange(player, experience);
        }
    }
}
