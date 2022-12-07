package com.onwumere.bright.employeemanagementsystem.repository;

/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 05/12/2022
 */

import com.onwumere.bright.employeemanagementsystem.exception.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class FileStorageRepository {

    @Value("${STORAGE_FOLDER}")
    private String storageFolder;
    public void save(String originalFilename, InputStream inputStream) {
        try {
            Path filePath = Path.of(storageFolder).resolve(originalFilename).normalize();
            Files.copy(inputStream, filePath);
        } catch (IOException e) {
           throw new StorageException(e);
        }
    }

    public Resource findByName(String filename){
        try {
            Path filePath = Path.of(storageFolder).resolve(filename).normalize();
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new StorageException(e);
        }
    }

    public void deleteByName(String fileName) {
        Path filePath = Path.of(storageFolder).resolve(fileName).normalize();
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAllByName(Collection<String> deleteFileNames) {
        deleteFileNames = deleteFileNames.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        try {
            for (String filename: deleteFileNames) {
                Path filePath = Path.of(storageFolder).resolve(filename).normalize();
                Files.deleteIfExists(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
