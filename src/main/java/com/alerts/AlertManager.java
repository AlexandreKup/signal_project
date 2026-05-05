package com.alerts;

/**
 * AlertManager handles the dispatching of alerts to medical staff.
 */
public class AlertManager {
    
    // In a more advanced version, this could be a list of "Subscribers" 
    // like NurseStation, DoctorApp, or SMSGateway.
    public void sendAlert(Alert alert) {
        // 1. Log the alert for the system record
        System.out.println("ALERT DISPATCHED: " + alert.getCondition() + 
                           " for Patient: " + alert.getPatientId() + 
                           " at " + alert.getTimestamp());

        // 2. Routing Logic
        if (isCritical(alert)) {
            dispatchEmergencyResponse(alert);
        } else {
            notifyNursingStation(alert);
        }
    }

    private boolean isCritical(Alert alert) {
        // Example logic: Very high heart rate is critical
        return alert.getCondition().contains("Critical");
    }

    private void dispatchEmergencyResponse(Alert alert) {
        // Code to trigger high-priority alarms
        System.out.println("Triggering Emergency Rapid Response team!");
    }

    private void notifyNursingStation(Alert alert) {
        // Standard notification logic
        System.out.println("Updating Nursing Station Dashboard.");
    }
}
