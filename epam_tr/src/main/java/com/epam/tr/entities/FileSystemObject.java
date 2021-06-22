package com.epam.tr.entities;

import java.util.Objects;

public class FileSystemObject {

    private FileSystemObjectType fileSystemObjectType;
    private String name;
    private String path;
    private long size;

    public FileSystemObject(FileSystemObjectType fileSystemObjectType, String name, String path, long size) {
        this.fileSystemObjectType = fileSystemObjectType;
        this.name = name;
        this.path = path;
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public FileSystemObjectType getType() {
        return fileSystemObjectType;
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
        FileSystemObject that = (FileSystemObject) o;
        return fileSystemObjectType == this.fileSystemObjectType && Objects.equals(name, that.name) && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileSystemObjectType, name, path);
    }
}
