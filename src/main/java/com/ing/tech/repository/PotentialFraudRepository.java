package com.ing.tech.repository;

import com.ing.tech.model.LoginEntry;
import com.ing.tech.model.PotentialFraud;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PotentialFraudRepository extends CrudRepository<PotentialFraud, Long> {
    PotentialFraud getPotentialFraudById(Long id);
    PotentialFraud getPotentialFraudByLoginEntry(LoginEntry loginEntry);
    boolean existsByLoginEntry(LoginEntry loginEntry);
}
