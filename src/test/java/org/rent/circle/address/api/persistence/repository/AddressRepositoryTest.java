package org.rent.circle.address.api.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.rent.circle.address.api.persistence.model.Address;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class AddressRepositoryTest {

    @Inject
    AddressRepository addressRepository;

    @Test
    @TestTransaction
    public void getAll_WhenAddressesDontExistWithGivenId_ShouldReturnEmptyList() {
        // Arrange

        // Act
        List<Address> result = addressRepository.getAll(Collections.singletonList(123456L));

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    @TestTransaction
    public void getAll_WhenCalled_ShouldReturnAddressesThatMatchGivenIds() {
        // Arrange

        // Act
        List<Address> result = addressRepository.getAll(Arrays.asList(100L, 1234L));

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }
}
