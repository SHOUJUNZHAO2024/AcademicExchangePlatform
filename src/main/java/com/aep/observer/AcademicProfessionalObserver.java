package com.aep.observer;

/**
 * AcademicProfessionalObserver acts as the concrete observer in the Observer Design Pattern.
 * It receives notifications from the subject about updates to teach requests.
 */
public class AcademicProfessionalObserver implements Observer {
    private String name;

    /**
     * Constructor initializes the observer with a name.
     *
     * @param name the name of the academic professional
     */
    public AcademicProfessionalObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("Notification for " + name + ": " + message);
    }
}

