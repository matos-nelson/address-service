package org.property.management.address.persistence.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.property.management.address.persistence.BaseModel;

@Entity
@Table(name = "zip")
@Setter
@Getter
public class Zip extends BaseModel {

    @EmbeddedId
    private ZipId zipId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", insertable = false, updatable = false)
    private State state;
}

@AllArgsConstructor
@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
class ZipId implements Serializable {

    @Column(name = "zip_code")
    private String code;

    @Column(name = "city")
    private String city;

    @Column(name = "state_id")
    private Long stateId;
}