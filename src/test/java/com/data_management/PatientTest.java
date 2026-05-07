package com.data_management;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class PatientTest {

    @Test
    void testAddAndRetrieveRecords() {
        Patient patient = new Patient(1);

        patient.addRecord(
                98.0,
                "BloodSaturation",
                1000L);

        patient.addRecord(
                120.0,
                "SystolicBloodPressure",
                2000L);

        List<PatientRecord> records =
                patient.getRecords(500L, 2500L);

        assertEquals(2, records.size());
    }

    @Test
    void testEmptyRecordRange() {
        Patient patient = new Patient(1);

        patient.addRecord(
                98.0,
                "BloodSaturation",
                1000L);

        List<PatientRecord> records =
                patient.getRecords(2000L, 3000L);

        assertEquals(0, records.size());
    }

    @Test
    void testGetPatientId() {
        Patient patient = new Patient(15);

        assertEquals(15, patient.getPatientId());
    }
}