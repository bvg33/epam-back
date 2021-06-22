package com.epam.tr.service.logic.validator;

import com.epam.tr.entities.FileSystemObject;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class FileValidator implements Validator<FileSystemObject> {

    @Override
    public boolean isValid(FileSystemObject entity) {
        return nonNull(entity.getName()) && nonNull(entity.getPath()) && nonNull(entity.getType());
    }
}
