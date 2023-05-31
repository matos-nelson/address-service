package org.rent.circle.address.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import org.rent.circle.address.api.persistence.model.Address;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {

    public List<Address> getAll(List<Long> ids) {
        Parameters queryParams = Parameters.with("ids", ids);
        return list("id in :ids", queryParams);
    }
}
