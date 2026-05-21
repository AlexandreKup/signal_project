package com.alerts;

import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data
 * and generating alerts when certain predefined conditions are met. This class
 * relies on a {@link DataStorage} instance to access patient data and evaluate
 * it against specific health criteria.
 */
public class AlertGenerator {
    
    private final DataStorage dataStorage;
    private List<Alert> triggeredAlerts;

    /**
     * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}.
     * The {@code DataStorage} is used to retrieve patient data that this class
     * will monitor and evaluate.
     *
     * @param dataStorage the data storage system that provides access to patient
     *                    data
     */
    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
        this.triggeredAlerts = new ArrayList<>();
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions
     * are met. If a condition is met, an alert is triggered via the
     * {@link #triggerAlert}
     * method. This method should define the specific conditions under which an
     * alert
     * will be triggered.
     *
     * @param patient the patient data to evaluate for alert conditions
     */
    public void evaluateData(Patient patient) {
        List<PatientRecord> records =
                patient.getRecords(0, System.currentTimeMillis());

        for (PatientRecord record : records) {
            String recordType = record.getRecordType();
            double value = record.getMeasurementValue();

            if (recordType.equalsIgnoreCase("TriggeredAlert")
                    && value == 1.0) {

                triggerAlert(new Alert(
                        String.valueOf(patient.getPatientId()),
                        "Manual triggered alert",
                        record.getTimestamp()));
            }

            if (recordType.equalsIgnoreCase("BloodSaturation")
                    && value < 92.0) {

                triggerAlert(new Alert(
                        String.valueOf(patient.getPatientId()),
                        "Low blood oxygen saturation",
                        record.getTimestamp()));
            }

            if (recordType.equalsIgnoreCase("SystolicBloodPressure")
                    && (value > 180.0 || value < 90.0)) {

                triggerAlert(new Alert(
                        String.valueOf(patient.getPatientId()),
                        "Critical systolic blood pressure",
                        record.getTimestamp()));
            }

            if (recordType.equalsIgnoreCase("DiastolicBloodPressure")
                    && (value > 120.0 || value < 60.0)) {

                triggerAlert(new Alert(
                        String.valueOf(patient.getPatientId()),
                        "Critical diastolic blood pressure",
                        record.getTimestamp()));
            }

        }

        checkRapidOxygenDrop(patient, records);
        checkHypotensiveHypoxemia(patient, records);
        checkBloodPressureTrend(patient, records);
        checkEcgPeak(patient, records);

    }

    /**
     * Triggers an alert for the monitoring system. It can be extended to
     * notify medical staff, log the alert, or perform other actions. 
     * Now the method assumes that the alert info is fully formed.
     *
     * @param alert the alert object containing details about the alert condition
     */
    private void triggerAlert(Alert alert) {
        triggeredAlerts.add(alert);
    }


    /**
     * Checks if blood oxygen saturation drops by at least 5 percent
     * within a 10-minute interval.
     *
     * @param patient the patient whose records are evaluated
     * @param records the records to check
     */
    private void checkRapidOxygenDrop(Patient patient, List<PatientRecord> records) {
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
                    triggerAlert(new Alert(
                            String.valueOf(patient.getPatientId()),
                            "Rapid blood oxygen saturation drop",
                            second.getTimestamp()));
                    return;
                }
            }
        }
    }

    /**
     * Checks if the patient has low blood pressure and low oxygen.
     *
     * @param patient the patient to check
     * @param records the records of the patient
     */
    private void checkHypotensiveHypoxemia(
            Patient patient,
            List<PatientRecord> records) {

        PatientRecord lowSystolicRecord = null;
        PatientRecord lowOxygenRecord = null;

        for (PatientRecord record : records) {
            if (record.getRecordType().equalsIgnoreCase("SystolicBloodPressure")
                    && record.getMeasurementValue() < 90.0) {
                lowSystolicRecord = record;
            }

            if (record.getRecordType().equalsIgnoreCase("BloodSaturation")
                    && record.getMeasurementValue() < 92.0) {
                lowOxygenRecord = record;
            }
        }

        if (lowSystolicRecord != null && lowOxygenRecord != null) {
            long alertTime = Math.max(
                    lowSystolicRecord.getTimestamp(),
                    lowOxygenRecord.getTimestamp());

            triggerAlert(new Alert(
                    String.valueOf(patient.getPatientId()),
                    "Hypotensive hypoxemia",
                    alertTime));
        }
    }

    /**
     * Checks if blood pressure goes up or down strongly over three readings.
     *
     * @param patient the patient to check
     * @param records the records of the patient
     */
    private void checkBloodPressureTrend(
            Patient patient,
            List<PatientRecord> records) {

        checkTrendForType(
                patient,
                records,
                "SystolicBloodPressure",
                "Systolic blood pressure trend");

        checkTrendForType(
                patient,
                records,
                "DiastolicBloodPressure",
                "Diastolic blood pressure trend");
    }

    /**
     * Checks one type of blood pressure record for a trend.
     *
     * @param patient the patient to check
     * @param records the records of the patient
     * @param recordType the blood pressure type
     * @param alertCondition the alert text
     */
    private void checkTrendForType(
            Patient patient,
            List<PatientRecord> records,
            String recordType,
            String alertCondition) {

        List<PatientRecord> bloodPressureRecords = new ArrayList<>();

        for (PatientRecord record : records) {
            if (record.getRecordType().equalsIgnoreCase(recordType)) {
                bloodPressureRecords.add(record);
            }
        }

        for (int i = 0; i <= bloodPressureRecords.size() - 3; i++) {
            PatientRecord first = bloodPressureRecords.get(i);
            PatientRecord second = bloodPressureRecords.get(i + 1);
            PatientRecord third = bloodPressureRecords.get(i + 2);

            double firstChange = second.getMeasurementValue() - first.getMeasurementValue();
            double secondChange = third.getMeasurementValue() - second.getMeasurementValue();

            boolean increasingTrend = firstChange > 10.0 && secondChange > 10.0;

            boolean decreasingTrend = firstChange < -10.0 && secondChange < -10.0;

            if (increasingTrend || decreasingTrend) {
                triggerAlert(new Alert(
                        String.valueOf(patient.getPatientId()),
                        alertCondition,
                        third.getTimestamp()));
                return;
            }
        }
    }

    /**
     * Checks ECG records using a sliding window average.
     * An alert is triggered if one ECG value is much higher than
     * the average of the previous ECG values.
     *
     * @param patient the patient to check
     * @param records the records of the patient
     */
    private void checkEcgPeak(Patient patient, List<PatientRecord> records) {
        final int windowSize = 5;
        final double peakMultiplier = 1.5;

        List<PatientRecord> ecgRecords = new ArrayList<>();

        for (PatientRecord record : records) {
            if (record.getRecordType().equalsIgnoreCase("ECG")) {
                ecgRecords.add(record);
            }
        }

        for (int i = windowSize; i < ecgRecords.size(); i++) {
            double sum = 0.0;

            for (int j = i - windowSize; j < i; j++) {
                sum += ecgRecords.get(j).getMeasurementValue();
            }

            double average = sum / windowSize;
            PatientRecord currentRecord = ecgRecords.get(i);

            if (currentRecord.getMeasurementValue() > average * peakMultiplier) {
                triggerAlert(new Alert(
                        String.valueOf(patient.getPatientId()),
                        "Abnormal ECG peak",
                        currentRecord.getTimestamp()));
                return;
            }
        }
    }

    /**
     * Returns all alerts triggered during evaluation.
     *
     * @return a list of triggered alerts
     */
    public List<Alert> getTriggeredAlerts() {
        return triggeredAlerts;
    }
    
}
