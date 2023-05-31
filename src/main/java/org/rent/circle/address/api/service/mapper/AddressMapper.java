package org.rent.circle.address.api.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.rent.circle.address.api.persistence.model.Address;
import org.rent.circle.address.dto.runtime.AddressDto;
import org.rent.circle.address.dto.runtime.SaveAddressDto;

@Mapper(componentModel = "cdi")
public interface AddressMapper {

    Address toModel(SaveAddressDto saveAddressDto);

    @Mapping(target = "zipcode", source = "address.zip.code")
    @Mapping(target = "city", source = "address.zip.city")
    @Mapping(target = "stateCode", source = "address.zip.state.code")
    AddressDto toDto(Address address);

    List<AddressDto> toDtoList(List<Address> addresses);
}
