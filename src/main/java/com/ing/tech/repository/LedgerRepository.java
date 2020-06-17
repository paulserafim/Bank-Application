package com.ing.tech.repository;

import com.ing.tech.model.Ledger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LedgerRepository extends CrudRepository<Ledger, Long> {
    Ledger getLedgerByTransactionId(Long transactionId);
    List<Ledger> getLedgerByDateTime(LocalDateTime localDateTime);
    List<Ledger> getLedgerByFromAccount(String fromAccount);
    List<Ledger> getLedgerByToAccount(String toAccount);
}
