package com.data_management;

import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

public class SingletonTest {
    @Test
    void testDataStorageSingleton() {
        // Testing that getInstance returns the same instance
        DataStorage instance1 = DataStorage.getInstance();
        DataStorage instance2 = DataStorage.getInstance();
        assertSame(instance1, instance2, "Both instances should be the same");
    }
}