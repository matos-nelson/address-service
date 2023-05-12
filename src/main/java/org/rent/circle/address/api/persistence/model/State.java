package org.rent.circle.address.api.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rent.circle.address.api.persistence.BaseModel;

@Entity
@Table(name = "state")
@Setter
@Getter
@ToString
public class State extends BaseModel {

    @Id
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;
}
