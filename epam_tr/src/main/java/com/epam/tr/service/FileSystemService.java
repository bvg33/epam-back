package com.epam.tr.service;

import com.epam.tr.entities.FileSystemObject;
import com.epam.tr.entities.FileSystemObjectType;
import com.epam.tr.exceptions.InvalidFileException;
import com.epam.tr.service.logic.sorter.SorterFactory;
import com.epam.tr.service.logic.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.epam.tr.entities.FileSystemObjectType.*;
import static java.util.Objects.requireNonNull;

@Service
public class FileSystemService implements FileService {

    @Autowired
    private Validator<FileSystemObject> validator;

    private static final String NOT_VALID = "Not valid file parameters";
    private static final String CREATE_EXCEPTION = "create file exception";
    public static final String ASC = "asc";
    public static final String DESC = "desc";

    @Override
    public void create(FileSystemObject fileSystemObject) throws InvalidFileException {
        checkFile(fileSystemObject);
        try {
            if (fileSystemObject.getType() == FOLDER) {
                createFolder(fileSystemObject);
            }
            if (fileSystemObject.getType() == FILE) {
                createFile(fileSystemObject);
            }
        } catch (IOException e) {
            throw new InvalidFileException(CREATE_EXCEPTION);
        }
    }

    @Override
    public void update(FileSystemObject oldFile, FileSystemObject newFile) throws InvalidFileException {
        checkFile(oldFile);
        checkFile(newFile);
        String oldFileAbsolutePath = createAbsolutePath(oldFile);
        String newFileAbsolutePath = createAbsolutePath(newFile);
        new File(oldFileAbsolutePath).renameTo(new File(newFileAbsolutePath));
    }

    @Override
    public void delete(FileSystemObject fileSystemObject) throws InvalidFileException {
        checkFile(fileSystemObject);
        String absolutePath = createAbsolutePath(fileSystemObject);
        new File(absolutePath).delete();
    }

    private void checkFile(FileSystemObject fileSystemObject) throws InvalidFileException {
        if (!validator.isValid(fileSystemObject)) {
            throw new InvalidFileException(NOT_VALID);
        }
    }

    private void createFile(FileSystemObject fileSystemObject) throws IOException {
        String absolutePath = createAbsolutePath(fileSystemObject);
        new File(absolutePath).createNewFile();
    }

    private void createFolder(FileSystemObject fileSystemObject) {
        new File(fileSystemObject.getPath(), fileSystemObject.getName()).mkdir();
    }

    private String createAbsolutePath(FileSystemObject fileSystemObject) {
        String path = fileSystemObject.getPath();
        String name = fileSystemObject.getName();
        return String.format("%s\\%s", path, name);
    }

    @Override
    public List<FileSystemObject> readFileByPath(String path) {
        List<FileSystemObject> files;
        if (path.isEmpty()) {
            files = getRoots();
        } else {
            File dir = new File(path);
            files = scanDir(dir);
        }
        return files;
    }

    @Override
    public List<FileSystemObject> filter(String path, String parameter, String sortType) {
        List<FileSystemObject> list = readFileByPath(path);
        Comparator<FileSystemObject> sorter = SorterFactory.createSorter(parameter);
        if (sortType.equals(DESC)) {
            sorter = sorter.reversed();
        }
        list.sort(sorter);
        return list;
    }

    @Override
    public List<FileSystemObject> search(String path, String mask) {
        List<FileSystemObject> fileList = this.readFileByPath(path);
        Pattern pattern = Pattern.compile(mask);
        return fileList
                .stream()
                .filter(fileEntity -> pattern.matcher(fileEntity.getName()).matches())
                .collect(Collectors.toList());
    }

    private List<FileSystemObject> getRoots() {
        List<FileSystemObject> files = new ArrayList<>();
        for (File file : File.listRoots()) {
            String path = file.getPath();
            String absolutePath = file.getAbsolutePath();
            long totalSpace = file.getTotalSpace();
            files.add(new FileSystemObject(DRIVE, path, absolutePath, totalSpace));
        }
        return files;
    }

    private List<FileSystemObject> scanDir(File dir) {
        List<FileSystemObject> files = new ArrayList<>();
        if (dir.isDirectory()) {
            for (File item : requireNonNull(dir.listFiles())) {
                FileSystemObjectType type = item.isDirectory() ? FOLDER : FILE;
                String name = item.getName();
                String absolutePath = item.getAbsolutePath();
                long length = item.length();
                files.add(new FileSystemObject(type, name, absolutePath, length));
            }
        }
        return files;
    }
}
