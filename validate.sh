#!/bin/sh

# 'exec' allows to replace main process with desired one
# useful for cli cancellation with Ctrl+C
exec java -jar ./target/sudoku-validator-*.jar $1
