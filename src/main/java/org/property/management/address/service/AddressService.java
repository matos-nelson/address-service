package org.property.management.address.service;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import lombok.AllArgsConstructor;
import org.property.management.address.dto.SaveAddressDto;
import org.property.management.address.persistence.model.Address;
import org.property.management.address.persistence.model.Zip;
import org.property.management.address.persistence.repository.AddressRepository;
import org.property.management.address.persistence.repository.ZipRepository;
import org.property.management.address.service.mapper.AddressMapper;

@AllArgsConstructor
@ApplicationScoped
public class AddressService {

    private final ZipRepository zipRepository;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Transactional
    public Long saveAddress(SaveAddressDto saveAddressDto) {
        Zip zip = zipRepository.findByZipAndCity(saveAddressDto.getZipcode(), saveAddressDto.getCity());
        if (zip == null || !zip.getState().getCode().equalsIgnoreCase(saveAddressDto.getStateCode())) {
            throw new BadRequestException("Given address information is not valid");
        }

        Address address = addressMapper.toModel(saveAddressDto);
        address.setZip(zip);

        addressRepository.persist(address);
        return address.getId();
    }
}