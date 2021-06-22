package com.epam.tr.service.logic.sorter;

import com.epam.tr.entities.FileSystemObject;
import com.epam.tr.entities.FileSystemObjectType;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSizeSorterTest {

    private final FileSizeSorter sorter = new FileSizeSorter();

    @Test
    public void testCompare() {
        FileSystemObject firstEntity = new FileSystemObject(FileSystemObjectType.FILE, "name1", "path", 1);
        FileSystemObject secondEntity = new FileSystemObject(FileSystemObjectType.FILE, "name2", "path", 2);
        FileSystemObject thirdEntity = new FileSystemObject(FileSystemObjectType.FILE, "name2", "path", 2);
        FileSystemObject fourthEntity = new FileSystemObject(FileSystemObjectType.FILE,"name","path",0);

        assertEquals(-1,sorter.compare(firstEntity,secondEntity));
        assertEquals(0,sorter.compare(secondEntity,thirdEntity));
        assertEquals(1,sorter.compare(firstEntity,fourthEntity));
    }
}
