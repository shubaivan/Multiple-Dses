package com.example.card_server.service;

import com.example.card_server.dto.CardDTOInterface;
import com.example.card_server.helper.CardType;
import com.example.card_server.strategy.MainStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CardService {
    @Autowired
    @Qualifier("cardStrategyMap")
    private Map<CardType, MainStrategy> map;

    public CardDTOInterface analysisCard(String acctNumber) throws Exception {
        CardType cardType = CardType.detect(acctNumber);
        MainStrategy mainStrategy = map.get(cardType);

        return mainStrategy.fetchData(acctNumber);
    }
}
