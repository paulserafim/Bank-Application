package com.ing.tech.service;

import com.ing.tech.model.Account;
import com.ing.tech.model.dto.AccountRequestDTO;
import com.ing.tech.model.dto.AccountResponseDTO;
import com.ing.tech.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public AccountResponseDTO getAccountByAccountNumber(String accountNumber) {
        Account account = accountRepository.getAccountByNumber(accountNumber);
        return new AccountResponseDTO(
                account.getNumber(),
                account.getBalance(),
                account.getClient().getId()
        );
    }

    public Account getAccountByAccountNumberDAO(String accountNumber) {
        return accountRepository.getAccountByNumber(accountNumber);
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public AccountResponseDTO update(AccountRequestDTO accountRequestDTO, String id) {
        Account accountToUpdate = accountRepository.getAccountByNumber(id);

        accountToUpdate.setNumber(accountRequestDTO.getNumber());
        accountToUpdate.setBalance(accountRequestDTO.getBalance());

        Account updatedAccount = accountRepository.save(accountToUpdate);

        return new AccountResponseDTO(
                updatedAccount.getNumber(),
                updatedAccount.getBalance()
        );
    }

    public Set<AccountResponseDTO> getAllAccounts() {
        Iterable<Account> accounts = accountRepository.findAll();
        Set<AccountResponseDTO> accountResponseDTOSet = new HashSet<>();
        Iterator<Account> accountIterator = accounts.iterator();
        while(accountIterator.hasNext()) {
            Account account = accountIterator.next();
            accountResponseDTOSet.add(
                    new AccountResponseDTO(
                            account.getNumber(),
                            account.getBalance(),
                            account.getClient().getId()
                    )
            );
        }
        return accountResponseDTOSet;
    }
}
