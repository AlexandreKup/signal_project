package com.alerts;

import com.data_management.Patient;

/**
 * Strategy for monitoring abnormal heart rates/ECG.
 */
public class HeartRateStrategy implements AlertStrategy {
    @Override
    public void checkAlert(Patient patient) {
        // Implementation logic for:
        // 1. Irregular heart rates and rhythms[cite: 218].
        // 2. Peaks far beyond the sliding window average[cite: 360].
    }
}