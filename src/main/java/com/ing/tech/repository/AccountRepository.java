package com.ing.tech.repository;

import com.ing.tech.model.Account;
import com.ing.tech.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account getAccountByNumber(String number);
    Account getAccountByClient(Client client);
    boolean existsByNumber(String accountNumber);
}
