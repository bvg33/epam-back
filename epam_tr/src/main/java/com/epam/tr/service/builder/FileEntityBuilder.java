package com.epam.tr.service.builder;

import com.epam.tr.dto.FileDto;
import com.epam.tr.dto.FileRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.epam.tr.dto.FileDto.FileDtoType.FILE;
import static com.epam.tr.dto.FileDto.FileDtoType.FOLDER;

@Component
public class FileEntityBuilder {

    @Autowired
    private PathBuilder builder;

    private static final String FILE_STRING = "file";
    private static final int FILE_SIZE = 0;

    public FileDto buildFileEntity(FileRequestDto requestDto) {
        String path = builder.createPath(requestDto);
        String stringFileType = requestDto.getType();
        FileDto.FileDtoType type = (stringFileType.equals(FILE_STRING)) ? FILE : FOLDER;
        String name = requestDto.getName();
        return new FileDto(type, name, path, FILE_SIZE);
    }
}
