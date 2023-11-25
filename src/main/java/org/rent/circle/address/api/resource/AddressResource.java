package org.rent.circle.address.api.resource;

import io.quarkus.security.Authenticated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import lombok.AllArgsConstructor;
import org.rent.circle.address.api.service.AddressService;
import org.rent.circle.address.dto.runtime.AddressDto;
import org.rent.circle.address.dto.runtime.SaveAddressDto;

@AllArgsConstructor
@Authenticated
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

    @GET
    public List<AddressDto> getAddresses(@QueryParam("ids") @NotEmpty @Size(max = 50) List<Long> addressIds) {
        return addressService.getAllAddresses(addressIds);
    }

    @POST
    public Long saveAddress(@Valid SaveAddressDto saveAddressDto) {
        return addressService.saveAddress(saveAddressDto);
    }
}
