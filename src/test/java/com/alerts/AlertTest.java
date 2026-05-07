package com.alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AlertTest {

    @Test
    void testAlertGetters() {
        Alert alert =
                new Alert(
                        "1",
                        "Low blood oxygen saturation",
                        1000L);

        assertEquals("1", alert.getPatientId());
        assertEquals(
                "Low blood oxygen saturation",
                alert.getCondition());
        assertEquals(1000L, alert.getTimestamp());
    }
}