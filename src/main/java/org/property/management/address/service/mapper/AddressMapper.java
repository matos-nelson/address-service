package org.property.management.address.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.property.management.address.dto.runtime.AddressDto;
import org.property.management.address.dto.runtime.SaveAddressDto;
import org.property.management.address.persistence.model.Address;

@Mapper(componentModel = "cdi")
public interface AddressMapper {

    Address toModel(SaveAddressDto saveAddressDto);

    @Mapping(target = "zipcode", source = "address.zip.code")
    @Mapping(target = "city", source = "address.zip.city")
    @Mapping(target = "stateCode", source = "address.zip.state.code")
    AddressDto toDto(Address address);
}
