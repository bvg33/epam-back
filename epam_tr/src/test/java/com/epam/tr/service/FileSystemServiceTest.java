package com.epam.tr.service;

import com.epam.tr.entities.FileEntity;
import com.epam.tr.entities.FileType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FileSystemServiceTest {

    private final FileSystemService service = new FileSystemService();

    @Test
    public void testReadFileByPathWhenPathExist() {
        String path = "C:\\Users\\Dima\\IdeaProjects\\epam_tr\\src\\main\\webapp\\WEB-INF";
        List<FileEntity> expected = Arrays.asList(new FileEntity(FileType.FILE, "web.xml", path + "\\web.xml", 0));

        List<FileEntity> actual = service.readFileByPath(path);

        assertEquals(expected, actual);
    }

    @Test
    public void testReadFileByPathWhenPathDontExist() {
        String path = "C:\\Users\\D";
        List<FileEntity> expected = new ArrayList<>();

        List<FileEntity> actual = service.readFileByPath(path);

        assertEquals(expected, actual);
    }
}
