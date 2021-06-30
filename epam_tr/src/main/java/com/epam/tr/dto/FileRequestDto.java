package com.epam.tr.dto;

import java.util.List;

public class FileRequestDto {
    private List<String> folder;
    private String drive;
    private String parameter;
    private String mask;
    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileRequestDto() {
    }

    public List<String> getFolder() {
        return folder;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public void setFolder(List<String> folder) {
        this.folder = folder;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }
}
