package org.rent.circle.address.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import javax.enterprise.context.ApplicationScoped;
import org.rent.circle.address.api.persistence.model.Zip;

@ApplicationScoped
public class ZipRepository implements PanacheRepository<Zip> {

    public Zip findByZipAndCity(String zip, String city) {
        Parameters queryParams = Parameters.with("zip", zip).and("city", city);
        return find("zip_code = :zip and upper(city) = upper(:city)", queryParams).firstResult();
    }
}
