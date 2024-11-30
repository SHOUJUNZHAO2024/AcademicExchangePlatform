package com.aep.observer;

import java.util.List;

/**
 * Subject interface for the Observer Design Pattern.
 * Subjects implement this interface to manage and notify observers.
 */
public interface Subject {
    /**
     * Registers an observer to the subject.
     *
     * @param observer the observer to add
     */
    void addObserver(Observer observer);

    /**
     * Removes an observer from the subject.
     *
     * @param observer the observer to remove
     */
    void removeObserver(Observer observer);

    /**
     * Notifies all registered observers with a message.
     *
     * @param message the notification message
     */
    void notifyObservers(String message);
}
