package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * This class generate blood saturation data for patients.
 * It simulate small variation of oxygen level over time.
 */
public class BloodSaturationDataGenerator implements PatientDataGenerator {

    private static final Random random = new Random();
    private int[] lastSaturationValues;

    /**
     * Create a generator for blood saturation data.
     * It initialize baseline saturation values for each patient.
     *
     * @param patientCount number of patients
     */
    public BloodSaturationDataGenerator(int patientCount) {

        lastSaturationValues = new int[patientCount + 1];

        // Initialize with baseline saturation values for each patient
        for (int i = 1; i <= patientCount; i++) {
            lastSaturationValues[i] = 95 + random.nextInt(6); // Initializes with a value between 95 and 100
        }
    }

    /**
     * Generate new saturation value for a patient.
     * Value stay in realistic range between 90 and 100.
     *
     * @param patientId id of patient
     * @param outputStrategy strategy to output data
     */
    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            // Simulate blood saturation values
            int variation = random.nextInt(3) - 1; // -1, 0, or 1 to simulate small fluctuations
            int newSaturationValue = lastSaturationValues[patientId] + variation;

            // Ensure the saturation stays within a realistic and healthy range
            newSaturationValue = Math.min(Math.max(newSaturationValue, 90), 100);
            lastSaturationValues[patientId] = newSaturationValue;
            outputStrategy.output(patientId, System.currentTimeMillis(), "Saturation",
                    Double.toString(newSaturationValue) + "%");
        } catch (Exception e) {
            System.err.println("An error occurred while generating blood saturation data for patient " + patientId);
            e.printStackTrace(); // This will print the stack trace to help identify where the error occurred.
        }
    }
}
