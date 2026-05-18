package com.alerts.strategies;

import java.util.List;

import com.data_management.Patient;
import com.data_management.PatientRecord;

/**
 * Strategy for monitoring oxygen level drops.
 */
public class OxygenSaturationStrategy implements AlertStrategy {

    @Override
    public void checkAlert(Patient patient) {
        List<PatientRecord> records = patient.getRecords(0, System.currentTimeMillis());

        checkLowSaturation(patient, records);
        checkRapidDrop(patient, records);
    }

    private void checkLowSaturation(Patient patient, List<PatientRecord> records) {
        for (PatientRecord record : records) {
            if (record.getRecordType().equalsIgnoreCase("BloodSaturation")
                    && record.getMeasurementValue() < 92.0) {

                System.out.println("Low oxygen saturation alert for patient "
                        + patient.getPatientId() + " at " + record.getTimestamp());
            }
        }
    }

    private void checkRapidDrop(Patient patient, List<PatientRecord> records) {
        final long tenMinutes = 10L * 60L * 1000L;

        for (int i = 0; i < records.size(); i++) {
            PatientRecord first = records.get(i);

            if (!first.getRecordType().equalsIgnoreCase("BloodSaturation")) {
                continue;
            }

            for (int j = i + 1; j < records.size(); j++) {
                PatientRecord second = records.get(j);

                if (!second.getRecordType().equalsIgnoreCase("BloodSaturation")) {
                    continue;
                }

                long timeDifference = second.getTimestamp() - first.getTimestamp();
                double drop = first.getMeasurementValue() - second.getMeasurementValue();

                if (timeDifference <= tenMinutes && drop >= 5.0) {
                    System.out.println("Rapid oxygen drop alert for patient "
                            + patient.getPatientId() + " at " + second.getTimestamp());
                    return;
                }
            }
        }
    }
}