package com.aep.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * TeachRequestManager acts as the concrete subject in the Observer Design Pattern.
 * It notifies academic professionals about updates to their teach requests.
 */
public class TeachRequestManager implements Subject {
    private List<Observer> observers;

    /**
     * Constructor initializes the observer list.
     */
    public TeachRequestManager() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    /**
     * Updates the teach request status and notifies observers.
     *
     * @param status the updated status
     * @param observer the observer to notify
     */
    public void updateTeachRequestStatus(String status, Observer observer) {
        notifyObservers("Your teach request status is now: " + status);
    }
}

