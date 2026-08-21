package com.sushma.gatewayserver.filter;


import org.springframework.stereotype.Component;

import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class FilterUtility {

    public static final String CORRELATION_ID = "eazybank_correlation_id";

    public String getCorrelationId(HttpHeaders requestHeader){
        if(requestHeader.get(CORRELATION_ID) != null) {
            List<String> requestHeadersList = requestHeader.get(CORRELATION_ID);
            return requestHeadersList.stream().findFirst().get();
        }else{
            return null;
        }
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value){
        return exchange.mutate().request(exchange.getRequest().mutate().header(name,value).build()).build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String value){
        return this.setRequestHeader(exchange, CORRELATION_ID, value);
    }
}
