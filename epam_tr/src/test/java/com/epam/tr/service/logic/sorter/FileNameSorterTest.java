package com.epam.tr.service.logic.sorter;

import com.epam.tr.entities.FileSystemObject;
import com.epam.tr.entities.FileSystemObjectType;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileNameSorterTest {

    private final FileNameSorter sorter = new FileNameSorter();

    @Test
    public void testCompareDifferentEntitiesWhereFirstSmaller() {
        FileSystemObject firstEntity = new FileSystemObject(FileSystemObjectType.FILE, "name1", "path", 0);
        FileSystemObject secondEntity = new FileSystemObject(FileSystemObjectType.FILE, "name2", "path", 0);

        assertEquals(-1, sorter.compare(firstEntity, secondEntity));

    }

    @Test
    public void testCompareSameEntities() {
        FileSystemObject secondEntity = new FileSystemObject(FileSystemObjectType.FILE, "name2", "path", 0);
        FileSystemObject thirdEntity = new FileSystemObject(FileSystemObjectType.FILE, "name2", "path", 0);

        assertEquals(0, sorter.compare(secondEntity, thirdEntity));
    }

    @Test
    public void testCompareDifferentEntitiesWhereFirstIsBigger() {
        FileSystemObject fourthEntity = new FileSystemObject(FileSystemObjectType.FILE, "name", "path", 0);
        FileSystemObject firstEntity = new FileSystemObject(FileSystemObjectType.FILE, "name1", "path", 0);

        assertEquals(1, sorter.compare(firstEntity, fourthEntity));
    }
}
