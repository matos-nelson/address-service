package org.rent.circle.address.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import org.rent.circle.address.api.persistence.model.Address;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {

    public Address findAddress(String address1, String address2, Long zipId) {
        StringBuilder sb = new StringBuilder(
            "FROM Address a JOIN Zip z ON a.zip.id = z.id WHERE a.zip.id = :zipId and a.address1 = :address1");
        Parameters queryParams = Parameters.with("address1", address1).and("zipId", zipId);
        if (address2 == null) {
            sb.append(" and address2 is null");
        } else {
            queryParams = queryParams.and("address2", address2);
            sb.append(" and a.address2 =:address2");
        }

        return find(sb.toString(), queryParams).firstResult();
    }

    public List<Address> getAll(List<Long> ids) {
        Parameters queryParams = Parameters.with("ids", ids);
        return list("id in :ids", queryParams);
    }
}
