package com.epam.tr.service.logic.builder;

import com.epam.tr.dto.FileRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.StringJoiner;

import static java.util.Objects.nonNull;

@Component
public class PathBuilder {

    public String createPath(FileRequestDto requestDto) {
        String fullPath = "";
        String drive = requestDto.getDrive();
        if (nonNull(drive)) {
            List<String> folders = requestDto.getFolder();
            StringJoiner joiner = new StringJoiner("\\");
            if (nonNull(folders)) {
                folders.forEach(joiner::add);
            }
            String pathToFile = joiner.toString();
            fullPath = drive + ":\\" + pathToFile;
        }
        return fullPath;
    }
}
