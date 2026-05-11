package com.alerts;

import com.data_management.Patient;

/**
 * Strategy interface for checking specific health metrics and triggering alerts.
 */
public interface AlertStrategy {
    /**
     * Evaluates the patient's data based on specific criteria.
     * * @param patient the patient whose data is being evaluated
     */
    void checkAlert(Patient patient);
}