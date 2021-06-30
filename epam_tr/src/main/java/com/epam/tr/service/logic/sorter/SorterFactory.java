package com.epam.tr.service.logic.sorter;

import com.epam.tr.dto.FileDto;

import java.util.Comparator;

public class SorterFactory {

    private static final String SORT_BY_NAME_ASC = "sortByName_asc";
    private static final String SORT_BY_SIZE_ASC = "sortBySize_asc";
    private static final String SORT_BY_TYPE_ASC = "sortByType_asc";
    private static final String SORT_BY_NAME_DESC = "sortByName_desc";
    private static final String SORT_BY_SIZE_DESC = "sortBySize_desc";
    private static final String SORT_BY_TYPE_DESC = "sortByType_desc";

    public static Comparator<FileDto> createSorter(String sorterType) {
        switch (sorterType) {
            case SORT_BY_NAME_ASC:
                return new FileNameSorter();
            case SORT_BY_SIZE_ASC:
                return new FileSizeSorter();
            case SORT_BY_TYPE_ASC:
                return new FileTypeSorter();
            case SORT_BY_NAME_DESC:
                return new FileNameSorter().reversed();
            case SORT_BY_SIZE_DESC:
                return new FileSizeSorter().reversed();
            case SORT_BY_TYPE_DESC:
                return new FileTypeSorter().reversed();
            default: {
                String message = String.format("There is no sorter of this type : %s", sorterType);
                throw new IllegalArgumentException(message);
            }
        }
    }
}
