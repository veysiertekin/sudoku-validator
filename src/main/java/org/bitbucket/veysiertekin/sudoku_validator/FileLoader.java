package org.bitbucket.veysiertekin.sudoku_validator;

import org.bitbucket.veysiertekin.sudoku_validator.exception.InvalidFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileLoader {
    private final String fileName;

    public FileLoader(final String fileName) {
        this.fileName = fileName;
    }

    public String[] load() {
        try {
            final var path = Paths.get(fileName);
            return Files.lines(path)
                    .toArray(String[]::new);
        } catch (IOException e) {
            throw new InvalidFileException(e.getMessage(), e);
        }
    }
}
