package com.aep.observer;

/**
 * Observer interface for the Observer Design Pattern.
 * Observers implement this interface to receive updates from a subject.
 */
public interface Observer {
    /**
     * Updates the observer with a message.
     *
     * @param message the update message
     */
    void update(String message);
}
