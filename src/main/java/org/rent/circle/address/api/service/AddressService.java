package org.rent.circle.address.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.address.api.persistence.model.Address;
import org.rent.circle.address.api.persistence.model.Zip;
import org.rent.circle.address.api.persistence.repository.AddressRepository;
import org.rent.circle.address.api.persistence.repository.ZipRepository;
import org.rent.circle.address.api.service.mapper.AddressMapper;
import org.rent.circle.address.dto.runtime.AddressDto;
import org.rent.circle.address.dto.runtime.SaveAddressDto;

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

    public List<AddressDto> getAllAddresses(List<Long> addressIds) {
        List<Address> addresses = addressRepository.getAll(addressIds);
        return addressMapper.toDtoList(addresses);
    }

    @Transactional
    public Long saveAddress(SaveAddressDto saveAddressDto) {
        Zip zip = zipRepository.findByZipAndCity(saveAddressDto.getZipcode(), saveAddressDto.getCity());
        if (zip == null || !zip.getState().getCode().equalsIgnoreCase(saveAddressDto.getStateCode())) {
            log.error("Zip is not valid. Zip: {} Given Info: {}", zip, saveAddressDto);
            throw new BadRequestException("Given address information is not valid");
        }

        Address address = addressRepository.findAddress(saveAddressDto.getAddress1(), saveAddressDto.getAddress2(),
            zip.getId());
        if (address != null) {
            return address.getId();
        }

        address = addressMapper.toModel(saveAddressDto);
        address.setZip(zip);

        addressRepository.persist(address);
        return address.getId();
    }
}
