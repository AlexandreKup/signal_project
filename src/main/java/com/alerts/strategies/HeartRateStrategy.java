package com.alerts.strategies;

import java.util.List;

import com.data_management.Patient;
import com.data_management.PatientRecord;

/**
 * Strategy for monitoring abnormal heart rates/ECG.
 */
public class HeartRateStrategy implements AlertStrategy {

    @Override
    public void checkAlert(Patient patient) {
        List<PatientRecord> records = patient.getRecords(0, System.currentTimeMillis());

        for (PatientRecord record : records) {
            if (record.getRecordType().equalsIgnoreCase("ECG")) {
                double value = record.getMeasurementValue();

                if (value > 150.0 || value < 40.0) {
                    System.out.println("ECG alert for patient "
                            + patient.getPatientId() + " at " + record.getTimestamp());
                }
            }
        }
    }
}