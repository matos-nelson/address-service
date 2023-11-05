package org.rent.circle.address.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rent.circle.address.api.persistence.model.Address;
import org.rent.circle.address.api.persistence.model.State;
import org.rent.circle.address.api.persistence.model.Zip;
import org.rent.circle.address.api.persistence.repository.AddressRepository;
import org.rent.circle.address.api.persistence.repository.ZipRepository;
import org.rent.circle.address.api.service.mapper.AddressMapper;
import org.rent.circle.address.dto.runtime.AddressDto;
import org.rent.circle.address.dto.runtime.SaveAddressDto;

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
        Exception exception = assertThrows(BadRequestException.class,
            () -> addressService.saveAddress(new SaveAddressDto()));

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
        Exception exception = assertThrows(BadRequestException.class, () -> addressService.saveAddress(saveAddressDto));

        // Assert
        assertEquals("Given address information is not valid", exception.getMessage());
    }

    @Test
    public void saveAddress_WhenAddressAlreadyExists_ShouldReturnExistingAddress() {
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
        when(addressRepository.findAddress(saveAddressDto.getStreet1(), saveAddressDto.getStreet2(),
            zip.getId())).thenReturn(address);

        // Act
        Long result = addressService.saveAddress(saveAddressDto);

        // Assert
        assertNotNull(result);
        assertEquals(address.getId(), result);
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
        when(addressRepository.findAddress(saveAddressDto.getStreet1(), saveAddressDto.getStreet2(),
            zip.getId())).thenReturn(null);
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
        address.setStreet1("street1");
        address.setStreet2("street2");
        address.setZip(zip);

        AddressDto addressDto = new AddressDto();
        when(addressRepository.findById(addressId)).thenReturn(address);
        when(addressMapper.toDto(address)).thenReturn(addressDto);

        // Act
        AddressDto result = addressService.getAddress(address.getId());

        // Assert
        assertEquals(addressDto, result);
    }

    @Test
    public void getAddresses_WhenCalled_ShouldReturnAddresses() {
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
        address.setStreet1("street1");
        address.setStreet2("street2");
        address.setZip(zip);

        List<Address> addresses = new ArrayList<>();
        addresses.add(address);

        AddressDto addressDto = new AddressDto();
        when(addressRepository.getAll(Collections.singletonList(addressId))).thenReturn(addresses);
        when(addressMapper.toDtoList(addresses)).thenReturn(Collections.singletonList(addressDto));

        // Act
        List<AddressDto> result = addressService.getAllAddresses(Collections.singletonList(addressId));

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(addressDto, result.get(0));
    }
}
