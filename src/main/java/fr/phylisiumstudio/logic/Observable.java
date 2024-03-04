package fr.phylisiumstudio.logic;

/**
 * Interface for the observer pattern
 */
public interface IObservable {
    /**
     * Add an observer to the list of observers
     * @param observer the observer to add
     */
    void addObserver(IObserver observer);

    /**
     * Remove an observer from the list of observers
     * @param observer the observer to remove
     */
    void removeObserver(IObserver observer);

    /**
     * Notify all the observers that the player level has changed
     * @param level the new level of the player
     */
    void NotifyPlayerLevelChanged(int level);
}
