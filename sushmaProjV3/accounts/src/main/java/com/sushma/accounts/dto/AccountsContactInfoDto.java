package com.sushma.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
@Getter @Setter
public class AccountsContactInfoDto {
    public String message;
    public Map<String, String> contactDetails;
    public List<String> onCallSupport;
}
