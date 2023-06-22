package org.rent.circle.address.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import org.rent.circle.address.api.persistence.model.Address;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {

    public Address findAddress(String street1, String street2, Long zipId) {
        StringBuilder sb = new StringBuilder(
            "FROM Address a JOIN Zip z ON a.zip.id = z.id WHERE a.zip.id = :zipId and upper(a.street1) = upper(:street1)");
        Parameters queryParams = Parameters.with("street1", street1).and("zipId", zipId);
        if (street2 == null) {
            sb.append(" and street2 is null");
        } else {
            queryParams = queryParams.and("street2", street2);
            sb.append(" and upper(a.street2) = upper(:street2)");
        }

        return find(sb.toString(), queryParams).firstResult();
    }

    public List<Address> getAll(List<Long> ids) {
        Parameters queryParams = Parameters.with("ids", ids);
        return list("id in :ids", queryParams);
    }
}
