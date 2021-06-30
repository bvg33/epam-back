package com.epam.tr.dto;

import java.util.Objects;

public class FileDto {

    private FileDtoType fileDtoType;
    private String name;
    private String path;
    private long size;

    public FileDto(FileDtoType fileDtoType, String name, String path, long size) {
        this.fileDtoType = fileDtoType;
        this.name = name;
        this.path = path;
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public FileDtoType getType() {
        return fileDtoType;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileDto that = (FileDto) o;
        return Objects.equals(name, that.name) && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileDtoType, name, path);
    }

    public enum FileDtoType {
        FILE, FOLDER, DRIVE
    }
}
