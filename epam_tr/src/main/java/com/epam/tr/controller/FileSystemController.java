package com.epam.tr.controller;

import com.epam.tr.dto.FileDto;
import com.epam.tr.dto.FileRequestDto;
import com.epam.tr.exceptions.InvalidCredentialsException;
import com.epam.tr.exceptions.InvalidFileException;
import com.epam.tr.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/files")
public class FileSystemController {

    @Autowired
    private FileService service;

    @GetMapping(value = {"/getFile", "/getFile/{drive}"})
    public ResponseEntity getFiles(FileRequestDto requestDto) {
        List<FileDto> list = service.readFileByPath(requestDto);
        return ResponseEntity.status(OK).body(list);
    }

    @PostMapping("/{drive}")
    public ResponseEntity create(FileRequestDto requestDto) throws InvalidFileException, InvalidCredentialsException, IOException {
        service.create(requestDto);
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/{drive}")
    public ResponseEntity delete(FileRequestDto requestDto) throws InvalidFileException, InvalidCredentialsException, IOException {
        service.delete(requestDto);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @GetMapping("/filter/{drive}")
    public ResponseEntity filterFiles(FileRequestDto requestDto) {
        List<FileDto> list = service.filter(requestDto);
        return ResponseEntity.status(OK).body(list);
    }

    @GetMapping("/search/{drive}")
    public ResponseEntity findFilesByMask(FileRequestDto requestDto) {
        List<FileDto> list = service.search(requestDto);
        return ResponseEntity.status(OK).body(list);
    }
}
