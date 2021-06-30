package com.epam.tr.service.logic.sorter;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SorterFactoryTest {

    @Test
    public void testCreateSorter() {
        FileNameSorter fileNameSorter = (FileNameSorter) SorterFactory.create("sortByName");
        FileSizeSorter fileSizeSorter = (FileSizeSorter) SorterFactory.create("sortBySize");
        FileTypeSorter fileTypeSorter = (FileTypeSorter) SorterFactory.create("sortByType");

        assertEquals(FileNameSorter.class,fileNameSorter.getClass());
        assertEquals(FileSizeSorter.class,fileSizeSorter.getClass());
        assertEquals(FileTypeSorter.class,fileTypeSorter.getClass());
    }
}
