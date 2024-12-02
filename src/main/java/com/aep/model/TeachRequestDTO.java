/**
 * File name: TeachRequestDTO.java
 * Author: Group10
 * Course: CST8288-section 031
 * Term: Fall 2024
 * Assignment: Final Project
 * Date: November 30
 * Purpose: TeachRequestDTO represents a request to teach a course by an academic professional.
 */
package com.aep.model;

/**
 * TeachRequestDTO represents a request to teach a course by an academic professional.
 * It serves as a Data Transfer Object for the TeachRequest table.
 */
public class TeachRequestDTO {

    /**
     * The unique ID of the teaching request.
     */
    private int requestId;

    /**
     * The unique ID of the course associated with the teaching request.
     */
    private int courseId;

    /**
     * The unique ID of the academic professional making the request.
     */
    private int professionalId;

    /**
     * The status of the teaching request (e.g., Pending, Approved, Rejected).
     */
    private String status;

    /**
     * Indicates whether a notification is enabled for the teaching request.
     */
    private boolean notification;

    /**
     * The code of the course associated with the teaching request.
     */
    private String courseCode;

    /**
     * The term during which the course is offered (e.g., Fall, Winter, Spring).
     */
    private String term;

    /**
     * The name of the institution associated with the teaching request.
     */
    private String institutionName;

    /**
     * The name of the academic professional making the request.
     */
    private String professionalName;

    /**
     * The title of the course associated with the teaching request.
     */
    private String courseTitle;

    /**
     * Gets the request ID.
     * @return the request ID
     */
    public int getRequestId() {
        return requestId;
    }

    /**
     * Sets the request ID.
     * @param requestId the request ID to set
     */
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    /**
     * Gets the course ID.
     * @return the course ID
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * Sets the course ID.
     * @param courseId the course ID to set
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    /**
     * Gets the professional ID of the academic professional.
     * @return the professional ID
     */
    public int getProfessionalId() {
        return professionalId;
    }

    /**
     * Sets the professional ID of the academic professional.
     * @param professionalId the professional ID to set
     */
    public void setProfessionalId(int professionalId) {
        this.professionalId = professionalId;
    }

    /**
     * Gets the status of the request (e.g., Pending, Approved, Rejected).
     * @return the status of the request
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the request.
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Checks if the request has a notification enabled.
     * @return true if notification is enabled, false otherwise
     */
    public boolean isNotification() {
        return notification;
    }

    /**
     * Sets the notification status of the request.
     * @param notification true to enable notification, false to disable
     */
    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    /**
     * Gets the course code associated with the request.
     * @return the course code
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Sets the course code associated with the request.
     * @param courseCode the course code to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Gets the term in which the course is offered.
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * Sets the term in which the course is offered.
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * Gets the name of the institution associated with the request.
     * @return the institution name
     */
    public String getInstitutionName() {
        return institutionName;
    }

    /**
     * Sets the name of the institution associated with the request.
     * @param institutionName the institution name to set
     */
    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    /**
     * Gets the name of the academic professional making the request.
     * @return the professional's name
     */
    public String getProfessionalName() {
        return professionalName;
    }

    /**
     * Sets the name of the academic professional making the request.
     * @param professionalName the professional's name to set
     */
    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName;
    }

    /**
     * Gets the title of the course associated with the request.
     * @return the course title
     */
    public String getCourseTitle() {
        return courseTitle;
    }

    /**
     * Sets the title of the course associated with the request.
     * @param courseTitle the course title to set
     */
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
