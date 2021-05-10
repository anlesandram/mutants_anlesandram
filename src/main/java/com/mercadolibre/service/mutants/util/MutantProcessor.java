package com.mercadolibre.service.mutants.util;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MutantProcessor {

    private static final int DEFAULT_INCREASE_VALUE = 1;
    private static final int SUFFIX_HORIZONTAL = 0;
    private static final int SUFFIX_VERTICAL = 1;
    private static final int SUFFIX_DIAGONAL_RIGHT = 2;
    private static final int SUFFIX_DIAGONAL_LEFT = 3;

    private String patternSplitSequence = "";
    private int maxSequencesRequired = 2;
    private int patternLength = 3;


    public Boolean findMutant(String[] dna) {
        return checkNumberDnaSequences(createDnaMatrix(dna, dna.length));
    }


    private Boolean checkNumberDnaSequences(String[][] matrizDna) {
        int length = matrizDna.length;
        int total = 0;

        String[][] matrizDnaMirror = new String[length][length];

        for (int row = 0; row < length; row++) {
            for (int col = 0; col < length; col++) {
                total = checkHorizontalSequence(matrizDna, matrizDnaMirror, col, row, length) + total;
                total = checkVerticalSequence(matrizDna, matrizDnaMirror, col, row, length) + total;
                total = checkDiagonalRightSequence(matrizDna, matrizDnaMirror, col, row, length) + total;
                total = checkDiagonalLeftSequence(matrizDna, matrizDnaMirror, col, row, length) + total;

                if (total >= maxSequencesRequired) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    private int checkHorizontalSequence(String[][] matrizDna, String[][] matrizDnaMirror, int col, int row, int length) {
        if (col + patternLength >= length) {
            return 0;
        }

        String newDnaSequence = matrizDna[row][col];
        String possibleDnaMatch = matrizDna[row][col + patternLength];

        if (col >= DEFAULT_INCREASE_VALUE) {
            String oldDnaSequence = matrizDnaMirror[row][col - DEFAULT_INCREASE_VALUE];
            if (Objects.nonNull(oldDnaSequence) && (oldDnaSequence).equals(newDnaSequence + SUFFIX_HORIZONTAL)) {
                matrizDnaMirror[row][col] = newDnaSequence + SUFFIX_HORIZONTAL;
                return 0;
            }
        }

        if (newDnaSequence.equals(possibleDnaMatch)) {
            if (checkHorizontalHint(matrizDna, row, col, DEFAULT_INCREASE_VALUE, 0, 0) >= patternLength) {
                matrizDnaMirror[row][col] = newDnaSequence + SUFFIX_HORIZONTAL;
                return 1;
            }
        }

        return 0;
    }


    private int checkVerticalSequence(String[][] matrizDna, String[][] matrizDnaMirror, int col, int row, int length) {
        if (row + patternLength >= length) {
            return 0;
        }

        String newDnaSequence = matrizDna[row][col];
        String possibleDnaMatch = matrizDna[row + patternLength][col];

        if (row >= DEFAULT_INCREASE_VALUE) {
            String oldDnaSequence = matrizDnaMirror[row - DEFAULT_INCREASE_VALUE][col];
            if (Objects.nonNull(oldDnaSequence) && (oldDnaSequence).equals(newDnaSequence + SUFFIX_VERTICAL)) {
                matrizDnaMirror[row][col] = newDnaSequence + SUFFIX_VERTICAL;
                return 0;
            }
        }

        if (newDnaSequence.equals(possibleDnaMatch)) {
            if (checkVerticalHint(matrizDna, row, col, DEFAULT_INCREASE_VALUE, 0, 0) >= patternLength) {
                matrizDnaMirror[row][col] = newDnaSequence + SUFFIX_VERTICAL;
                return 1;
            }
        }
        return 0;
    }


    private int checkDiagonalRightSequence(String[][] matrizDna, String[][] matrizDnaMirror, int col, int row, int length) {
        if (col + patternLength >= length || row + patternLength >= length) {
            return 0;
        }
        String newDnaSequence = matrizDna[row][col];
        String possibleDnaMatch = matrizDna[row + patternLength][col + patternLength];

        if (row >= DEFAULT_INCREASE_VALUE && col >= DEFAULT_INCREASE_VALUE) {
            String oldDnaSequence = matrizDnaMirror[row - DEFAULT_INCREASE_VALUE][col - DEFAULT_INCREASE_VALUE];
            if (Objects.nonNull(oldDnaSequence)
                    && (oldDnaSequence).equals(newDnaSequence + SUFFIX_DIAGONAL_RIGHT)) {
                matrizDnaMirror[row][col] = newDnaSequence + SUFFIX_DIAGONAL_RIGHT;
                return 0;
            }
        }

        if (newDnaSequence.equals(possibleDnaMatch)) {
            if (checkDiagonalRightHint(matrizDna, row, col, DEFAULT_INCREASE_VALUE, 0, 0) >= patternLength) {
                matrizDnaMirror[row][col] = newDnaSequence + SUFFIX_DIAGONAL_RIGHT;
                return 1;
            }
        }
        return 0;
    }


    private int checkDiagonalLeftSequence(String[][] matrizDna, String[][] matrizDnaMirror, int col, int row, int length) {
        if (row + patternLength >= length || col - patternLength < 0) {
            return 0;
        }
        String newDnaSequence = matrizDna[row][col];
        String possibleDnaMatch = matrizDna[row + patternLength][col - patternLength];

        if (row >= DEFAULT_INCREASE_VALUE && col < patternLength) {
            String oldDnaSequence = matrizDnaMirror[row - DEFAULT_INCREASE_VALUE][col + DEFAULT_INCREASE_VALUE];
            if (Objects.nonNull(oldDnaSequence) && (oldDnaSequence).equals(newDnaSequence)) {
                matrizDnaMirror[row][col] = newDnaSequence + SUFFIX_DIAGONAL_LEFT;
                return 0;
            }
        }

        if (newDnaSequence.equals(possibleDnaMatch)) {
            if (checkDiagonalLeftHint(matrizDna, row, col, DEFAULT_INCREASE_VALUE, 0, 0) >= patternLength) {
                matrizDnaMirror[row][col] = newDnaSequence + SUFFIX_DIAGONAL_LEFT;
                return 1;
            }
        }
        return 0;
    }

    private int checkHorizontalHint(String[][] matrizDna, int row, int col, int increase, int sequences, int iterations) {
        if (iterations >= patternLength) {
            return sequences;
        } else {
            sequences = matrizDna[row][col + increase].equals(matrizDna[row][col]) ? sequences + 1:0;
            iterations++;
            increase++;
            return checkHorizontalHint(matrizDna, row, col, increase, sequences, iterations);
        }
    }

    private int checkVerticalHint(String[][] matrizDna, int row, int col, int increase, int sequences, int iterations) {
        if (iterations >= patternLength) {
            return sequences;
        } else {
            sequences = matrizDna[row + increase][col].equals(matrizDna[row][col]) ? sequences + 1:0;
            iterations++;
            increase++;
            return checkVerticalHint(matrizDna, row, col, increase, sequences, iterations);
        }
    }

    private int checkDiagonalLeftHint(String[][] matrizDna, int row, int col, int increase, int sequences, int iterations) {
        if (iterations >= patternLength) {
            return sequences;
        } else {
            sequences = matrizDna[row + increase][col - increase].equals(matrizDna[row][col]) ? sequences + 1:0;
            iterations++;
            increase++;
            return checkDiagonalLeftHint(matrizDna, row, col, increase, sequences, iterations);
        }
    }

    private int checkDiagonalRightHint(String[][] matrizDna, int row, int col, int increase, int sequences, int iterations) {
        if (iterations >= patternLength) {
            return sequences;
        } else {
            sequences = matrizDna[row + increase][col + increase].equals(matrizDna[row][col]) ? sequences + 1:0;
            iterations++;
            increase++;
            return checkDiagonalRightHint(matrizDna, row, col, increase, sequences, iterations);
        }
    }

    private String[][] createDnaMatrix(String[] dna, int length) {
        String[][] matrizDna = new String[length][length];

        for (int index = 0; index < length; index++) {
            matrizDna[index] = dna[index].split(patternSplitSequence);
        }
        return matrizDna;
    }
}

