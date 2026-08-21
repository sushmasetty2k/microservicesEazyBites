package com.sushma.accounts.service;

import com.sushma.accounts.dto.CustomerDetailsDto;

public interface ICustomerDetailsService {

    public CustomerDetailsDto getCustomerDetails(String correlationId, String mobileNumber);
}
