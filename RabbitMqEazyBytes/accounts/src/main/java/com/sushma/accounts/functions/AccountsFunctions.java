package com.sushma.accounts.functions;

import com.sushma.accounts.service.IAccountsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

@Configuration
public class AccountsFunctions {

    private static final Logger log = LoggerFactory.getLogger(AccountsFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication(IAccountsService accountsService){
        return acno -> {
            log.info("Updating Communication status for the account number : " + acno.toString());
            accountsService.updateCommunicationStatus(acno);
        };

    }
}
