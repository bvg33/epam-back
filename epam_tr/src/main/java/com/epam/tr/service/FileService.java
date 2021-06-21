package com.epam.tr.service;

import com.epam.tr.entities.FileEntity;

import java.util.List;

public interface FileService extends Service<FileEntity> {
    List<FileEntity> readFileByPath(String path);
    List<FileEntity>  filter(String path,String parameter,String sortType);
    List<FileEntity>  search (String path,String mask);
}
