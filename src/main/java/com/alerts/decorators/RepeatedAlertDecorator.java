package com.alerts.decorators;

import com.alerts.Alert;

/**
 * Decorator that adds repetition logic to an alert.
 * Used to verify if a condition persists over a specific interval.
 */
public class RepeatedAlertDecorator extends AlertDecorator {

    /**
     * Constructs a decorator that allows for repeated alert checking.
     *
     * @param alert the alert to be decorated with repetition logic
     */
    public RepeatedAlertDecorator(Alert alert) {
        super(alert);
    }

    /**
     * Simulates the re-checking of alert conditions.
     * Represents the logic for verifying if an alert should persist.
     */
    public void recheckCondition() {
        // Logic to verify if the medical condition is still met
        System.out.println("Re-checking condition for patient: " + getPatientId());
    }
}