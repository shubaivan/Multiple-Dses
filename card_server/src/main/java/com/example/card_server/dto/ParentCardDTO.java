package com.example.card_server.dto;

import java.util.Collections;

abstract class ParentCardDTO implements CardDTOInterface {

    public String maxVersion()
    {
        return Collections.max(fetchVersions());
    }
}
