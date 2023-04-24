package org.property.management.address.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.property.management.address.persistence.BaseModel;

@Entity
@Table(name = "address")
@Setter
@Getter
public class Address extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_1")
    private String address1;

    @Column(name = "address_2")
    private String address2;

    private ZipId zipId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "zip_code", referencedColumnName = "zip_code", insertable = false, updatable = false),
        @JoinColumn(name = "city", referencedColumnName = "city", insertable = false, updatable = false),
        @JoinColumn(name = "state_id", referencedColumnName = "state_id", insertable = false, updatable = false),
    })
    private Zip zip;
}
