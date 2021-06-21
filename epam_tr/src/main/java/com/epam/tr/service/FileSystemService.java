package com.epam.tr.service;

import com.epam.tr.entities.FileEntity;
import com.epam.tr.entities.FileType;
import com.epam.tr.exceptions.WrongFileException;
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

import static com.epam.tr.entities.FileType.*;
import static java.util.Objects.requireNonNull;

@Service
public class FileSystemService implements FileService {

    @Autowired
    private Validator<FileEntity> validator;

    private static final String NOT_VALID = "Not valid file parameters";
    private static final String CREATE_EXCEPTION = "create file exception";
    public static final String ASC = "asc";
    public static final String DESC = "desc";

    @Override
    public void create(FileEntity fileEntity) throws WrongFileException {
        checkFile(fileEntity);
        try {
            if (fileEntity.getType() == FOLDER) {
                createFolder(fileEntity);
            }
            if (fileEntity.getType() == FILE) {
                createFile(fileEntity);
            }
        } catch (IOException e) {
            throw new WrongFileException(CREATE_EXCEPTION);
        }
    }

    @Override
    public void update(FileEntity oldFile, FileEntity newFile) throws WrongFileException {
        checkFile(oldFile);
        checkFile(newFile);
        String oldFileAbsolutePath = createAbsolutePath(oldFile);
        String newFileAbsolutePath = createAbsolutePath(newFile);
        new File(oldFileAbsolutePath).renameTo(new File(newFileAbsolutePath));
    }

    @Override
    public void delete(FileEntity fileEntity) throws WrongFileException {
        checkFile(fileEntity);
        String absolutePath = createAbsolutePath(fileEntity);
        new File(absolutePath).delete();
    }

    private void checkFile(FileEntity fileEntity) throws WrongFileException {
        if (!validator.isValid(fileEntity)) {
            throw new WrongFileException(NOT_VALID);
        }
    }

    private void createFile(FileEntity fileEntity) throws IOException {
        String absolutePath = createAbsolutePath(fileEntity);
        new File(absolutePath).createNewFile();
    }

    private void createFolder(FileEntity fileEntity) {
        new File(fileEntity.getPath(), fileEntity.getName()).mkdir();
    }

    private String createAbsolutePath(FileEntity fileEntity) {
        String path = fileEntity.getPath();
        String name = fileEntity.getName();
        String absolutePath = path + "\\" + name;
        if (fileEntity.getType() == FILE) {
            absolutePath += ".txt";
        }
        return absolutePath;
    }

    @Override
    public List<FileEntity> readFileByPath(String path) {
        List<FileEntity> files;
        if (path.isEmpty()) {
            files = getRoots();
        } else {
            File dir = new File(path);
            files = scanDir(dir);
        }
        return files;
    }

    @Override
    public List<FileEntity> filter(String path, String parameter, String sortType) {
        List<FileEntity> list = readFileByPath(path);
        Comparator<FileEntity> sorter = SorterFactory.createSorter(parameter);
        if (sortType.equals(ASC)) {
            list.sort(sorter);
        } else if (sortType.equals(DESC)) {
            list.sort(sorter.reversed());
        }
        return list;
    }

    @Override
    public List<FileEntity> search(String path, String mask) {
        List<FileEntity> fileList = this.readFileByPath(path);
        Pattern pattern = Pattern.compile(mask);
        List<FileEntity> suitableFiles = new ArrayList<>();
        fileList.stream().filter((fileEntity) -> pattern.matcher(fileEntity.getName()).matches()).forEach((suitableFiles::add));
        return suitableFiles;
    }

    private List<FileEntity> getRoots() {
        List<FileEntity> files = new ArrayList<>();
        for (File file : File.listRoots()) {
            String path = file.getPath();
            String absolutePath = file.getAbsolutePath();
            long totalSpace = file.getTotalSpace();
            files.add(new FileEntity(DRIVE, path, absolutePath, totalSpace));
        }
        return files;
    }

    private List<FileEntity> scanDir(File dir) {
        List<FileEntity> files = new ArrayList<>();
        if (dir.isDirectory()) {
            for (File item : requireNonNull(dir.listFiles())) {
                FileType type = (item.isDirectory()) ? FOLDER : FILE;
                String name = item.getName();
                String absolutePath = item.getAbsolutePath();
                long length = item.length();
                files.add(new FileEntity(type, name, absolutePath, length));
            }
        }
        return files;
    }
}
