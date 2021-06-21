package com.epam.tr.service.logic.validator;

import com.epam.tr.entities.FileEntity;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class FileValidator implements Validator<FileEntity> {

    @Override
    public boolean isValid(FileEntity entity) {
        return nonNull(entity.getName()) && nonNull(entity.getPath()) && nonNull(entity.getType());
    }
}
