package com.sushma.accounts.service.impl;

import com.sushma.accounts.dto.AccountsDto;
import com.sushma.accounts.dto.CardsDto;
import com.sushma.accounts.dto.CustomerDetailsDto;
import com.sushma.accounts.dto.CustomerDto;
import com.sushma.accounts.dto.LoansDto;
import com.sushma.accounts.exception.ResourceNotFoundException;
import com.sushma.accounts.mapper.AccountsMapper;
import com.sushma.accounts.mapper.CustomerMapper;
import com.sushma.accounts.model.Accounts;
import com.sushma.accounts.model.Customer;
import com.sushma.accounts.repository.AccountsRepo;
import com.sushma.accounts.repository.CustomerRepo;
import com.sushma.accounts.service.ICustomerDetailsService;
import com.sushma.accounts.service.client.CardsFeignClient;
import com.sushma.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerDetailsServiceImpl implements ICustomerDetailsService {

    private final CustomerRepo customerRepo;
     private final AccountsRepo accountsRepo;
     private final CardsFeignClient cardsFeignClient;
     private final LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto getCustomerDetails(String correlationId, String mobileNumber) {
        Customer customer = customerRepo.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Accounts accounts = accountsRepo.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Accounst", "customerId", customer.getCustomerId().toString()));

        CustomerDetailsDto customerDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<CardsDto> cardsDto = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        ResponseEntity<LoansDto> loansDto = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);

        if (null != cardsDto){
            customerDto.setCardsDto(cardsDto.getBody());
        }

        if(null != loansDto){
            customerDto.setLoansDto(loansDto.getBody());
        }

        return customerDto;

    }
}
