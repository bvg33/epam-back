package com.epam.tr.service;

import com.epam.tr.entities.FileSystemObject;
import com.epam.tr.entities.FileSystemObjectType;
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
        List<FileSystemObject> expected = Arrays.asList(new FileSystemObject(FileSystemObjectType.FILE, "web.xml", path + "\\web.xml", 0));

        List<FileSystemObject> actual = service.readFileByPath(path);

        assertEquals(expected, actual);
    }

    @Test
    public void testReadFileByPathWhenPathDontExist() {
        String path = "C:\\Users\\D";
        List<FileSystemObject> expected = new ArrayList<>();

        List<FileSystemObject> actual = service.readFileByPath(path);

        assertEquals(expected, actual);
    }
}
