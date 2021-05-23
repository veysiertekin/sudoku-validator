package org.bitbucket.veysiertekin.sudoku_validator;

import org.bitbucket.veysiertekin.sudoku_validator.cli.CommandLineParser;
import org.bitbucket.veysiertekin.sudoku_validator.exception.SudokuRuntimeException;
import org.bitbucket.veysiertekin.sudoku_validator.factory.CsvOptionFactory;
import org.bitbucket.veysiertekin.sudoku_validator.file.CsvLoader;
import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuBoard;
import org.bitbucket.veysiertekin.sudoku_validator.sudoku.SudokuSolver;
import org.bitbucket.veysiertekin.sudoku_validator.utils.Logger;

/**
 * Starting point of Sudoku validation.
 *
 * <pre>
 * Usage:
 * $ ./validate.sh path_to_file
 * </pre>
 * <pre>
 * Example:
 * $ ./validate.sh src/test/resources/data/01-valid-input.csv
 * </pre>
 */
public class App {
    public static void main(String[] args) {
        var logger = Logger.getInstance();
        try {
            var options = new CommandLineParser(args).parse();
            var csvOptions = CsvOptionFactory.createSudokuCsvOptionFrom(options);
            var data = new CsvLoader(csvOptions).load();
            var result = SudokuSolver.getInstance()
                    .solve(new SudokuBoard(data));
            if (result.isPresent())
                logger.info(ApplicationMessage.STATUS_VALID);
            else
                logger.info(ApplicationMessage.STATUS_INVALID);
        } catch (SudokuRuntimeException exception) {
            logger.error(exception);
            logger.info(ApplicationMessage.STATUS_INVALID);
        }
    }
}
