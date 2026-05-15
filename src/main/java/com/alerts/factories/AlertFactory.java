package com.alerts.factories;

import com.alerts.Alert;

/**
 * Base abstract class for the Factory Method pattern.
 * Defines the contract for creating specialized Alert objects.
 */
public abstract class AlertFactory {

    /**
     * Creates an instance of a specific Alert subclass.
     *
     * @param patientId the unique identifier of the patient
     * @param condition the specific medical condition triggering the alert
     * @param timestamp the time the alert was generated in miliseconds
     * @return a specific Alert object
     */
    public abstract Alert createAlert(String patientId, String condition, long timestamp);
}