package org.rent.circle.address.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.address.api.persistence.model.Address;
import org.rent.circle.address.api.persistence.model.State;
import org.rent.circle.address.api.persistence.model.Zip;
import org.rent.circle.address.dto.runtime.AddressDto;
import org.rent.circle.address.dto.runtime.SaveAddressDto;

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

    @Test
    public void toDto_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        AddressDto result = addressMapper.toDto(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toDto_WhenGivenAnAddress_ShouldMap() {
        // Arrange
        State state = new State();
        state.setId(1L);
        state.setName("California");
        state.setCode("CA");

        Zip zip = new Zip();
        zip.setState(state);
        zip.setCity("San Marcos");
        zip.setCode("90011");

        Address address = new Address();
        address.setId(1L);
        address.setAddress1("Address 1");
        address.setAddress2("Address 2");
        address.setZip(zip);

        // Act
        AddressDto result = addressMapper.toDto(address);

        // Assert
        assertNotNull(result);
        assertEquals(address.getAddress1(), result.getAddress1());
        assertEquals(address.getAddress2(), result.getAddress2());
        assertEquals(address.getZip().getCity(), result.getCity());
        assertEquals(address.getZip().getCode(), result.getZipcode());
        assertEquals(address.getZip().getState().getCode(), result.getStateCode());
    }
}
