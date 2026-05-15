package com.alerts.factories;

import com.alerts.Alert;

/**
 * Factory implementation for creating blood oxygen saturation alerts.
 */
public class BloodOxygenAlertFactory extends AlertFactory {

    /**
     * Creates a Blood Oxygen specific alert.
     *
     * @param patientId the unique identifier of the patient
     * @param condition the oxygen condition 
     * @param timestamp the time of detection
     * @return an Alert object labeled as a Blood Oxygen Alert
     */
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        return new Alert(patientId, "Blood Oxygen Alert: " + condition, timestamp);
    }
}