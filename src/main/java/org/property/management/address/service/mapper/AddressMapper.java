package org.property.management.address.service.mapper;

import org.mapstruct.Mapper;
import org.property.management.address.dto.SaveAddressDto;
import org.property.management.address.persistence.model.Address;

@Mapper(componentModel = "cdi")
public interface AddressMapper {

    Address toModel(SaveAddressDto saveAddressDto);
}
