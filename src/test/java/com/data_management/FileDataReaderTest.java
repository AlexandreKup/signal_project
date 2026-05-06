package com.data_management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

class FileDataReaderTest {

    @Test
    void testReadDataFromFile() throws IOException {
        File tempDirectory = new File("temp_test_data");
        tempDirectory.mkdir();

        File tempFile = new File(tempDirectory, "patient_data.txt");

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("1,80.0,HeartRate,1000\n");
            writer.write("1,95.5,BloodSaturation,2000\n");
        }

        DataStorage storage = new DataStorage();
        FileDataReader reader = new FileDataReader(tempDirectory.getPath());

        reader.readData(storage);

        List<PatientRecord> records = storage.getRecords(1, 0, 3000);

        assertEquals(2, records.size());
        assertEquals(80.0, records.get(0).getMeasurementValue());
        assertEquals("HeartRate", records.get(0).getRecordType());

        tempFile.delete();
        tempDirectory.delete();
    }

    @Test
    void testInvalidDirectoryThrowsIOException() {
        DataStorage storage = new DataStorage();
        FileDataReader reader = new FileDataReader("directory_that_does_not_exist");

        assertThrows(IOException.class, () -> reader.readData(storage));
    }
}