# Sudoku Validation & Solution Finder

This project targets to validate given board mappings and calculates a solution if any exists.

## Installation

Make sure Java and Maven are installed on development machine.

Java & Maven can be installed with [homebrew](https://brew.sh) following commands on MacOS systems:

```bash
$ brew install openjdk@11
$ brew install maven
```

Set JAVA_HOME:

```bash
export JAVA_HOME=<path to java 11 home>
```

## Building Project

Project & surefire report can be build with following command. This will compile sources and generate a test report at `target/site/`.

```bash
$ mvn clean surefire-report:report
```

To create a jar package:

```bash
$ mvn jar:jar
```

This will create a jar package at `./target/sudoku-validator-*.jar` (pre-compiled package will be pushed to `git` for an easy evaluation process)

## Usage

Make sure `./validate.sh` file has desired permissions:

```bash
chmod +x ./validate.sh
```

### Printing Usage

Application can be executed with following command. This command will print a simple usage pattern:

```bash
$  ./validate.sh
Usage:
 $ ./validate.sh <path to file>

Example:
 $ ./validate.sh src/test/resources/data/01-valid-input.csv

Expected arguments: 1, given: 0
INVALID
```

### Triying invalid inputs

Invalid inputs produces different outputs for related errors.

#### Malformed input

Data:

```
-,3,,,7,,,,
6,,,1,9,5,,,
,9,8,,,,,6,
8,,,,6,,,,3
4,,,8,,3,,,1
7,,,,2,,,,6
,6,,,,,2,8,
,,,4,1,9,,,5
,,,,8,,,7,9
```

Result:

```bash
./validate.sh src/test/resources/data/02-marformed-input.csv
+Input does not match desired format!
> Expected format: ([1-9]?,){8}[1-9]?
> Line number: 1
> Input: -,3,,,7,,,,
INVALID
```

#### Invalid line count

Data:

```
5,3,,,7,,,,
6,,,1,9,5,,,
```

Result:

```bash
./validate.sh src/test/resources/data/04-invalid-line-count.csv
Invalid line count for given input size: 2
INVALID
```

#### Duplicated data on row, column or box

Data:

```
5,3,,,7,,,,
6,,,1,9,5,,,
,9,8,,,,,6,
6,,,,6,,,,3
4,,,8,,3,,,1
7,,,,2,,,,6
,6,,,,,2,8,
,,,4,1,9,,,5
,,,,8,,,7,9
```

Result:

```bash
./validate.sh src/test/resources/data/06-duplicated-data-on-cells.csv     
Duplicated values have been found on same ROW. Duplicated value: 6, Conflicted data points (x,y): (3, 0), (3, 4)
INVALID
```

#### Visually valid but no solution exists

(Taken from: [impossible-sudoku](http://www.jibble.org/impossible-sudoku/) )

Data:

```
,7,,,,6,,,
9,,,,,,,4,1
,,8,,,9,,5,
,9,,,,7,,,2
,,3,,,,8,,
4,,,8,,,,1,
,8,,3,,,9,,
1,6,,,,,,,7
,,,5,,,,8,
```

Result:

```bash
./validate.sh src/test/resources/data/05-visually-valid-but-unsolvable.csv 
Board visually valid but it is unsolvable!
INVALID
```

#### Valid and solution exist

Data:

```
5,3,,,7,,,,
6,,,1,9,5,,,
,9,8,,,,,6,
8,,,,6,,,,3
4,,,8,,3,,,1
7,,,,2,,,,6
,6,,,,,2,8,
,,,4,1,9,,,5
,,,,8,,,7,9
```

Result:

```bash
./validate.sh src/test/resources/data/01-valid-input.csv
Solution has been found for given sudoku puzzle!

Result:
534|678|912
672|195|348
198|342|567
-----------
859|761|423
426|853|791
713|924|856
-----------
961|537|284
287|419|635
345|286|179

VALID
```

