package org.rent.circle.address.api.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rent.circle.address.api.persistence.BaseModel;

@Entity
@Table(name = "zip")
@Setter
@Getter
@ToString
public class Zip extends BaseModel {

    @Id
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "city")
    private String city;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", insertable = false, updatable = false)
    private State state;
}

