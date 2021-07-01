package com.epam.tr.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class FileRequestDto {
    @NotNull
    private List<String> folder;
    @NotNull
    @Size(min=1)
    private String drive;
    @Pattern(regexp = "SortBy\\w{4}_\\w{3,4}")
    private String sorterParameter;
    @Pattern(regexp = "\\w+")
    private String mask;
    @Size(min=4,max=6)
    private String type;
    @Size(min=2,max=15)
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

    public String getSorterParameter() {
        return sorterParameter;
    }

    public void setSorterParameter(String sorterParameter) {
        this.sorterParameter = sorterParameter;
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
