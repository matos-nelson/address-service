package org.rent.circle.address.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.Collections;
import java.util.List;
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
            .street1("street_1")
            .street2("street_2")
            .city("City")
            .zipcode("12345")
            .stateCode("AA")
            .build();

        // Act
        Address result = addressMapper.toModel(saveAddressDto);

        // Assert
        assertNotNull(result);
        assertNull(result.getId());
        assertEquals(saveAddressDto.getStreet1(), result.getStreet1());
        assertEquals(saveAddressDto.getStreet2(), result.getStreet2());
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
        address.setStreet1("Address 1");
        address.setStreet2("Address 2");
        address.setZip(zip);

        // Act
        AddressDto result = addressMapper.toDto(address);

        // Assert
        assertNotNull(result);
        assertEquals(address.getStreet1(), result.getStreet1());
        assertEquals(address.getStreet2(), result.getStreet2());
        assertEquals(address.getZip().getCity(), result.getCity());
        assertEquals(address.getZip().getCode(), result.getZipcode());
        assertEquals(address.getZip().getState().getCode(), result.getStateCode());
    }

    @Test
    public void toDtoList_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        List<AddressDto> result = addressMapper.toDtoList(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toDtoList_WhenGivenAnAddressList_ShouldMap() {
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
        address.setStreet1("Address 1");
        address.setStreet2("Address 2");
        address.setZip(zip);

        // Act
        List<AddressDto> result = addressMapper.toDtoList(Collections.singletonList(address));

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(address.getStreet1(), result.get(0).getStreet1());
        assertEquals(address.getStreet2(), result.get(0).getStreet2());
        assertEquals(address.getZip().getCity(), result.get(0).getCity());
        assertEquals(address.getZip().getCode(), result.get(0).getZipcode());
        assertEquals(address.getZip().getState().getCode(), result.get(0).getStateCode());
    }
}
