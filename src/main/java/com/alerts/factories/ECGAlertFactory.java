package com.alerts.factories;

import com.alerts.Alert;

/**
 * Factory implementation for creating ECG and heart rate related alerts.
 */
public class ECGAlertFactory extends AlertFactory {

    /**
     * Creates an ECG specific alert.
     *
     * @param patientId the unique identifier of the patient
     * @param condition the heart rate condition 
     * @param timestamp the time of detection
     * @return an Alert object labeled as an ECG Alert
     */
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        return new Alert(patientId, "ECG Alert: " + condition, timestamp);
    }
}