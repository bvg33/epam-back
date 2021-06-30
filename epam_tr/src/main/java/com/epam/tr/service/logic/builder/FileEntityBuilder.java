package com.epam.tr.service.logic.builder;

import com.epam.tr.dto.FileDto;
import com.epam.tr.entities.FileSystemObjectType;
import org.osgi.service.component.annotations.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;

import static com.epam.tr.entities.FileSystemObjectType.FILE;
import static com.epam.tr.entities.FileSystemObjectType.FOLDER;

@Component
public class FileEntityBuilder {

    @Autowired
    private PathBuilder builder;

    private static final String FILE_STRING = "file";
    private static final String TYPE = "type";
    private static final int FILE_SIZE = 0;
    private static final String NAME = "name";

    public FileDto buildFileEntity(String drive, MultiValueMap<String, String> allRequestParams) {
        String path = builder.createPath(drive, allRequestParams);
        String stringFileType = allRequestParams.getFirst(TYPE);
        FileSystemObjectType type = (stringFileType.equals(FILE_STRING)) ? FILE : FOLDER;
        String name = allRequestParams.getFirst(NAME);
        return new FileDto(type, name, path, FILE_SIZE);
    }
}
