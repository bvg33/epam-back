package com.epam.tr.service.logic.validator;

import com.epam.tr.dto.FileDto;
import com.epam.tr.exceptions.InvalidFileException;
import org.springframework.stereotype.Component;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Objects.nonNull;

@Component
public class FileValidator implements Validator<FileDto> {

    @Override
    public boolean isValid(FileDto entity) {
        if(!(!isNullOrEmpty(entity.getName()) && !isNullOrEmpty(entity.getPath()) && nonNull(entity.getType()))){
            String message = String.format("FileDto %s is not valid",entity.toString());
            throw new InvalidFileException(message);
        }
        return true;
    }
}
