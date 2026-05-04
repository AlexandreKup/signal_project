package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Interface for generating patient data.
 * Each implementation generate a specific type of data.
 */
public interface PatientDataGenerator {
    /**
     * Generate data for a patient and send it to output.
     *
     * @param patientId id of the patient
     * @param outputStrategy strategy used to output data
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
