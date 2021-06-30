package com.epam.tr.service;

import com.epam.tr.dto.FileDto;
import com.epam.tr.dto.FileRequestDto;
import com.epam.tr.exceptions.InvalidCredentialsException;
import com.epam.tr.exceptions.InvalidFileException;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<FileDto> readByPath(FileRequestDto requestDt);

    List<FileDto> filter(FileRequestDto requestDt);

    List<FileDto> search(FileRequestDto requestDt);

    void create(FileRequestDto requestDto) throws IOException;

    void delete(FileRequestDto requestDto) throws IOException;
}
