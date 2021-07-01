package com.epam.tr.service.sorter;

import com.epam.tr.dto.FileDto;
import org.testng.annotations.Test;

import static com.epam.tr.dto.FileDto.FileDtoType.FILE;
import static com.epam.tr.dto.FileDto.FileDtoType.FOLDER;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileDtoTypeSorterTest {

    private final FileTypeSorter sorter = new FileTypeSorter();

    @Test
    public void testCompare() {
        FileDto firstEntity = new FileDto(FOLDER, "name1", "path", 0);
        FileDto secondEntity = new FileDto(FILE, "name2", "path", 0);
        FileDto thirdEntity = new FileDto(FILE, "name2", "path", 0);
        FileDto fourthEntity = new FileDto(FILE,"name","path",0);

        assertEquals(-1,sorter.compare(secondEntity,firstEntity));
        assertEquals(0,sorter.compare(secondEntity,thirdEntity));
        assertEquals(1,sorter.compare(firstEntity,fourthEntity));
    }
}
