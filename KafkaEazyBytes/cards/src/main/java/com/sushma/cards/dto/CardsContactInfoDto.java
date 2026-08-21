package com.sushma.cards.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "cards")
@Setter @Getter
public class CardsContactInfoDto {
    public String message;
    public Map<String, String> contactDetails;
    public List<String> onCallSupport;
}
