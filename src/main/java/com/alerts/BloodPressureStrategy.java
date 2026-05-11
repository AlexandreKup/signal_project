package com.alerts;

import java.util.List;

import com.data_management.Patient;
import com.data_management.PatientRecord;

/**
 * Strategy for monitoring blood pressure anomalies.
 */
public class BloodPressureStrategy implements AlertStrategy {
    @Override
    public void checkAlert(Patient patient) {
        // Implementation logic for:
        // 1. Systolic > 180 or < 90[cite: 336].
        // 2. Diastolic > 120 or < 60[cite: 336].
        // 3. Trend alert: 3 consecutive readings changing by > 10 mmHg[cite: 335].
        
        List<PatientRecord> records = patient.getRecords(0, System.currentTimeMillis());
        // Logic code provided by your partner in Part 3 goes here.
    }
}