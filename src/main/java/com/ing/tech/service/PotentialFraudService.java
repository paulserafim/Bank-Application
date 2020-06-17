package com.ing.tech.service;

import com.ing.tech.model.LoginEntry;
import com.ing.tech.model.PotentialFraud;
import com.ing.tech.model.dto.LocationResponseDTO;
import com.ing.tech.model.dto.PotentialFraudRequestDTO;
import com.ing.tech.model.dto.PotentialFraudResponseDTO;
import com.ing.tech.repository.PotentialFraudRepository;
import org.springframework.stereotype.Service;

@Service
public class PotentialFraudService {
    private PotentialFraudRepository potentialFraudRepository;
    private LoginEntryService loginEntryService;

    public PotentialFraudService(PotentialFraudRepository potentialFraudRepository, LoginEntryService loginEntryService) {
        this.potentialFraudRepository = potentialFraudRepository;
        this.loginEntryService = loginEntryService;
    }


    public PotentialFraudResponseDTO save(PotentialFraudRequestDTO potentialFraudRequestDTO) {
        PotentialFraud potentialFraud = potentialFraudRepository.save(new PotentialFraud(
                loginEntryService.getLoginEntryById(potentialFraudRequestDTO.getLoginEntryId()),
                potentialFraudRequestDTO.getFirstLocation(),
                potentialFraudRequestDTO.getSecondLocation(),
                potentialFraudRequestDTO.getEstimatedArrivalTime(),
                potentialFraudRequestDTO.getActualArrivalTime(),
                potentialFraudRequestDTO.isFraudSuspect()));

        return new PotentialFraudResponseDTO(
                potentialFraud.getId(),
                potentialFraud.getLoginEntry().getId(),
                new LocationResponseDTO(
                        potentialFraud.getFirstLocation().getId(),
                        potentialFraud.getFirstLocation().getLatCoordinate(),
                        potentialFraud.getFirstLocation().getLongCoordinate(),
                        potentialFraud.getFirstLocation().getAddress()
                ),
                new LocationResponseDTO(
                        potentialFraud.getSecondLocation().getId(),
                        potentialFraud.getSecondLocation().getLatCoordinate(),
                        potentialFraud.getSecondLocation().getLongCoordinate(),
                        potentialFraud.getSecondLocation().getAddress()
                ),
                potentialFraud.getEstimatedArrivalTime(),
                potentialFraud.getActualArrivalTime(),
                potentialFraud.isFraudSuspect());
    }



    public void save(PotentialFraud potentialFraud) {
        potentialFraudRepository.save(potentialFraud);
    }

    public PotentialFraud getPotentialFraudByLoginEntryId(Long loginEntryId) {
        LoginEntry loginEntry = loginEntryService.getLoginEntryById(loginEntryId);
        return potentialFraudRepository.getPotentialFraudByLoginEntry(loginEntry);
    }

    public PotentialFraudResponseDTO getPotentialFraudById(Long id) {
        PotentialFraud potentialFraud = potentialFraudRepository.getPotentialFraudById(id);
        return new PotentialFraudResponseDTO(
                potentialFraud.getId(),
                potentialFraud.getLoginEntry().getId(),
                new LocationResponseDTO(
                        potentialFraud.getFirstLocation().getId(),
                        potentialFraud.getFirstLocation().getLatCoordinate(),
                        potentialFraud.getFirstLocation().getLongCoordinate(),
                        potentialFraud.getFirstLocation().getAddress()
                ),
                new LocationResponseDTO(
                        potentialFraud.getSecondLocation().getId(),
                        potentialFraud.getSecondLocation().getLatCoordinate(),
                        potentialFraud.getSecondLocation().getLongCoordinate(),
                        potentialFraud.getSecondLocation().getAddress()
                ),
                potentialFraud.getEstimatedArrivalTime(),
                potentialFraud.getActualArrivalTime(),
                potentialFraud.isFraudSuspect()

        );
    }

    public boolean deletePotentialFraud(Long id) {
        if(potentialFraudRepository.existsById(id)) {
            potentialFraudRepository.delete(potentialFraudRepository.getPotentialFraudById(id));
            return true;
        }
        else
            return false;
    }

    public boolean existsByLoginEntry (LoginEntry loginEntry) {
        return potentialFraudRepository.existsByLoginEntry(loginEntry);
    }

}
