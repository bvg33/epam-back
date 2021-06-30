package com.epam.tr.service;

import com.epam.tr.dto.FileDto;
import com.epam.tr.entities.FileSystemObjectType;
import com.epam.tr.service.logic.builder.FileEntityBuilder;
import com.epam.tr.service.logic.sorter.SorterFactory;
import com.epam.tr.service.logic.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.epam.tr.entities.FileSystemObjectType.*;
import static java.io.File.listRoots;
import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;

@Service
public class FileSystemService implements FileService {

    @Autowired
    private Validator<FileDto> validator;
    private FileEntityBuilder builder = new FileEntityBuilder();

    @Override
    public void create(String drive, MultiValueMap<String, String> allRequestParams) throws IOException {
        FileDto fileDto = builder.buildFileEntity(drive, allRequestParams);
        if (validator.isValid(fileDto)) {
            if (fileDto.getType() == FOLDER) {
                createFolder(fileDto);
            }
            if (fileDto.getType() == FILE) {
                createFile(fileDto);
            }
        }
    }

    @Override
    public void update(FileDto oldFile, FileDto newFile) throws IOException {
        if (validator.isValid(newFile)) {
            String oldFileAbsolutePath = createAbsolutePath(oldFile);
            String newFileAbsolutePath = createAbsolutePath(newFile);
            Path fileToMovePath = Paths.get(newFileAbsolutePath);
            Path targetPath = Paths.get(oldFileAbsolutePath);
            Files.move(fileToMovePath, targetPath);
        }
    }

    @Override
    public void delete(String drive, MultiValueMap<String, String> allRequestParams) throws IOException {
        FileDto fileDto = builder.buildFileEntity(drive, allRequestParams);
        if (validator.isValid(fileDto)) {
            String absolutePath = createAbsolutePath(fileDto);
            Path path = Paths.get(absolutePath);
            Files.delete(path);
        }
    }

    private void createFile(FileDto fileSystemObject) throws IOException {
        String absolutePath = createAbsolutePath(fileSystemObject);
        Path path = Paths.get(absolutePath);
        Files.createFile(path);
    }

    private void createFolder(FileDto fileDto) throws IOException {
        Path path = Paths.get(createAbsolutePath(fileDto));
        Files.createDirectory(path);
    }

    private String createAbsolutePath(FileDto fileDto) {
        String path = fileDto.getPath();
        String name = fileDto.getName();
        return String.format("%s\\%s", path, name);
    }

    @Override
    public List<FileDto> readFileByPath(String path) {
        List<FileDto> files = goThroughFileTree(path);
        return files;
    }

    private List<FileDto> goThroughFileTree(String path) {
        List<FileDto> files = new ArrayList<>();
        if (path.isEmpty()) {
            files = getRoots();
        } else {
            File dir = new File(path);
            if (dir.isDirectory()) {
                files = scanDir(dir);
            }
        }
        return files;
    }

    @Override
    public List<FileDto> filter(String path, String parameter) {
        List<FileDto> list = goThroughFileTree(path);
        Comparator<FileDto> sorter = SorterFactory.createSorter(parameter);
        list.sort(sorter);
        return list;
    }

    @Override
    public List<FileDto> search(String path, String mask) {
        List<FileDto> fileList = goThroughFileTree(path);
        Pattern pattern = Pattern.compile(mask);
        return fileList
                .stream()
                .filter(fileEntity -> pattern.matcher(fileEntity.getName()).matches())
                .collect(Collectors.toList());
    }

    private List<FileDto> getRoots() {
        return stream(listRoots()).map(file -> {
            String path = file.getPath();
            String absolutePath = file.getAbsolutePath();
            long totalSpace = file.getTotalSpace();
            return new FileDto(DRIVE, path, absolutePath, totalSpace);
        }).collect(Collectors.toList());
    }

    private List<FileDto> scanDir(File dir) {
        return stream(requireNonNull(dir.listFiles())).map(file -> {
            FileSystemObjectType type = file.isDirectory() ? FOLDER : FILE;
            String name = file.getName();
            String absolutePath = file.getAbsolutePath();
            long length = file.length();
            return new FileDto(type, name, absolutePath, length);
        }).collect(Collectors.toList());
    }
}
