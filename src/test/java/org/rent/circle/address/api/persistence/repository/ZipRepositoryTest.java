package org.rent.circle.address.api.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.address.api.persistence.model.Zip;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class ZipRepositoryTest {

    @Inject
    ZipRepository zipRepository;

    @Test
    @TestTransaction
    public void findByZipAndCity_WhenZipDoesNotExist_ShouldReturnNull() {
        // Arrange

        // Act
        Zip result = zipRepository.findByZipAndCity("12345", "City");

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findByZipAndCity_WhenCalled_ShouldReturnZip() {
        // Arrange
        String zipCode = "90001";
        String city = "Los Angeles";

        // Act
        Zip result = zipRepository.findByZipAndCity(zipCode, city);

        // Assert
        assertNotNull(result);
        assertEquals(city, result.getCity());
        assertEquals(zipCode, result.getCode());
    }
}
