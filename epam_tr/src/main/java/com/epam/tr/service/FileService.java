package com.epam.tr.service;

import com.epam.tr.entities.FileSystemObject;

import java.util.List;

public interface FileService extends Service<FileSystemObject> {
    List<FileSystemObject> readFileByPath(String path);

    List<FileSystemObject> filter(String path, String parameter, String sortType);

    List<FileSystemObject> search(String path, String mask);
}
