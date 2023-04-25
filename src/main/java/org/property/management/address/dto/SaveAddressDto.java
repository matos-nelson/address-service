package org.property.management.address.dto;

import io.smallrye.common.constraint.NotNull;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveAddressDto {

    @NotNull
    @NotBlank(message = "Address may not be blank")
    private String address1;

    private String address2;

    @NotNull
    @NotBlank(message = "Zipcode may not be blank")
    private String zipcode;

    @NotNull
    @NotBlank(message = "City may not be blank")
    private String city;

    @NotNull
    @NotBlank(message = "State may not be blank")
    private String stateCode;
}
