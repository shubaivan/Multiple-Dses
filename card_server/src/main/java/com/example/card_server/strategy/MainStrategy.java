package com.example.card_server.strategy;

import com.example.card_server.dto.CardDTOInterface;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface MainStrategy {

    CardDTOInterface fetchData(String acctNumber) throws IOException;
}
