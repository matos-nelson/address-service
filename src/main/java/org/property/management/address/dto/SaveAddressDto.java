package org.property.management.address.dto;

import io.smallrye.common.constraint.NotNull;
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
    private String address1;

    private String address2;

    @NotNull
    private String zipcode;

    @NotNull
    private String city;

    @NotNull
    private String stateCode;
}
