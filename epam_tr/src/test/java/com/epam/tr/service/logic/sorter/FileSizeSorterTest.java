package com.epam.tr.service.logic.sorter;

import com.epam.tr.dto.FileDto;
import org.testng.annotations.Test;

import static com.epam.tr.dto.FileDto.FileSystemObjectType.FILE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSizeSorterTest {

    private final FileSizeSorter sorter = new FileSizeSorter();

    @Test
    public void testCompare() {
        FileDto firstEntity = new FileDto(FILE, "name1", "path", 1);
        FileDto secondEntity = new FileDto(FILE, "name2", "path", 2);
        FileDto thirdEntity = new FileDto(FILE, "name2", "path", 2);
        FileDto fourthEntity = new FileDto(FILE,"name","path",0);

        assertEquals(-1,sorter.compare(firstEntity,secondEntity));
        assertEquals(0,sorter.compare(secondEntity,thirdEntity));
        assertEquals(1,sorter.compare(firstEntity,fourthEntity));
    }
}
