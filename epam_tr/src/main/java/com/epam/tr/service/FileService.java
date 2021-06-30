package com.epam.tr.service;

import com.epam.tr.dto.FileDto;
import com.epam.tr.exceptions.InvalidCredentialsException;
import com.epam.tr.exceptions.InvalidFileException;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<FileDto> readFileByPath(String path);

    List<FileDto> filter(String path, String parameter);

    List<FileDto> search(String path, String mask);

    void create(String drive, MultiValueMap<String, String> allRequestParams) throws InvalidFileException, InvalidCredentialsException, IOException;

    void update(FileDto oldEntity, FileDto newEntity) throws InvalidFileException, InvalidCredentialsException, IOException;

    void delete(String drive, MultiValueMap<String, String> allRequestParams) throws InvalidFileException, InvalidCredentialsException, IOException;
}
