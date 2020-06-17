package com.ing.tech.service;

import com.ing.tech.model.*;
import com.ing.tech.model.dto.AccountResponseDTO;
import com.ing.tech.model.dto.ClientResponseDTO;
import com.ing.tech.model.dto.CredentialsRequestDTO;
import com.ing.tech.model.dto.CredentialsResponseDTO;
import com.ing.tech.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class CredentialsService {
    private CredentialsRepository credentialsRepository;
    private ClientService clientService;
    private AccountService accountService;
    private LoginEntryService loginEntryService;
    private PotentialFraudService potentialFraudService;

    public CredentialsService (CredentialsRepository credentialsRepository, ClientService clientService, AccountService accountService, LoginEntryService loginEntryService, PotentialFraudService potentialFraudService) {
        this.credentialsRepository = credentialsRepository;
        this.clientService = clientService;
        this.accountService = accountService;
        this.loginEntryService = loginEntryService;
        this.potentialFraudService = potentialFraudService;
    }

    public boolean getLoginAccept(LoginRequest loginRequest) throws IOException {

            if(!credentialsRepository.existsByAccountNumber(loginRequest.getAccountNumber()))
                return false;

            if (loginRequest.getPassword().compareTo(
                    credentialsRepository.getCredentialsByAccountNumber(
                            loginRequest.getAccountNumber()).getPassword()) == 0
            )
            {
                Client client = accountService.getAccountByAccountNumberDAO(loginRequest.getAccountNumber()).getClient();
                LoginEntry loginEntry = new LoginEntry(client, loginRequest.getLocation(), loginRequest.getDateTime());
                loginEntry.setClient(client);

                Long loginEntryId = loginEntryService.save(loginEntry).getId();

                List<LoginEntry> lastLogins = loginEntryService.getLoginEntryByClientDAO(client);

                Collections.sort(lastLogins);
                if(lastLogins.size() > 1) {
                    PotentialFraud potentialFraud = new PotentialFraud();

                    potentialFraud.setFirstLocation(lastLogins.get(lastLogins.size() - 2).getLocation());

                    potentialFraud.setSecondLocation(lastLogins.get(lastLogins.size() - 1).getLocation());

                    LocalDateTime now = lastLogins.get(lastLogins.size() - 1).getDateTime();
                    LocalDateTime past = lastLogins.get(lastLogins.size() - 2).getDateTime();
                    Duration duration = Duration.between(now, past);
                    Long differenceInSeconds = Math.abs(duration.getSeconds());
                    potentialFraud.setActualArrivalTime(differenceInSeconds);
                    potentialFraud.setLoginEntry(loginEntry);
                    potentialFraud.checkIfSuspectOfFraud();

                    potentialFraudService.save(potentialFraud);

                }
                return true;
            }
            else
                return false;
    }

    public ClientResponseDTO getLoggedClientDetails(String accountNumber) {

        Client client = clientService.getClientByIdDAO(accountService.getAccountByAccountNumberDAO(accountNumber).getClient().getId());

        List<LoginEntry> loginEntryList = loginEntryService.getLoginEntryByClientDAO(client);
        Collections.sort(loginEntryList);
        try {
            return new ClientResponseDTO(
                    client.getId(),
                    client.getFirstName(),
                    client.getLastName(),
                    new AccountResponseDTO(
                            client.getAccount().getNumber(),
                            client.getAccount().getBalance()
                    ),
                    loginEntryList.get(loginEntryList.size() - 2).getDateTime(),
                    loginEntryList.get(loginEntryList.size() - 2).getLocation().getAddress(),
                    loginEntryList.get(loginEntryList.size() - 1).getId()
            );
        }catch (IndexOutOfBoundsException exception) {
            return new ClientResponseDTO(
                    client.getId(), client.getFirstName(),
                    client.getLastName(),
                    new AccountResponseDTO(
                            client.getAccount().getNumber(),
                            client.getAccount().getBalance()
                    ),
                    loginEntryList.get(loginEntryList.size() - 1).getId()
            );
        }

    }

    public CredentialsResponseDTO getCredentialsByAccountNumber(String accountNumber) {
        Credentials credentials = credentialsRepository.getCredentialsByAccountNumber(accountNumber);
        return new CredentialsResponseDTO(
                credentials.getId(),
                credentials.getAccountNumber()
        );
    }

    public boolean deleteCredentials(Long id) {
        if(credentialsRepository.existsById(id)) {
            credentialsRepository.delete(credentialsRepository.getCredentialsById(id));
            return true;
        }
        else
            return false;
    }

    public boolean deleteCredentials(String accountNumber) {
        if(credentialsRepository.existsByAccountNumber(accountNumber)) {
            credentialsRepository.delete(credentialsRepository.getCredentialsByAccountNumber(accountNumber));
            return true;
        }
        else
            return false;
    }

    public CredentialsResponseDTO save(CredentialsRequestDTO credentialsRequestDTO) {
        Credentials credentials = credentialsRepository.save(new Credentials(
                credentialsRequestDTO.getAccountNumber(),
                credentialsRequestDTO.getPassword()
                )
        );

        return new CredentialsResponseDTO(
                credentials.getId(),
                credentials.getAccountNumber()
        );
    }
}
