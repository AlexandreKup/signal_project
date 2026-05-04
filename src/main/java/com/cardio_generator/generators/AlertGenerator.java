package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * This class generate alert events for patients.
 * Alerts can be triggered or resolved randomly.
 */
public class AlertGenerator implements PatientDataGenerator {

    // Extracted constant and renamed to UPPER_SNAKE_CASE.
    private static final double LAMBDA = 0.1; // Average rate (alerts per period), adjust based on desired frequency

    // Changed constant name to UPPER_SNAKE_CASE
    public static final Random RANDOM_GENERATOR = new Random();
    // Changed field name to lowerCamelCase
    // Added final because it is not reasigned
    private final boolean[] alertStates; // false = resolved, true = pressed


    /**
     * Create alert generator for patients.
     * It initialize the alert state for each patient.
     *
     * @param patientCount number of patients
     */
    public AlertGenerator(int patientCount) {
        alertStates = new boolean[patientCount + 1];
    }

    /**
     * Generate alert for a patient.
     * It decide randomly if alert is triggered or resolved.
     *
     * @param patientId id of patient
     * @param outputStrategy strategy used to output data
     */

    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            if (alertStates[patientId]) {
                if (RANDOM_GENERATOR.nextDouble() < 0.9) { // 90% chance to resolve
                    alertStates[patientId] = false;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                double p = -Math.expm1(-LAMBDA); // Probability of at least one alert in the period
                boolean alertTriggered = RANDOM_GENERATOR.nextDouble() < p;

                if (alertTriggered) {
                    alertStates[patientId] = true;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}
