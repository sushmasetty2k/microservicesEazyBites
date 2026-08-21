package com.sushma.message.functions;

import com.sushma.message.dto.AccountsMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;


@Configuration
public class MessageFunctions {

    private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<AccountsMsgDto, AccountsMsgDto> email(){
        return e -> {
            log.info("Sending email with the details : " +  e.toString());
            return e;
        };
    }

    @Bean
    public Function<AccountsMsgDto, Long> sms(){
        return e -> {
            log.info("Sending sms with the details : " +  e.toString());
            return e.accountNumber();
        };
    }
}
