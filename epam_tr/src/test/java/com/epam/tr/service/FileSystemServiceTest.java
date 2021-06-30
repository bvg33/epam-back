package com.epam.tr.service;

import com.epam.tr.dto.FileDto;
import com.epam.tr.dto.FileRequestDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.epam.tr.dto.FileDto.FileSystemObjectType.FILE;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class FileSystemServiceTest {

    private final FileSystemService service = new FileSystemService();

    @Test
    public void testReadFileByPathWhenPathExist() {
        String path = "C:\\Users\\Dima\\IdeaProjects\\epam_tr\\src\\main\\webapp\\WEB-INF";
        List<FileDto> expected = Arrays.asList(new FileDto(FILE, "web.xml", path + "\\web.xml", 0));

        List<FileDto> actual = service.readByPath(new FileRequestDto());

        assertEquals(expected, actual);
    }

    @Test
    public void testReadFileByPathWhenPathDontExist() {
        String path = "C:\\Users\\D";
        List<FileDto> expected = new ArrayList<>();

        List<FileDto> actual = service.readByPath(new FileRequestDto());

        assertEquals(expected, actual);
    }
}
