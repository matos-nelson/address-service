package org.property.management.address.service;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.property.management.address.dto.runtime.AddressDto;
import org.property.management.address.dto.runtime.SaveAddressDto;
import org.property.management.address.persistence.model.Address;
import org.property.management.address.persistence.model.Zip;
import org.property.management.address.persistence.repository.AddressRepository;
import org.property.management.address.persistence.repository.ZipRepository;
import org.property.management.address.service.mapper.AddressMapper;

@AllArgsConstructor
@ApplicationScoped
@Slf4j
public class AddressService {

    private final ZipRepository zipRepository;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressDto getAddress(Long addressId) {
        Address address = addressRepository.findById(addressId);

        return addressMapper.toDto(address);
    }

    @Transactional
    public Long saveAddress(SaveAddressDto saveAddressDto) {
        Zip zip = zipRepository.findByZipAndCity(saveAddressDto.getZipcode(), saveAddressDto.getCity());
        if (zip == null || !zip.getState().getCode().equalsIgnoreCase(saveAddressDto.getStateCode())) {
            log.error("Zip is not valid. Zip: {} Given Info: {}", zip, saveAddressDto);
            throw new BadRequestException("Given address information is not valid");
        }

        Address address = addressMapper.toModel(saveAddressDto);
        address.setZip(zip);

        addressRepository.persist(address);
        return address.getId();
    }
}
