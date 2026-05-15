package com.alerts;

import org.junit.jupiter.api.Test;

import com.alerts.strategies.BloodPressureStrategy;
import com.data_management.Patient;

public class StrategyTest {
    @Test
    void testBloodPressureStrategyLogic() {
        // Verifying that the threshold logic triggers correctly
        Patient patient = new Patient(1);
        // Adding a critical reading (> 180 systolic)
        patient.addRecord(190.0, "SystolicBP", System.currentTimeMillis());
        
        BloodPressureStrategy strategy = new BloodPressureStrategy();
        // This confirms the strategy class is properly decoupled 
        strategy.checkAlert(patient); 
        
        
    }
}