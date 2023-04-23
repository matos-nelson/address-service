package org.property.management.address.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.property.management.address.persistence.BaseModel;

@Entity
@Table(name = "state")
@Setter
@Getter
public class State extends BaseModel {

    @Id
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;
}
