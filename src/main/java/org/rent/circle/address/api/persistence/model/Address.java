package org.rent.circle.address.api.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rent.circle.address.api.persistence.BaseModel;

@Entity
@Table(name = "address")
@Setter
@Getter
@ToString
public class Address extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street_1")
    private String street1;

    @Column(name = "street_2")
    private String street2;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zip_id", updatable = false)
    private Zip zip;
}
