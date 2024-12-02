/**
 * File name: CourseDTO.java
 * Author: Group10
 * Course: CST8288-section 031
 * Term: Fall 2024
 * Assignment: Final Project
 * Date: November 30
 * Purpose: CourseDTO represents a course offered by an institution in the system.
 */
package com.aep.model;

import java.math.BigDecimal;

/**
 * CourseDTO represents a course offered by an institution in the system.
 * It serves as a Data Transfer Object for the Course table.
 */
public class CourseDTO {

    /**
     * The unique ID of the course.
     */
    private int courseId;

    /**
     * The unique ID of the institution offering the course.
     */
    private int institutionId;

    /**
     * The title of the course.
     */
    private String courseTitle;

    /**
     * The unique code identifying the course.
     */
    private String courseCode;

    /**
     * The academic term in which the course is offered (e.g., Fall, Winter).
     */
    private String term;

    /**
     * The course outline or description.
     */
    private String outline;

    /**
     * The schedule for the course (e.g., days and times it is held).
     */
    private String schedule;

    /**
     * The preferred qualifications for teaching the course.
     */
    private String preferredQualifications;

    /**
     * The delivery method of the course (e.g., Online, In-person, Hybrid).
     */
    private String deliveryMethod;

    /**
     * The compensation offered for teaching the course.
     */
    private BigDecimal compensation;

    /**
     * The name of the institution offering the course.
     */
    private String institutionName;

    /**
     * Gets the unique ID of the course.
     * @return the course ID
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * Sets the unique ID of the course.
     * @param courseId the course ID to set
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    /**
     * Gets the unique ID of the institution offering the course.
     * @return the institution ID
     */
    public int getInstitutionId() {
        return institutionId;
    }

    /**
     * Sets the unique ID of the institution offering the course.
     * @param institutionId the institution ID to set
     */
    public void setInstitutionId(int institutionId) {
        this.institutionId = institutionId;
    }

    /**
     * Gets the title of the course.
     * @return the course title
     */
    public String getCourseTitle() {
        return courseTitle;
    }

    /**
     * Sets the title of the course.
     * @param courseTitle the course title to set
     */
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    /**
     * Gets the unique code identifying the course.
     * @return the course code
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Sets the unique code identifying the course.
     * @param courseCode the course code to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Gets the academic term in which the course is offered.
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * Sets the academic term in which the course is offered.
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * Gets the course outline or description.
     * @return the course outline
     */
    public String getOutline() {
        return outline;
    }

    /**
     * Sets the course outline or description.
     * @param outline the course outline to set
     */
    public void setOutline(String outline) {
        this.outline = outline;
    }

    /**
     * Gets the schedule for the course.
     * @return the schedule
     */
    public String getSchedule() {
        return schedule;
    }

    /**
     * Sets the schedule for the course.
     * @param schedule the schedule to set
     */
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    /**
     * Gets the preferred qualifications for teaching the course.
     * @return the preferred qualifications
     */
    public String getPreferredQualifications() {
        return preferredQualifications;
    }

    /**
     * Sets the preferred qualifications for teaching the course.
     * @param preferredQualifications the preferred qualifications to set
     */
    public void setPreferredQualifications(String preferredQualifications) {
        this.preferredQualifications = preferredQualifications;
    }

    /**
     * Gets the delivery method of the course.
     * @return the delivery method
     */
    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    /**
     * Sets the delivery method of the course.
     * @param deliveryMethod the delivery method to set
     */
    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    /**
     * Gets the compensation offered for teaching the course.
     * @return the compensation
     */
    public BigDecimal getCompensation() {
        return compensation;
    }

    /**
     * Sets the compensation offered for teaching the course.
     * @param compensation the compensation to set
     */
    public void setCompensation(BigDecimal compensation) {
        this.compensation = compensation;
    }

    /**
     * Gets the name of the institution offering the course.
     * @return the institution name
     */
    public String getInstitutionName() {
        return institutionName;
    }

    /**
     * Sets the name of the institution offering the course.
     * @param institutionName the institution name to set
     */
    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }
}
