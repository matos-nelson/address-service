package org.property.management.address.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.property.management.address.persistence.BaseModel;

@Entity
@Table(name = "zip")
@Setter
@Getter
public class Zip extends BaseModel {

    @Id
    private Long id;

    @Column(name = "zip_code")
    private String code;

    @Column(name = "city")
    private String city;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", insertable = false, updatable = false)
    private State state;
}

