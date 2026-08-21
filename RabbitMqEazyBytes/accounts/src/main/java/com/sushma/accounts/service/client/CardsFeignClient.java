package com.sushma.accounts.service.client;

import com.sushma.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cards", fallback = CardsFallBack.class)
public interface CardsFeignClient {

    @GetMapping(value = "api/fetch",consumes = "application/json")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestHeader(value = "eazybank_correlation_id") String correlationId,
                                                     @RequestParam String mobileNumber);
}
