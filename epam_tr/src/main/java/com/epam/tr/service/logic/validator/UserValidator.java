package com.epam.tr.service.logic.validator;

import com.epam.tr.entities.AppUser;
import com.epam.tr.exceptions.InvalidFileException;
import org.springframework.stereotype.Component;

import static com.google.common.base.Strings.isNullOrEmpty;

@Component
public class UserValidator implements Validator<AppUser> {

    @Override
    public boolean isValid(AppUser entity) {
        if(!(!isNullOrEmpty(entity.getLogin()) && !isNullOrEmpty(entity.getPassword()))) {
            String message = String.format("App user :%s is not valid",entity.toString());
            throw new InvalidFileException(message);
        }
        return true;
    }
}
