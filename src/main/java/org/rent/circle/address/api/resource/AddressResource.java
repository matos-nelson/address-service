package org.rent.circle.address.api.resource;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import org.rent.circle.address.api.service.AddressService;
import org.rent.circle.address.dto.runtime.AddressDto;
import org.rent.circle.address.dto.runtime.SaveAddressDto;

@AllArgsConstructor
@Path("/address")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AddressResource {

    private final AddressService addressService;

    @GET
    @Path("/{id}")
    public AddressDto getAddress(@PathParam("id") long addressId) {
        return addressService.getAddress(addressId);
    }

    @POST
    public Long saveAddress(@Valid SaveAddressDto saveAddressDto) {
        return addressService.saveAddress(saveAddressDto);
    }
}
