package pl.lukakan.showstracker.show;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.time.LocalDate;

public class FileUploadUtils {

    public static String saveFileAndReturnFileName(String uploadDir, Long id, String title, MultipartFile multipartFile) throws IOException {

        Path uploadPath = Paths.get(uploadDir + id);
        String fileExtension = FileUploadUtils.getFileExtension(
                StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        String fileName = createImageName(id, title, fileExtension);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
//            File file = new File(uploadPath.toString());
//            for (File f : file.listFiles()) {
//                if (!f.isDirectory()) {
//                    f.delete();
//                }
//            }
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new IOException("Could not save image file: " + fileName, e);
        }
    }

    private static String createImageName(Long id, String title, String extension) {
        StringBuilder fileNameBuild = new StringBuilder();
        fileNameBuild.append(id);
        fileNameBuild.append('_');
        fileNameBuild.append(title);
        fileNameBuild.append('_');
        fileNameBuild.append(LocalDate.now().toString());
        fileNameBuild.append(extension);
        return fileNameBuild.toString();
    }

    public static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

}