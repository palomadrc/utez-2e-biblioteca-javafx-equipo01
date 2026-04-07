package com.example.bibliotecaescolar.repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
//necesitamos hacer el archivo ese pa que se guarden los datos pa siempre
public class BookRepo {

    private final Path filePath = Paths.get("Data","books.csv");

    private void ensureFile() throws IOException {
        if (Files.notExists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }

    public List<String> readAllLines() throws IOException {
        ensureFile();
        return Files.readAllLines(filePath, StandardCharsets.UTF_8);
    }

    public void appendLine(String line) throws IOException {
        ensureFile();
        Files.writeString(filePath, line + System.lineSeparator(),
                StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }

    public void saveAll(List<String> lines) throws IOException {
        ensureFile();
        Files.write(filePath, lines, StandardCharsets.UTF_8,
                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }

}
