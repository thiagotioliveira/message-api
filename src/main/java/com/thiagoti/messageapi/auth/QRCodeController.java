package com.thiagoti.messageapi.auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth/qrcode")
@RequiredArgsConstructor
@Slf4j
public class QRCodeController {
    
    @Value("${app.directory}")
    private String directory;
    
    @PostMapping
    public String handleQRCodeUpload(HttpServletRequest req) throws IOException {
        InputStream initialStream = req.getInputStream();
        String fileName = String.format("qrcode-%s.png", UUID.randomUUID().toString());
        log.debug("filename: {}", fileName);
        Files.copy(initialStream, new File(directory, fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
        IOUtils.closeQuietly(initialStream);
        return fileName;
    }
    
    @GetMapping("/{filename}")
    public byte[] getQRCode(@PathVariable String filename) throws FileNotFoundException, IOException {
        log.debug("get qrcode {}", filename);
        return IOUtils.toByteArray(new FileInputStream(new File(directory, filename)));
    }
}
