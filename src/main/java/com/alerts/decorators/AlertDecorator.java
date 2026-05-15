package com.alerts.decorators;

import com.alerts.Alert;

/**
 * The base decorator class for extending Alert funcionality dynamically.
 * Implements the same interface as the objects it decorates.
 */
public abstract class AlertDecorator extends Alert {
    /** The alert instance being decorated. */
    protected Alert decoratedAlert;

    /**
     * Constructs a new AlertDecorator.
     *
     * @param alert the alert to be decorated
     */
    public AlertDecorator(Alert alert) {
        super(alert.getPatientId(), alert.getCondition(), alert.getTimestamp());
        this.decoratedAlert = alert;
    }
}