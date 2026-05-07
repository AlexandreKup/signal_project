package com.data_management;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PatientRecordTest {

    @Test
    void testPatientRecordGetters() {
        PatientRecord record =
                new PatientRecord(
                        1,
                        98.5,
                        "BloodSaturation",
                        1000L);

        assertEquals(1, record.getPatientId());
        assertEquals(98.5, record.getMeasurementValue());
        assertEquals("BloodSaturation", record.getRecordType());
        assertEquals(1000L, record.getTimestamp());
    }
}