package com.epam.tr.service.logic.sorter;

import com.epam.tr.dto.FileDto;

import java.util.Comparator;

public class FileSizeSorter implements Comparator<FileDto> {

    @Override
    public int compare(FileDto o1, FileDto o2) {
        return Long.compare(o1.getSize(), o2.getSize());
    }
}
