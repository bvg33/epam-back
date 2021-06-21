package com.epam.tr.entities;

import java.util.Objects;

public class FileEntity {

    private FileType fileType;
    private String name;
    private String path;
    private long size;

    public FileEntity(FileType fileType, String name, String path,long size) {
        this.fileType = fileType;
        this.name = name;
        this.path = path;
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public FileType getType() {
        return fileType;
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
        FileEntity that = (FileEntity) o;
        return fileType == this.fileType && Objects.equals(name, that.name) && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileType, name, path);
    }
}
