package com.noah.wishlist.config;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileConfiguration {
    
    @Value("${wishlist.markdown-path}")
    private String markdownPath;
    
    @Getter
    private File markdownFile;
    
    @PostConstruct
    private void init() throws IOException {
        if (markdownPath == null || markdownPath.trim().isEmpty()) {
            throw new IOException("No path for markdown file specified, please set `wishlist.markdown-path`.");
        }
        markdownFile = getMarkdownFile(markdownPath);
    }
    
    private File getMarkdownFile(String markdownPath) throws IOException {
        Path path = Paths.get(markdownPath);
        
        if (Files.isDirectory(path)) {
            Path filePath = path.resolve("markdown.md");
            return createDefaultFile(filePath);
        }

        if (!Files.exists(path)) {
            return createDefaultFile(path);
        };
        
        if (Files.isRegularFile(path)) {
            if (!Files.isReadable(path) || !Files.isWritable(path)) {
                throw new IOException("Can't read or write to markdown file, please update permissions");
            }
            return path.toFile();
        }
        throw new IOException("Something went horribly wrong...");
    }
     
    private File createDefaultFile(Path path) throws IOException {
        Path parentDir = path.getParent();
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }
        File file = Files.createFile(path).toFile();
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("""
                  # woaow \n
                  Initial markdown file!
                  """);
        }
        return file;
    }

}
