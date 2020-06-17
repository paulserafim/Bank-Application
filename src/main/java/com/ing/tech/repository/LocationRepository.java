package com.ing.tech.repository;

import com.ing.tech.model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    Location getLocationById(Long id);
}
