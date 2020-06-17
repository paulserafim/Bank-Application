package com.ing.tech.repository;

import com.ing.tech.model.Client;
import com.ing.tech.model.Credentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepository extends CrudRepository<Credentials, Long> {
    Credentials getCredentialsByClient(Client client);
    Credentials getCredentialsByAccountNumber(String accountNumber);
    Credentials getCredentialsById(Long id);
    boolean existsById(Long id);
    boolean existsByAccountNumber(String accountNumber);
}
