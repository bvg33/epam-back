package com.epam.tr.service.logic.builder;

import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathBuilderTest {
    private final PathBuilder builder = new PathBuilder();
    @Test
    public void testCreatePath(){
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("folder","folder1");
        map.add("folder","folder2");
        String expected = "c:\\folder1\\folder2";

        String actual = builder.createPath("c",map);

        assertEquals(expected,actual);
    }
}
