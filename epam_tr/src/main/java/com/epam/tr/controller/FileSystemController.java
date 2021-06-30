package com.epam.tr.controller;

import com.epam.tr.dto.FileDto;
import com.epam.tr.exceptions.InvalidCredentialsException;
import com.epam.tr.exceptions.InvalidFileException;
import com.epam.tr.service.FileService;
import com.epam.tr.service.logic.builder.FileEntityBuilder;
import com.epam.tr.service.logic.builder.PathBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/files")
public class FileSystemController {

    @Autowired
    private FileService service;
    @Autowired
    private PathBuilder pathBuilder;

    public static final String PARAMETER = "parameter";
    public static final String SORT_TYPE = "sortType";
    public static final String MASK = "mask";

    @GetMapping(value = {"/getFile", "/getFile/{drive}"})
    public ResponseEntity getFiles(@PathVariable(required = false) String drive, @RequestParam MultiValueMap<String, String> allRequestParams) {
        String path = pathBuilder.createPath(drive, allRequestParams);
        List<FileDto> list = service.readFileByPath(path);
        return ResponseEntity.status(OK).body(list);
    }

    @PostMapping("/{drive}")
    public ResponseEntity create(@PathVariable String drive, @RequestParam MultiValueMap<String, String> allRequestParams) throws InvalidFileException, InvalidCredentialsException, IOException {
        service.create(drive,allRequestParams);
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/{drive}")
    public ResponseEntity delete(@PathVariable String drive, @RequestParam MultiValueMap<String, String> allRequestParams) throws InvalidFileException, InvalidCredentialsException {
        service.delete(drive,allRequestParams);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @GetMapping("/filter/{drive}")
    public ResponseEntity filterFiles(@PathVariable String drive, @RequestParam MultiValueMap<String, String> allRequestParams) {
        String path = pathBuilder.createPath(drive, allRequestParams);
        String parameter = allRequestParams.getFirst(PARAMETER);
        List<FileDto> list = service.filter(path, parameter);
        return ResponseEntity.status(OK).body(list);
    }

    @GetMapping("/search/{drive}")
    public ResponseEntity findFilesByMask(@PathVariable String drive, @RequestParam MultiValueMap<String, String> allRequestParams) {
        String path = pathBuilder.createPath(drive, allRequestParams);
        String mask = allRequestParams.getFirst(MASK);
        List<FileDto> list = service.search(path, mask);
        return ResponseEntity.status(OK).body(list);
    }
}
