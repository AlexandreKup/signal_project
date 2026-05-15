package com.alerts.factories;

import com.alerts.Alert;

/**
 * Factory implementation for creating blood pressure related alerts.
 */
public class BloodPressureAlertFactory extends AlertFactory {

    /**
     * Creates a Blood Pressure specific alert.
     *
     * @param patientId the unique identifier of the patient
     * @param condition the blood pressure condition 
     * @param timestamp the time of detection
     * @return an Alert object labeled as a Blood Pressure Alert
     */
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        return new Alert(patientId, "Blood Pressure Alert: " + condition, timestamp);
    }
}
