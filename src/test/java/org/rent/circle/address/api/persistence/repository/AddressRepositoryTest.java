package org.rent.circle.address.api.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    public void findAddress_WhenNotFound_ShouldReturnNull() {
        // Arrange

        // Act
        Address result = addressRepository.findAddress("480", "APT 1234", 1L);

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findAddress_WhenAddressHasANullStreet2ValueAndDoesNotExist_ShouldReturnNull() {
        // Arrange

        // Act
        Address result = addressRepository.findAddress("1234 Main St", null, 3L);

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findAddress_WhenAddressHasANullStreet2ValueAndExists_ShouldReturnAddress() {
        // Arrange

        // Act
        Address result = addressRepository.findAddress("1234 Main Rd", null, 3L);

        // Assert
        assertNotNull(result);
        assertEquals("1234 Main Rd", result.getStreet1());
        assertNull(result.getStreet2());
        assertEquals(200L, result.getId());
        assertEquals(3L, result.getZip().getId());
        assertEquals("75051", result.getZip().getCode());
        assertEquals("Dallas", result.getZip().getCity());
        assertEquals(3L, result.getZip().getState().getId());
        assertEquals("Texas", result.getZip().getState().getName());
        assertEquals("TX", result.getZip().getState().getCode());
    }

    @Test
    @TestTransaction
    public void findAddress_WhenCalled_ShouldReturnAddress() {
        // Arrange

        // Act
        Address result = addressRepository.findAddress("4800 E Interstate 440 Road", "APT 1234", 1L);

        // Assert
        assertNotNull(result);
        assertEquals("4800 E Interstate 440 Road", result.getStreet1());
        assertEquals("APT 1234", result.getStreet2());
        assertEquals(100L, result.getId());
        assertEquals(1L, result.getZip().getId());
        assertEquals("90001", result.getZip().getCode());
        assertEquals("Los Angeles", result.getZip().getCity());
        assertEquals(1L, result.getZip().getState().getId());
        assertEquals("California", result.getZip().getState().getName());
        assertEquals("CA", result.getZip().getState().getCode());
    }

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
