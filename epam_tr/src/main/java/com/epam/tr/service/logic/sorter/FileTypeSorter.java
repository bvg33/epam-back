package com.epam.tr.service.logic.sorter;

import com.epam.tr.dto.FileDto;

import java.util.Comparator;

public class FileTypeSorter implements Comparator<FileDto> {

    @Override
    public int compare(FileDto o1, FileDto o2) {
        return o1.getType().compareTo(o2.getType());
    }
}
