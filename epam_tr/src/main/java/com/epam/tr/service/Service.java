package com.epam.tr.service;

import com.epam.tr.exceptions.InvalidCredentialsException;
import com.epam.tr.exceptions.InvalidFileException;

public interface Service<T> {
    void create(T Entity) throws InvalidFileException, InvalidCredentialsException;

    void update(T oldEntity, T newEntity) throws InvalidFileException, InvalidCredentialsException;

    void delete(T entity) throws InvalidFileException, InvalidCredentialsException;
}
