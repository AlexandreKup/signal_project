package com.alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.data_management.DataStorage;
import com.data_management.Patient;

class AlertGeneratorTest {

    @Test
    void testLowBloodSaturationAlert() {
        DataStorage storage = DataStorage.getInstance();
        Patient patient = new Patient(1);
        patient.addRecord(89.0, "BloodSaturation", 1000L);

        AlertGenerator generator = new AlertGenerator(storage);

        generator.evaluateData(patient);

        assertEquals(1, generator.getTriggeredAlerts().size());
        assertEquals(
                "Blood Oxygen Alert: Low blood oxygen saturation",
                generator.getTriggeredAlerts().get(0).getCondition());
    }

    @Test
    void testCriticalSystolicBloodPressureAlert() {
        DataStorage storage = DataStorage.getInstance();

        Patient patient = new Patient(2);
        patient.addRecord(185.0, "SystolicBloodPressure", 2000L);

        AlertGenerator generator = new AlertGenerator(storage);

        generator.evaluateData(patient);

        assertEquals(1, generator.getTriggeredAlerts().size());
        assertEquals(
                "Blood Pressure Alert: Critical systolic blood pressure",
                generator.getTriggeredAlerts().get(0).getCondition());
    }

    @Test
    void testNoAlertTriggered() {
        DataStorage storage = DataStorage.getInstance();

        Patient patient = new Patient(3);
        patient.addRecord(98.0, "BloodSaturation", 3000L);

        AlertGenerator generator = new AlertGenerator(storage);

        generator.evaluateData(patient);

        assertEquals(0, generator.getTriggeredAlerts().size());
    }

    @Test
    void testRapidBloodOxygenDropAlert() {
        DataStorage storage = DataStorage.getInstance();

        Patient patient = new Patient(4);

        patient.addRecord(
                98.0,
                "BloodSaturation",
                1000L);

        patient.addRecord(
                90.0,
                "BloodSaturation",
                2000L);

        AlertGenerator generator = new AlertGenerator(storage);

        generator.evaluateData(patient);

        assertEquals(
                2,
                generator.getTriggeredAlerts().size());

        assertEquals(
                "Rapid blood oxygen saturation drop",
                generator.getTriggeredAlerts().get(1).getCondition());
    }

    @Test
    void testHypotensiveHypoxemiaAlert() {
        DataStorage storage = DataStorage.getInstance();

        Patient patient = new Patient(5);

        patient.addRecord(
                85.0,
                "SystolicBloodPressure",
                1000L);

        patient.addRecord(
                90.0,
                "BloodSaturation",
                2000L);

        AlertGenerator generator = new AlertGenerator(storage);

        generator.evaluateData(patient);

        assertEquals(
                3,
                generator.getTriggeredAlerts().size());

        assertEquals(
                "Hypotensive hypoxemia",
                generator.getTriggeredAlerts().get(2).getCondition());
    }

    @Test
    void testSystolicBloodPressureIncreasingTrendAlert() {
        DataStorage storage = DataStorage.getInstance();

        Patient patient = new Patient(6);

        patient.addRecord(100.0, "SystolicBloodPressure", 1000L);
        patient.addRecord(112.0, "SystolicBloodPressure", 2000L);
        patient.addRecord(125.0, "SystolicBloodPressure", 3000L);

        AlertGenerator generator = new AlertGenerator(storage);

        generator.evaluateData(patient);

        assertEquals(
                1,
                generator.getTriggeredAlerts().size());

        assertEquals(
                "Systolic blood pressure trend",
                generator.getTriggeredAlerts().get(0).getCondition());
    }

    @Test
    void testSystolicBloodPressureDecreasingTrendAlert() {
        DataStorage storage = DataStorage.getInstance();

        Patient patient = new Patient(7);

        patient.addRecord(150.0, "SystolicBloodPressure", 1000L);
        patient.addRecord(138.0, "SystolicBloodPressure", 2000L);
        patient.addRecord(125.0, "SystolicBloodPressure", 3000L);

        AlertGenerator generator = new AlertGenerator(storage);

        generator.evaluateData(patient);

        assertEquals(
                1,
                generator.getTriggeredAlerts().size());

        assertEquals(
                "Systolic blood pressure trend",
                generator.getTriggeredAlerts().get(0).getCondition());
    }

    @Test
    void testAbnormalEcgAlert() {
        DataStorage storage = DataStorage.getInstance();

        Patient patient = new Patient(8);

        patient.addRecord(80.0, "ECG", 1000L);
        patient.addRecord(82.0, "ECG", 2000L);
        patient.addRecord(79.0, "ECG", 3000L);
        patient.addRecord(81.0, "ECG", 4000L);
        patient.addRecord(80.0, "ECG", 5000L);

        // This value is much higher than the average of the previous 5 ECG values.
        patient.addRecord(170.0, "ECG", 6000L);

        AlertGenerator generator = new AlertGenerator(storage);

        generator.evaluateData(patient);

        assertEquals(
                1,
                generator.getTriggeredAlerts().size());

        assertEquals(
                "ECG Alert: Abnormal ECG peak",
                generator.getTriggeredAlerts().get(0).getCondition());
    }

    @Test
    void testManualTriggeredAlert() {
        DataStorage storage = DataStorage.getInstance();

        Patient patient = new Patient(9);

        patient.addRecord(
                1.0,
                "TriggeredAlert",
                1000L);

        AlertGenerator generator = new AlertGenerator(storage);

        generator.evaluateData(patient);

        assertEquals(
                1,
                generator.getTriggeredAlerts().size());

        assertEquals(
                "Manual triggered alert",
                generator.getTriggeredAlerts().get(0).getCondition());
    }
}