package org.property.management.address.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.property.management.address.dto.AddressDto;
import org.property.management.address.dto.SaveAddressDto;
import org.property.management.address.persistence.model.Address;
import org.property.management.address.persistence.model.State;
import org.property.management.address.persistence.model.Zip;
import org.property.management.address.persistence.repository.AddressRepository;
import org.property.management.address.persistence.repository.ZipRepository;
import org.property.management.address.service.mapper.AddressMapper;

@QuarkusTest
public class AddressServiceTest {

    @InjectMock
    ZipRepository zipRepository;

    @InjectMock
    AddressRepository addressRepository;

    @InjectMock
    AddressMapper addressMapper;

    @Inject
    AddressService addressService;

    @Test
    public void saveAddress_WhenZipCantBeFound_ShouldThrowBadException() {
        // Arrange
        when(zipRepository.findByZipAndCity(Mockito.anyString(), Mockito.anyString())).thenReturn(null);

        // Act
        Exception exception = assertThrows(BadRequestException.class, () -> {
            addressService.saveAddress(new SaveAddressDto());
        });

        // Assert
        assertEquals("Given address information is not valid", exception.getMessage());
    }

    @Test
    public void saveAddress_WhenFoundZipStateCodeDoesNotMatchWithGivenAddressStateCode_ShouldThrowBadException() {
        // Arrange
        State state = new State();
        state.setCode("AA");

        Zip zip = new Zip();
        zip.setCode("12345");
        zip.setCity("AAA-AA");
        zip.setState(state);

        SaveAddressDto saveAddressDto = SaveAddressDto.builder()
            .zipcode(zip.getCode())
            .city(zip.getCity())
            .stateCode("BB")
            .build();

        when(zipRepository.findByZipAndCity(saveAddressDto.getZipcode(), saveAddressDto.getCity())).thenReturn(zip);

        // Act
        Exception exception = assertThrows(BadRequestException.class, () -> {
            addressService.saveAddress(saveAddressDto);
        });

        // Assert
        assertEquals("Given address information is not valid", exception.getMessage());
    }

    @Test
    public void saveAddress_WhenCalled_ShouldReturnAddress() {
        // Arrange
        State state = new State();
        state.setCode("AA");

        Zip zip = new Zip();
        zip.setCode("12345");
        zip.setCity("AAA-AA");
        zip.setState(state);

        SaveAddressDto saveAddressDto = SaveAddressDto.builder()
            .zipcode(zip.getCode())
            .city(zip.getCity())
            .stateCode(zip.getState().getCode())
            .build();

        Address address = new Address();
        address.setId(123L);
        when(zipRepository.findByZipAndCity(saveAddressDto.getZipcode(), saveAddressDto.getCity())).thenReturn(zip);
        when(addressMapper.toModel(saveAddressDto)).thenReturn(address);

        // Act
        Long result = addressService.saveAddress(saveAddressDto);

        // Assert
        assertNotNull(result);
        assertEquals(address.getId(), result);
    }

    @Test
    public void getAddress_WhenCalled_ShouldReturnAddress() {
        // Arrange
        State state = new State();
        state.setCode("AA");

        Zip zip = new Zip();
        zip.setCode("12345");
        zip.setCity("AAA-AA");
        zip.setState(state);

        Long addressId = 123L;
        Address address = new Address();
        address.setId(addressId);
        address.setAddress1("address1");
        address.setAddress2("address2");
        address.setZip(zip);

        AddressDto addressDto = new AddressDto();
        when(addressRepository.findById(addressId)).thenReturn(address);
        when(addressMapper.toDto(address)).thenReturn(addressDto);

        // Act
        AddressDto result = addressService.getAddress(address.getId());

        // Assert
        assertEquals(addressDto, result);
    }
}
