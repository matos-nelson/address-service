package org.rent.circle.address.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import org.rent.circle.address.api.persistence.model.Address;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {

}
