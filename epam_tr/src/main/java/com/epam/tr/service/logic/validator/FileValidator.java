package com.epam.tr.service.logic.validator;

import com.epam.tr.dto.FileDto;
import org.springframework.stereotype.Component;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Objects.nonNull;

@Component
public class FileValidator implements Validator<FileDto> {

    @Override
    public boolean isValid(FileDto entity) {
        return !isNullOrEmpty(entity.getName())&& !isNullOrEmpty(entity.getPath()) && nonNull(entity.getType());
    }
}
