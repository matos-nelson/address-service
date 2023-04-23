package org.property.management.address.persistence.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class ZipId implements Serializable {

    @Column(name = "zip_code")
    private String code;

    @Column(name = "city")
    private String city;

    @Column(name = "state_id")
    private Long stateId;
}
