package com.epam.tr.service.logic.sorter;

import com.epam.tr.entities.FileSystemObject;

import java.util.Comparator;

public class FileSizeSorter implements Comparator<FileSystemObject> {

    @Override
    public int compare(FileSystemObject o1, FileSystemObject o2) {
        return Long.compare(o1.getSize(), o2.getSize());
    }
}
