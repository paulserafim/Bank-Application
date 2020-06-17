package com.ing.tech;

import com.ing.tech.service.AccountService;
import com.ing.tech.service.ClientService;
import com.ing.tech.service.LedgerService;
import com.ing.tech.service.LoginEntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringMainApplication implements CommandLineRunner {

    private ClientService clientService;
    private LedgerService ledgerService;
    private LoginEntryService loginEntryService;
    private AccountService accountService;

    public SpringMainApplication(ClientService clientService, LedgerService ledgerService, LoginEntryService loginEntryService, AccountService accountService) {
        this.clientService = clientService;
        this.ledgerService = ledgerService;
        this.loginEntryService = loginEntryService;
        this.accountService = accountService;
    }

    public static void main(String[] args) {

        SpringApplication.run(SpringMainApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        //define some context
        //Credentials credentials = new Credentials("123456", "1234");
        //Account account = new Account("123456", 100);
        //Client client = new Client("Paul", "Serafim", 100);
        //client.setAccount(account);
        //client.setCredentials(credentials);
        //account.setClient(client);
        //clientService.save(client);

        //Credentials credentials2 = new Credentials("123457", "1235");
        //Account account2 = new Account("123457", 200);
        //Client client2 = new Client("Alex", "Serafim", 200);
        //client2.setAccount(account2);
        //client2.setCredentials(credentials2);
        //account2.setClient(client2);
        //clientService.save(client2);

        //Location firstLocation = new Location(46.672917, 27.7345347);
        //Location secondLocation = new Location(44.4144615, 26.1089237);

        //LoginEntry loginEntry = new LoginEntry(client, firstLocation, LocalDateTime.now());

        //ledgerService.transferMoneyBetweenAccounts(account.getNumber(), account2.getNumber(), 50, "Some mock description", (LoginEntryRequestDTO) loginEntry);
        //ledgerService.transferMoneyBetweenAccounts(account2.getNumber(), account.getNumber(), 30, "Other mock description");
        //ledgerService.transferMoneyBetweenAccounts(account.getNumber(), account2.getNumber(), 40, "Other mock description");


        //PotentialFraud potentialFraud = new PotentialFraud(firstLocation, secondLocation, 20L);
        //potentialFraud.checkIfSuspectOfFraud();
    }
}