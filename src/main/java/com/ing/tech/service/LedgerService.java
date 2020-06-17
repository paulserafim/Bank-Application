package com.ing.tech.service;

import com.ing.tech.model.*;
import com.ing.tech.model.dto.TransactionResponseDTO;
import com.ing.tech.repository.LedgerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class LedgerService {
    private LedgerRepository ledgerRepository;
    private AccountService accountService;
    private LoginEntryService loginEntryService;
    private PotentialFraudService potentialFraudService;

    public LedgerService (LedgerRepository ledgerRepository, AccountService accountService, LoginEntryService loginEntryService, PotentialFraudService potentialFraudService) {
        this.ledgerRepository = ledgerRepository;
        this.accountService = accountService;
        this.loginEntryService = loginEntryService;
        this.potentialFraudService = potentialFraudService;
    }

    public TransactionResponseDTO transferMoneyBetweenAccounts(String fromAccount, String toAccount, double amount, String transactionDescription, Long loginEntryId) throws InsufficientFundsException, PotentialFraudException {
        Account senderAccount = accountService.getAccountByAccountNumberDAO(fromAccount);
        Account receiverAccount = accountService.getAccountByAccountNumberDAO(toAccount);
        LoginEntry loginEntry = loginEntryService.getLoginEntryById(loginEntryId);

        if(potentialFraudService.existsByLoginEntry(loginEntry)) {
            PotentialFraud potentialFraud = potentialFraudService.getPotentialFraudByLoginEntryId(loginEntryId);
            if(potentialFraud.isFraudSuspect())
                throw new PotentialFraudException("The transaction could not be processed due to security reasons!");
        }

        if(senderAccount.getBalance() < amount)
            throw new InsufficientFundsException("You have insufficient funds to proceed with this transaction!");
        else {
            senderAccount.setBalance(senderAccount.getBalance() - amount);
            receiverAccount.setBalance(receiverAccount.getBalance() + amount);

            accountService.save(senderAccount);
            accountService.save(receiverAccount);
            Ledger ledger = new Ledger(fromAccount, toAccount, amount, transactionDescription, LocalDateTime.now(), loginEntry);

            ledgerRepository.save(ledger);
            return new TransactionResponseDTO(
                    ledger.getTransactionId(),
                    ledger.getFromAccount(),
                    ledger.getToAccount(),
                    ledger.getAmount(),
                    ledger.getDescription(),
                    ledger.getDateTime(),
                    ledger.getLoginEntry().getId()
            );
        }
    }

    public List<TransactionResponseDTO> getTransactionListByClientId(String accountNumber) {
        List<Ledger> transactionList = ledgerRepository.getLedgerByFromAccount(accountNumber);
        transactionList.addAll(ledgerRepository.getLedgerByToAccount(accountNumber));
        Collections.sort(transactionList);

        List<TransactionResponseDTO> transactionResponseDTOList = new ArrayList<>();
        transactionList.forEach(element ->{
            transactionResponseDTOList.add(new TransactionResponseDTO(element.getTransactionId(),
                    element.getFromAccount(),
                    element.getToAccount(),
                    element.getAmount(),
                    element.getDescription(),
                    element.getDateTime()));
        });
        return transactionResponseDTOList;
    }

    public List<TransactionResponseDTO> getAllTransactions() {
        List<Ledger> ledgerList = (List<Ledger>) ledgerRepository.findAll();

        List<TransactionResponseDTO> transactionResponseDTOList = new ArrayList<>();

        ledgerList.forEach(element->{transactionResponseDTOList.add(new TransactionResponseDTO(element.getTransactionId(),
                element.getFromAccount(), element.getToAccount(), element.getAmount(), element.getDescription(), element.getDateTime()));});
        return transactionResponseDTOList;
    }

    public boolean deleteTransaction(Long id) {
        if(ledgerRepository.existsById(id)) {
            ledgerRepository.delete(ledgerRepository.getLedgerByTransactionId(id));
            return true;
        }
        else
            return false;
    }
}
