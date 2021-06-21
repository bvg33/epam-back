package com.epam.tr.service.logic.builder;

import com.epam.tr.entities.FileEntity;
import com.epam.tr.entities.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;

import static com.epam.tr.entities.FileType.FILE;
import static com.epam.tr.entities.FileType.FOLDER;

public class FileEntityBuilder {
    private static final String FILE_STRING = "file";
    private static final String TYPE = "type";
    private static final int FILE_SIZE = 0;
    private static final String NAME = "name";
    @Autowired
    private PathBuilder builder;

    public FileEntity buildFileEntity(String drive, MultiValueMap<String, String> allRequestParams) {
        String path = builder.createPath(drive, allRequestParams);
        String stringFileType = allRequestParams.getFirst(TYPE);
        FileType type = (stringFileType.equals(FILE_STRING)) ? FILE : FOLDER;
        String name = allRequestParams.getFirst(NAME);
        return new FileEntity(type, name, path, FILE_SIZE);
    }
}
