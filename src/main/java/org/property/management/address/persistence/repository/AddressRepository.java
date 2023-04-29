package org.property.management.address.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import org.property.management.address.persistence.model.Address;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {

}
