package org.bitbucket.veysiertekin.sudoku_validator;

import org.bitbucket.veysiertekin.sudoku_validator.exception.InvalidFileException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileLoader {
    private final String fileName;

    public FileLoader(final String fileName) {
        this.fileName = fileName;
    }

    public Scanner load() {
        try {
            final FileInputStream input = new FileInputStream(fileName);
            return new Scanner(input);
        } catch (FileNotFoundException e) {
            throw new InvalidFileException(e.getMessage(), e);
        }
    }
}
