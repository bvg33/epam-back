package com.epam.tr.service.logic.sorter;

import com.epam.tr.entities.FileEntity;

import java.util.Comparator;

public class FileSizeSorter implements Comparator<FileEntity> {

    @Override
    public int compare(FileEntity o1, FileEntity o2) {
        return Long.compare(o1.getSize(),o2.getSize());
    }
}
