package com.epam.tr.service;

import com.epam.tr.exceptions.InvalidCredentialsException;
import com.epam.tr.exceptions.WrongFileException;

public interface Service <T>{
    void create(T Entity) throws WrongFileException, InvalidCredentialsException;


    void update(T oldEntity,T newEntity) throws WrongFileException, InvalidCredentialsException;

    void delete(T entity) throws WrongFileException, InvalidCredentialsException;
}
