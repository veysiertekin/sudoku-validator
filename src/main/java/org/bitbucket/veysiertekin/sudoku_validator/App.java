package org.bitbucket.veysiertekin.sudoku_validator;

import org.bitbucket.veysiertekin.sudoku_validator.cli.CommandLineParser;
import org.bitbucket.veysiertekin.sudoku_validator.exception.SudokuRuntimeException;
import org.bitbucket.veysiertekin.sudoku_validator.file.CsvLoader;
import org.bitbucket.veysiertekin.sudoku_validator.sudoku.SudokuSolver;
import org.bitbucket.veysiertekin.sudoku_validator.utils.Logger;

/**
 *
 */
public class App {
    public static void main(String[] args) {
        var logger = Logger.getInstance();
        try {
            var options = new CommandLineParser(args).parse();
            var data = new CsvLoader(options.fileName()).load();
            var result = new SudokuSolver().solve(data);
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
