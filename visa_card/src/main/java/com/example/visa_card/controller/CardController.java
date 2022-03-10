package com.example.visa_card.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
public class CardController {

    @PostMapping("/vc")
    public Map<String, String> hello() throws IOException {

        HashMap<String, String> map = new HashMap<>();

        map.put("dsMinVersion", "2.0");
        map.put("dsMaxVersion", "2.2");
        map.put("acsMinVersion", "2.1");
        map.put("acsMaxVersion", "2.3");

        return map;
    }


}
