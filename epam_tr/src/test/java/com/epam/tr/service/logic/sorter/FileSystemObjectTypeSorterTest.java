package com.epam.tr.service.logic.sorter;

import com.epam.tr.entities.FileSystemObject;
import com.epam.tr.entities.FileSystemObjectType;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSystemObjectTypeSorterTest {

    private final FileTypeSorter sorter = new FileTypeSorter();

    @Test
    public void testCompare() {
        FileSystemObject firstEntity = new FileSystemObject(FileSystemObjectType.FOLDER, "name1", "path", 0);
        FileSystemObject secondEntity = new FileSystemObject(FileSystemObjectType.FILE, "name2", "path", 0);
        FileSystemObject thirdEntity = new FileSystemObject(FileSystemObjectType.FILE, "name2", "path", 0);
        FileSystemObject fourthEntity = new FileSystemObject(FileSystemObjectType.FILE,"name","path",0);

        assertEquals(-1,sorter.compare(secondEntity,firstEntity));
        assertEquals(0,sorter.compare(secondEntity,thirdEntity));
        assertEquals(1,sorter.compare(firstEntity,fourthEntity));
    }
}
