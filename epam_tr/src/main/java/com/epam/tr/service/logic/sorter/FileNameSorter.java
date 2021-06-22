package com.epam.tr.service.logic.sorter;

import com.epam.tr.entities.FileSystemObject;

import java.util.Comparator;

public class FileNameSorter implements Comparator<FileSystemObject> {

    @Override
    public int compare(FileSystemObject o1, FileSystemObject o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
