package com.alerts;

import com.data_management.Patient;

/**
 * Strategy for monitoring oxygen level drops.
 */
public class OxygenSaturationStrategy implements AlertStrategy {
    @Override
    public void checkAlert(Patient patient) {
        // Implementation logic for:
        // 1. Low saturation (< 92%)[cite: 343].
        // 2. Rapid drop (5% or more within 10 minutes)[cite: 344].
    }
}