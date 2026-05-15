package com.alerts.decorators;

import com.alerts.Alert;

/**
 * A decorator that adds a priority level to an existing alert.
 * Useful for highlighting alerts that require inmediate attention.
 */
public class PriorityAlertDecorator extends AlertDecorator {
    private String priorityLevel;

    /**
     * Constructs a decorator that attaches a priority tag to an alert.
     *
     * @param alert the alert to be prioritized
     * @param priority the level of urgency (e.g., "HIGH", "URGENT")
     */
    public PriorityAlertDecorator(Alert alert, String priority) {
        super(alert);
        this.priorityLevel = priority;
    }

    /**
     * Retrieves the priority level assigned to this alert.
     *
     * @return the string representation of the priority level
     */
    public String getPriorityLevel() {
        return this.priorityLevel;
    }
}