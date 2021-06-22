package com.epam.tr.controller;

import com.epam.tr.entities.FileSystemObject;
import com.epam.tr.exceptions.InvalidCredentialsException;
import com.epam.tr.exceptions.InvalidFileException;
import com.epam.tr.service.FileService;
import com.epam.tr.service.logic.builder.FileEntityBuilder;
import com.epam.tr.service.logic.builder.PathBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/files")
public class FileSystemController {

    @Autowired
    private FileService service;
    private FileEntityBuilder builder = new FileEntityBuilder();
    @Autowired
    private PathBuilder pathBuilder;

    public static final String PARAMETER = "parameter";
    public static final String SORT_TYPE = "sortType";
    public static final String MASK = "mask";

    @GetMapping(value = {"/getFile", "/getFile/{drive}"})
    public ResponseEntity getFiles(@PathVariable(required = false) String drive, @RequestParam MultiValueMap<String, String> allRequestParams) {
        String path = pathBuilder.createPath(drive, allRequestParams);
        return ResponseEntity.status(OK).body(service.readFileByPath(path));
    }

    @PostMapping("/createFile/{drive}")
    public ResponseEntity create(@PathVariable String drive, @RequestParam MultiValueMap<String, String> allRequestParams) throws InvalidFileException, InvalidCredentialsException {
        FileSystemObject fileSystemObject = builder.buildFileEntity(drive, allRequestParams);
        service.create(fileSystemObject);
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/deleteFile/{drive}")
    public ResponseEntity delete(@PathVariable String drive, @RequestParam MultiValueMap<String, String> allRequestParams) throws InvalidFileException, InvalidCredentialsException {
        FileSystemObject fileSystemObject = builder.buildFileEntity(drive, allRequestParams);
        service.delete(fileSystemObject);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @GetMapping("/filter/{drive}")
    public ResponseEntity filterFiles(@PathVariable String drive, @RequestParam MultiValueMap<String, String> allRequestParams) {
        String path = pathBuilder.createPath(drive, allRequestParams);
        String parameter = allRequestParams.getFirst(PARAMETER);
        String sortType = allRequestParams.getFirst(SORT_TYPE);
        return ResponseEntity.status(OK).body(service.filter(path, parameter, sortType));
    }

    @GetMapping("/search/{drive}")
    public ResponseEntity findFilesByMask(@PathVariable String drive, @RequestParam MultiValueMap<String, String> allRequestParams) {
        String path = pathBuilder.createPath(drive, allRequestParams);
        String mask = allRequestParams.getFirst(MASK);
        return ResponseEntity.status(OK).body(service.search(path, mask));
    }
}
