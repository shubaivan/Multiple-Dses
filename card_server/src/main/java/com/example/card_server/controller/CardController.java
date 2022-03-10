package com.example.card_server.controller;

import com.example.card_server.dto.CardDTOInterface;
import com.example.card_server.dto.MainRequestDTO;
import com.example.card_server.helper.CardType;
import com.example.card_server.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
public class CardController {

    private CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/card_gate")
    public Map<String, String> cardGateAction(
            @Valid @RequestBody MainRequestDTO mainRequestDTO
    ) throws Exception {

        CardDTOInterface cardDTOInterface = this.cardService.analysisCard(mainRequestDTO.getAcctNumber());

        HashMap<String, String> map = new HashMap<>();
        String formatted = "masked {{acctNumber}} PCI rules".replace("{{acctNumber}}", mainRequestDTO.getAcctNumber());
        map.put("cardId", formatted);
        map.put("version", cardDTOInterface.maxVersion());

        return map;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
