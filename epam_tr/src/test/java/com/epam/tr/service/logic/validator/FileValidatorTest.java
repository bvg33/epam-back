package com.epam.tr.service.logic.validator;

import com.epam.tr.dto.FileDto;
import com.epam.tr.entities.FileSystemObjectType;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileValidatorTest {

    private final FileValidator validator = new FileValidator();

    @Test
    public void testIsValidWhenValid() {
        boolean actual = validator.isValid(new FileDto(FileSystemObjectType.FILE,"name","path",0));

        assertTrue(actual);
    }

    @Test
    public void testIsValidWhenNotValid(){
        boolean actual = validator.isValid(new FileDto(FileSystemObjectType.FILE,null,"path",0));

        assertFalse(actual);
    }
}
