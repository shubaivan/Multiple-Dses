package com.example.master_card.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
public class CardController {

    @PostMapping("/mc")
    public Map<String, String> hello() throws IOException {

        HashMap<String, String> map = new HashMap<>();
        map.put("version", "2.2");

        return map;
    }


}
