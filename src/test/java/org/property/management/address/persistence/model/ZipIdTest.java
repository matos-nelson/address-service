package org.property.management.address.persistence.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ZipIdTest {

    @Test
    public void ZipId_SettersAndGetters_ShouldWork() {
        // Arrange
        ZipId zipId = new ZipId();

        // Act
        zipId.setCity("City");
        zipId.setCode("Code");
        zipId.setStateId(1L);

        // Assert
        assertEquals("City", zipId.getCity());
        assertEquals("Code", zipId.getCode());
        assertEquals(1L, zipId.getStateId());
    }
}
