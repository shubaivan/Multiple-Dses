package com.example.card_server.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

public class MasterCardDTO extends ParentCardDTO {

    @NotNull
    @NotBlank
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public List<String> fetchVersions() {
        List<String> versions = new ArrayList<>();
        versions.add(this.version);

        return versions;
    }
}
