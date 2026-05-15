package com.alerts;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.alerts.factories.AlertFactory;
import com.alerts.factories.BloodPressureAlertFactory;

public class AlertFactoryTest {
    @Test
    void testBloodPressureFactoryCreatesCorrectAlert() {
        // Verifying the factory produces the correct alert type
        AlertFactory factory = new BloodPressureAlertFactory();
        Alert alert = factory.createAlert("1", "High BP", System.currentTimeMillis());
        assertTrue(alert.getCondition().contains("Blood Pressure"), "Alert condition should mention Blood Pressure");
    }
}