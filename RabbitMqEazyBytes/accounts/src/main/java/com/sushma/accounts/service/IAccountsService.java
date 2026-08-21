package com.sushma.accounts.service;

import com.sushma.accounts.dto.CustomerDto;
import jakarta.validation.Valid;


public interface IAccountsService {
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(@Valid CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);

    boolean updateCommunicationStatus(Long accountNumber);
}
