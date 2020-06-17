package com.ing.tech.repository;

import com.ing.tech.model.Account;
import com.ing.tech.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Client getClientById(Long id);
    Client getClientByAccount(Account account);
}
