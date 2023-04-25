package org.property.management.address.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.property.management.address.dto.SaveAddressDto;
import org.property.management.address.persistence.model.Address;

@QuarkusTest
public class AddressMapperTest {

    @Inject
    AddressMapper addressMapper;

    @Test
    public void toModel_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        Address result = addressMapper.toModel(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toModel_WhenGivenASaveAddressDto_ShouldMap() {
        // Arrange
        SaveAddressDto saveAddressDto = SaveAddressDto.builder()
            .address1("address_1")
            .address2("address_2")
            .city("City")
            .zipcode("12345")
            .stateCode("AA")
            .build();

        // Act
        Address result = addressMapper.toModel(saveAddressDto);

        // Assert
        assertNotNull(result);
        assertNull(result.getId());
        assertEquals(saveAddressDto.getAddress1(), result.getAddress1());
        assertEquals(saveAddressDto.getAddress2(), result.getAddress2());
    }
}
