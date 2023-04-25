package org.property.management.address.resource;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import org.property.management.address.dto.SaveAddressDto;
import org.property.management.address.service.AddressService;

@AllArgsConstructor
@Path("/address")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AddressResource {

    private final AddressService addressService;

    @POST
    public Long saveAddress(@Valid SaveAddressDto saveAddressDto) {
        return addressService.saveAddress(saveAddressDto);
    }
}
