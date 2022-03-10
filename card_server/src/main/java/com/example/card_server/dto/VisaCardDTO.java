package com.example.card_server.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class VisaCardDTO extends ParentCardDTO {

    @NotNull
    @NotBlank
    private String dsMinVersion;

    @NotNull
    @NotBlank
    private String dsMaxVersion;

    @NotNull
    @NotBlank
    private String acsMinVersion;

    @NotNull
    @NotBlank
    private String acsMaxVersion;

    public String getDsMinVersion() {
        return dsMinVersion;
    }

    public void setDsMinVersion(String dsMinVersion) {
        this.dsMinVersion = dsMinVersion;
    }

    public String getDsMaxVersion() {
        return dsMaxVersion;
    }

    public void setDsMaxVersion(String dsMaxVersion) {
        this.dsMaxVersion = dsMaxVersion;
    }

    public String getAcsMinVersion() {
        return acsMinVersion;
    }

    public void setAcsMinVersion(String acsMinVersion) {
        this.acsMinVersion = acsMinVersion;
    }

    public String getAcsMaxVersion() {
        return acsMaxVersion;
    }

    public void setAcsMaxVersion(String acsMaxVersion) {
        this.acsMaxVersion = acsMaxVersion;
    }

    @Override
    public List<String> fetchVersions() {
        List<String> versions = new ArrayList<>();
        versions.add(this.dsMinVersion);
        versions.add(this.dsMaxVersion);
        versions.add(this.acsMinVersion);
        versions.add(this.acsMaxVersion);

        return versions;
    }
}
