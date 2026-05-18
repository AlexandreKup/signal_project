package com.alerts.strategies;

import java.util.ArrayList;
import java.util.List;

import com.data_management.Patient;
import com.data_management.PatientRecord;

/**
 * Strategy for monitoring blood pressure anomalies.
 */
public class BloodPressureStrategy implements AlertStrategy {

    @Override
    public void checkAlert(Patient patient) {
        List<PatientRecord> records = patient.getRecords(0, System.currentTimeMillis());

        checkCriticalBloodPressure(patient, records);
        checkTrend(patient, records, "SystolicBloodPressure");
        checkTrend(patient, records, "DiastolicBloodPressure");
    }

    private void checkCriticalBloodPressure(Patient patient, List<PatientRecord> records) {
        for (PatientRecord record : records) {
            String type = record.getRecordType();
            double value = record.getMeasurementValue();

            boolean criticalSystolic = type.equalsIgnoreCase("SystolicBloodPressure")
                    && (value > 180.0 || value < 90.0);

            boolean criticalDiastolic = type.equalsIgnoreCase("DiastolicBloodPressure")
                    && (value > 120.0 || value < 60.0);

            if (criticalSystolic || criticalDiastolic) {
                System.out.println("Blood pressure alert for patient "
                        + patient.getPatientId() + " at " + record.getTimestamp());
            }
        }
    }

    private void checkTrend(Patient patient, List<PatientRecord> records, String recordType) {
        List<PatientRecord> filteredRecords = new ArrayList<>();

        for (PatientRecord record : records) {
            if (record.getRecordType().equalsIgnoreCase(recordType)) {
                filteredRecords.add(record);
            }
        }

        for (int i = 0; i <= filteredRecords.size() - 3; i++) {
            double first = filteredRecords.get(i).getMeasurementValue();
            double second = filteredRecords.get(i + 1).getMeasurementValue();
            double third = filteredRecords.get(i + 2).getMeasurementValue();

            boolean increasing = second - first > 10.0 && third - second > 10.0;
            boolean decreasing = first - second > 10.0 && second - third > 10.0;

            if (increasing || decreasing) {
                System.out.println("Blood pressure trend alert for patient "
                        + patient.getPatientId() + " using " + recordType);
                return;
            }
        }
    }
}