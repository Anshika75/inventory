package com.kcare.kcare.File;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Service

public class FileStorageService {

    @Value("${file.uploads.photos-output-path}")
    private String fileUploadPath;

    public List<String> saveProductImages(
            @NotNull List<MultipartFile> imageFiles,
            @NotNull Integer productId,
            @NotNull String fileTypeName

    ) {

        final String fileUploadSubPath = "Images" + File.separator + productId + File.separator + fileTypeName;
        return uploadFile(imageFiles, fileUploadSubPath);
    }

    private List<String> uploadFile(@NotNull List<MultipartFile> sourceFile, @NotNull String fileUploadSubPath) {
        final String finalUploadPath = fileUploadPath + File.separator + fileUploadSubPath;
        File targetFolder = new File(finalUploadPath);
        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
            if (!folderCreated) {
                log.warn("Folder Not created");
                return null;
            }
        }
        List<String> allFilePath = new ArrayList<>();
        int counter = 1;
        for (MultipartFile file : sourceFile) {
            final String fileExtension = getFileExtension(file.getOriginalFilename());
            String targetFilePath = finalUploadPath + File.separator + "Img" + counter + System.currentTimeMillis()
                    + "." + fileExtension;
            Path targetPath = Paths.get(targetFilePath);
            try {
                Files.write(targetPath, file.getBytes());
                allFilePath.add(targetFilePath);
                log.info("file saved at " + targetFilePath);
                counter++;
            } catch (IOException e) {
                log.error("File was not saved", e);
            }
        }
        return allFilePath;
    }

    private String getFileExtension(String fileName) {

        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }
        return fileName.substring(lastDotIndex + 1).toLowerCase();
    };

    public void deleteFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.deleteIfExists(path);
    }

}
