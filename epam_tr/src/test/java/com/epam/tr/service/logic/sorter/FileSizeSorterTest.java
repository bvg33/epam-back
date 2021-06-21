package com.epam.tr.service.logic.sorter;

import com.epam.tr.entities.FileEntity;
import com.epam.tr.entities.FileType;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSizeSorterTest {

    private final FileSizeSorter sorter = new FileSizeSorter();

    @Test
    public void testCompare() {
        FileEntity firstEntity = new FileEntity(FileType.FILE, "name1", "path", 1);
        FileEntity secondEntity = new FileEntity(FileType.FILE, "name2", "path", 2);
        FileEntity thirdEntity = new FileEntity(FileType.FILE, "name2", "path", 2);
        FileEntity fourthEntity = new FileEntity(FileType.FILE,"name","path",0);

        assertEquals(-1,sorter.compare(firstEntity,secondEntity));
        assertEquals(0,sorter.compare(secondEntity,thirdEntity));
        assertEquals(1,sorter.compare(firstEntity,fourthEntity));
    }
}
