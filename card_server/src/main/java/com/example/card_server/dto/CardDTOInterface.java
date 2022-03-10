package com.example.card_server.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface CardDTOInterface {
    public List<String> fetchVersions();
    public String maxVersion();
}
