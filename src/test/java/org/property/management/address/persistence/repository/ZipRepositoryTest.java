package org.property.management.address.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.property.management.address.persistence.model.State;
import org.property.management.address.persistence.model.Zip;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class ZipRepositoryTest {

    @Inject
    EntityManager entityManager;

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
        State state = new State();
        state.setId(1L);
        state.setCode("AA");
        state.setName("name");
        entityManager.persist(state);

        Zip zip = new Zip();
        zip.setId(1L);
        zip.setStateId(state.getId());
        zip.setCode("12345");
        zip.setCity("City");
        entityManager.persist(zip);

        // Act
        Zip result = zipRepository.findByZipAndCity("12345", "City");

        // Assert
        assertNotNull(result);
        assertEquals(zip.getCity(), result.getCity());
        assertEquals(zip.getCode(), result.getCode());
    }
}
