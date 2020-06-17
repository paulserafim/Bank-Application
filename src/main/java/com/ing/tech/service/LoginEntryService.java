package com.ing.tech.service;

import com.ing.tech.model.Client;
import com.ing.tech.model.Location;
import com.ing.tech.model.LoginEntry;
import com.ing.tech.model.dto.*;
import com.ing.tech.repository.LoginEntryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class LoginEntryService {

    private LoginEntryRepository loginEntryRepository;
    private ClientService clientService;
    private LocationService locationService;

    public LoginEntryService(LoginEntryRepository loginEntryRepository, ClientService clientService, LocationService locationService) {
        this.loginEntryRepository = loginEntryRepository;
        this.clientService = clientService;
        this.locationService = locationService;

    }

    public LoginEntry save(LoginEntry loginEntry) {
        return loginEntryRepository.save(loginEntry);
    }

    public List<LoginEntry> getLoginEntryByClientDAO(Client client) {
        return loginEntryRepository.getLoginEntryByClient(client);
    }

    public LoginEntry getLoginEntryById(Long loginEntryId) {
        return loginEntryRepository.getLoginEntryById(loginEntryId);
    }

    public LoginEntryResponseDTO save(LoginEntryRequestDTO loginEntryRequestDTO) {
        Client client = clientService.getClientByIdDAO(loginEntryRequestDTO.getClient().getId());
        Location location = locationService.getLocationByIdDAO(loginEntryRequestDTO.getLocation().getId());
        LoginEntry loginEntry = loginEntryRepository.save(
                new LoginEntry(
                        client,
                        location,
                        loginEntryRequestDTO.getLoginDateTime()
                )
        );

        return new LoginEntryResponseDTO (
                loginEntry.getId(),
                new ClientResponseDTO(
                  loginEntry.getClient().getId(),
                  loginEntry.getClient().getFirstName(),
                  loginEntry.getClient().getLastName(),
                        new AccountResponseDTO(
                                loginEntry.getClient().getAccount().getNumber(),
                                loginEntry.getClient().getAccount().getBalance(),
                                loginEntry.getClient().getId()
                                )),
                new LocationResponseDTO(
                        loginEntry.getLocation().getId(),
                        loginEntry.getLocation().getLatCoordinate(),
                        loginEntry.getLocation().getLongCoordinate(),
                        loginEntry.getLocation().getAddress()
                ),
                loginEntry.getDateTime()
        );

    }

    public LoginEntryResponseDTO getLoginEntryByIdDTO(Long id) {
        LoginEntry loginEntry = this.getLoginEntryById(id);
        return new LoginEntryResponseDTO (
                loginEntry.getId(),
                new ClientResponseDTO(
                        loginEntry.getClient().getId(),
                        loginEntry.getClient().getFirstName(),
                        loginEntry.getClient().getLastName(),
                        new AccountResponseDTO(
                                loginEntry.getClient().getAccount().getNumber(),
                                loginEntry.getClient().getAccount().getBalance(),
                                loginEntry.getClient().getId()
                        )),
                new LocationResponseDTO(
                        loginEntry.getLocation().getId(),
                        loginEntry.getLocation().getLatCoordinate(),
                        loginEntry.getLocation().getLongCoordinate(),
                        loginEntry.getLocation().getAddress()
                ),
                loginEntry.getDateTime()
        );
    }

    public boolean deleteLoginEntry(Long id) {
        if(loginEntryRepository.existsById(id)) {
            loginEntryRepository.delete(loginEntryRepository.getLoginEntryById(id));
            return true;
        }
        else return false;
    }

    public Set<LoginEntryResponseDTO> getAllLoginEntries() {

        Iterable<LoginEntry> loginEntries = loginEntryRepository.findAll();
        Set<LoginEntryResponseDTO> loginEntryResponseDTOS = new HashSet<>();
        Iterator<LoginEntry> loginEntryIterator = loginEntries.iterator();
        while(loginEntryIterator.hasNext()) {
            LoginEntry loginEntry = loginEntryIterator.next();
            loginEntryResponseDTOS.add(
                    new LoginEntryResponseDTO(
                            loginEntry.getId(),
                            new ClientResponseDTO(
                                    loginEntry.getClient().getId(),
                                    loginEntry.getClient().getFirstName(),
                                    loginEntry.getClient().getLastName(),
                                    new AccountResponseDTO(
                                            loginEntry.getClient().getAccount().getNumber(),
                                            loginEntry.getClient().getAccount().getBalance()
                                    ),
                                    loginEntry.getDateTime(),
                                    loginEntry.getLocation().toString()),
                            new LocationResponseDTO(
                                    loginEntry.getLocation().getLatCoordinate(),
                                    loginEntry.getLocation().getLongCoordinate(),
                                    loginEntry.getLocation().getAddress()
                            ),
                            loginEntry.getDateTime()));
            }
            return loginEntryResponseDTOS;
    }
}
