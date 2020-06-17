package com.ing.tech.service;

import com.ing.tech.model.Account;
import com.ing.tech.model.Client;
import com.ing.tech.model.Credentials;
import com.ing.tech.model.dto.AccountResponseDTO;
import com.ing.tech.model.dto.ClientRequestDTO;
import com.ing.tech.model.dto.ClientResponseDTO;
import com.ing.tech.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class ClientService {
    private ClientRepository clientRepository;
    private AccountService accountService;

    public ClientService(ClientRepository clientRepository, AccountService accountService) {
        this.clientRepository = clientRepository;
        this.accountService = accountService;
    }

    public ClientResponseDTO save(ClientRequestDTO client) {
        Account account = new Account(
                client.getAccount().getNumber(),
                client.getAccount().getBalance()
        );

        Credentials credentials = new Credentials(
                client.getCredentials().getAccountNumber(),
                client.getCredentials().getPassword()
        );

        Client clientToSave = new Client(
                client.getFirstName(),
                client.getLastName(),
                credentials,
                account

        );

        account.setClient(clientToSave);
        credentials.setClient(clientToSave);

        Client savedClient = clientRepository.save(clientToSave);

        return new ClientResponseDTO(
                savedClient.getId(),
                savedClient.getFirstName(),
                savedClient.getLastName(),
                new AccountResponseDTO(
                        savedClient.getAccount().getNumber(),
                        savedClient.getAccount().getBalance()
                )
        );
    }

    public ClientResponseDTO getClientById(Long id) {
        Client client = clientRepository.getClientById(id);
        Account account = accountService.getAccountByAccountNumberDAO(client.getAccount().getNumber());
        return new ClientResponseDTO(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                new AccountResponseDTO(
                        account.getNumber(),
                        account.getBalance()
                )
        );
    }

    public ClientResponseDTO getClientByAccountNumber(String accountNumber) {
        Account account = accountService.getAccountByAccountNumberDAO(accountNumber);
        Client client = clientRepository.getClientByAccount(account);
        return new ClientResponseDTO(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                new AccountResponseDTO(
                        account.getNumber(),
                        account.getBalance()
                )
        );
    }

    public void save(Client client) {
        clientRepository.save(client);
    }


    public boolean deleteClient(Long id) {
        if(clientRepository.existsById(id)) {
            Client client = clientRepository.getClientById(id);
            clientRepository.delete(client);
            return true;
        }
        else
            return false;
    }

    public Client getClientByIdDAO(Long id) {
        return clientRepository.getClientById(id);
    }

    public ClientResponseDTO update(ClientRequestDTO client, Long id) {

        Client clientToUpdate = clientRepository.getClientById(id);

        clientToUpdate.setFirstName(client.getFirstName());
        clientToUpdate.setLastName(client.getLastName());

        Client updatedClient = clientRepository.save(clientToUpdate);

        return new ClientResponseDTO(
                updatedClient.getId(),
                updatedClient.getFirstName(),
                updatedClient.getLastName(),
                new AccountResponseDTO(
                        updatedClient.getAccount().getNumber(),
                        updatedClient.getAccount().getBalance()
                )
        );

    }
}
