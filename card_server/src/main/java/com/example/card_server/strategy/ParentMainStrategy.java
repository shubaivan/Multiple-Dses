package com.example.card_server.strategy;

import com.example.card_server.service.ObjectHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
abstract class ParentMainStrategy implements MainStrategy{
    @Autowired
    protected ObjectHandler objectHandler;
}
