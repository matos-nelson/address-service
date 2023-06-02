package org.rent.circle.address.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import org.rent.circle.address.api.persistence.model.Address;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {

    public Address findAddress(String address1, String address2, Long zipId) {
        Parameters queryParams = Parameters.with("address1", address1)
            .and("address2", address2)
            .and("zipId", zipId);

        return find("FROM Address a "
            + "JOIN Zip z "
            + "ON a.zip.id = z.id "
            + "WHERE a.zip.id = :zipId and a.address1 = :address1 and a.address2 =:address2", queryParams)
            .firstResult();
    }

    public List<Address> getAll(List<Long> ids) {
        Parameters queryParams = Parameters.with("ids", ids);
        return list("id in :ids", queryParams);
    }
}
