package com.epam.tr.service.logic.validator;

import com.epam.tr.dto.UserDto;
import com.epam.tr.entities.AppUser;
import org.springframework.stereotype.Component;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Objects.nonNull;

@Component
public class UserValidator implements Validator<AppUser> {

    @Override
    public boolean isValid(AppUser entity) {
        return !isNullOrEmpty(entity.getLogin()) && !isNullOrEmpty(entity.getPassword());
    }
}
