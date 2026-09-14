package com.unicam.hackhub.Util;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileDownloadUtil {
    public static ResponseEntity<Object>  fileDownload(String filename)
            throws FileNotFoundException
    {
        String path = "src/main/resources/"+filename;
        File file = new File(path);
        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"",file.getName()));
        headers.add("Cache-Control","no-cache, no-store, must-revalidate");
        headers.add("Pragma","no-cache");
        headers.add("Expires","0");

        ResponseEntity<Object> responseEntity = ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt"))
                .body(inputStreamResource);

        return responseEntity;
    }
}
