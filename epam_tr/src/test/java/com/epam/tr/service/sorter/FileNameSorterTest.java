package com.epam.tr.service.sorter;

import com.epam.tr.dto.FileDto;
import org.testng.annotations.Test;

import static com.epam.tr.dto.FileDto.FileDtoType.FILE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileNameSorterTest {

    private final FileNameSorter sorter = new FileNameSorter();

    @Test
    public void testCompareDifferentEntitiesWhereFirstSmaller() {
        FileDto firstEntity = new FileDto(FILE, "name1", "path", 0);
        FileDto secondEntity = new FileDto(FILE, "name2", "path", 0);

        assertEquals(-1, sorter.compare(firstEntity, secondEntity));

    }

    @Test
    public void testCompareSameEntities() {
        FileDto secondEntity = new FileDto(FILE, "name2", "path", 0);
        FileDto thirdEntity = new FileDto(FILE, "name2", "path", 0);

        assertEquals(0, sorter.compare(secondEntity, thirdEntity));
    }

    @Test
    public void testCompareDifferentEntitiesWhereFirstIsBigger() {
        FileDto fourthEntity = new FileDto(FILE, "name", "path", 0);
        FileDto firstEntity = new FileDto(FILE, "name1", "path", 0);

        assertEquals(1, sorter.compare(firstEntity, fourthEntity));
    }
}
