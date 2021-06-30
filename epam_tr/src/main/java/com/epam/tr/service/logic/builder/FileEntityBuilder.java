package com.epam.tr.service.logic.builder;

import com.epam.tr.dto.FileDto;
import com.epam.tr.dto.FileRequestDto;
import com.epam.tr.entities.FileSystemObjectType;
import org.osgi.service.component.annotations.Component;
import org.springframework.beans.factory.annotation.Autowired;

import static com.epam.tr.entities.FileSystemObjectType.FILE;
import static com.epam.tr.entities.FileSystemObjectType.FOLDER;

@Component
public class FileEntityBuilder {

    @Autowired
    private PathBuilder builder;

    private static final String FILE_STRING = "file";
    private static final int FILE_SIZE = 0;

    public FileDto buildFileEntity(FileRequestDto requestDto) {
        String path = builder.createPath(requestDto);
        String stringFileType = requestDto.getType();
        FileSystemObjectType type = (stringFileType.equals(FILE_STRING)) ? FILE : FOLDER;
        String name = requestDto.getName();
        return new FileDto(type, name, path, FILE_SIZE);
    }
}
