package com.example.rzdwebapp.repository.query;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

@Component
public class QueryLoader {
    public static String loadQuery(String name){
        try {
            Resource resource = new ClassPathResource("sql/" + name);
            String q = StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());

            System.err.println(q);
            return q;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }
}
