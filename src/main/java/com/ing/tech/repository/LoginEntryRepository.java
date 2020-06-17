package com.ing.tech.repository;

import com.ing.tech.model.Client;
import com.ing.tech.model.LoginEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginEntryRepository extends CrudRepository<LoginEntry, Long> {
    LoginEntry getLoginEntryById(Long id);
    List<LoginEntry> getLoginEntryByClient(Client client);
}
