package com.example.card_server.configure;

import com.example.card_server.helper.CardType;
import com.example.card_server.strategy.MainStrategy;
import com.example.card_server.strategy.MasterCardStrategy;
import com.example.card_server.strategy.VisaCardStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class StrategyConfiguration {
    @Bean("cardStrategyMap")
    public Map<CardType, MainStrategy> cardStrategyMap(MainStrategy masterCardStrategy, VisaCardStrategy visaCardStrategy){
        return Map.of(
                CardType.MASTERCARD, masterCardStrategy,
                CardType.VISA, visaCardStrategy
        );
    }
}
