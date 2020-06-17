package com.ing.tech.controller;

import com.ing.tech.model.*;
import com.ing.tech.model.dto.TransactionRequestDTO;
import com.ing.tech.model.dto.TransactionResponseDTO;
import com.ing.tech.service.LedgerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/ledger")
@CrossOrigin(origins = "http://localhost:8081")

public class LedgerController {

    private LedgerService ledgerService;

    public LedgerController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    @GetMapping("/custom")
    public ResponseEntity getTransactionListByClient(@RequestParam String accountNumber) {
        List<TransactionResponseDTO> transactionResponseDTOList = ledgerService.getTransactionListByClientId(accountNumber);
        return ResponseEntity.status(HttpStatus.OK).body(transactionResponseDTOList);
    }

    @GetMapping("/all")
    public ResponseEntity getAllTransactions() {
        List<TransactionResponseDTO> transactionResponseDTOList = ledgerService.getAllTransactions();
        return ResponseEntity.status(HttpStatus.OK).body(transactionResponseDTOList);
    }

    @PostMapping("/new")
    public ResponseEntity postNewTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        TransactionResponseDTO transactionResponseDTO = null;

        try {
            transactionResponseDTO = ledgerService.transferMoneyBetweenAccounts(
                    transactionRequestDTO.getFromAccount(),
                    transactionRequestDTO.getToAccount(),
                    transactionRequestDTO.getAmount(),
                    transactionRequestDTO.getDescription(),
                    transactionRequestDTO.getLoginEntryId()
            );
        } catch (InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InsufficientFundsMessage(e.getMessage()));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InvalidUserAccountMessage("This account number does not exist"));
        } catch (PotentialFraudException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new PotentialFraudException(e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(transactionResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTransactionById(@PathVariable Long id) {
        boolean transactionExists = ledgerService.deleteTransaction(id);
        if(transactionExists)
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
