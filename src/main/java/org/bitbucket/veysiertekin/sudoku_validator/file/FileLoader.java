package org.bitbucket.veysiertekin.sudoku_validator.file;

import org.bitbucket.veysiertekin.sudoku_validator.exception.InvalidFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileLoader {
    private final String fileName;

    public FileLoader(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads the file has been given
     *
     * @return String lines of file content
     */
    public String[] load() {
        try {
            final var path = Paths.get(fileName);
            return readLines(path);
        } catch (IOException e) {
            throw new InvalidFileException(e, fileName);
        }
    }

    private String[] readLines(java.nio.file.Path path) throws IOException {
        return Files.lines(path)
                .toArray(String[]::new);
    }
}
