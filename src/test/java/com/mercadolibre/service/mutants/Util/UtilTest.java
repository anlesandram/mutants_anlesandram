package com.mercadolibre.service.mutants.Util;

import com.mercadolibre.service.mutants.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilTest {
    public static final String[] DNA_MUTANT_SQUARE = {"ATGAAT", "CAATGA", "TAGCAT", "ATCAGG", "CCCGTA", "CCACTG"};
    public static final String[] DNA_MUTANT_NOT_PATTERN = {"ATGAAT", "CFATGA", "TAGCAT", "ATCAGG", "CCCGTA", "CCACTG"};
    public static final String[] DNA_MUTANT_NOT_SQUARE = {"ATGAAT", "CAATGA", "TAGCAT", "ATCAGG", "CCCGTA", "CCACTG", "CCACTG"};
    public static final String[] DNA_MUTANT_NOT_PATTERN_NOT_SQUARE = {"ATGAAT", "CFATGA", "TAGCAT", "ATCAGG", "CCCGTA", "CCACTG","CCACTG"};


    @Test
    void givenSequence_whenProcessDNA_thenCheckSquare() {
        assertTrue(Util.validateMatriz(DNA_MUTANT_SQUARE));
    }

    @Test
    void givenSequence_whenProcessDNA_thenCheckNotSquare() {
        assertFalse(Util.validateMatriz(DNA_MUTANT_NOT_SQUARE));
    }

    @Test
    void givenNotPattern_whenProcessDNA_thenCheckNotSquare() {
        assertFalse(Util.validateMatriz(DNA_MUTANT_NOT_PATTERN));
    }

    @Test
    void givenPattern_whenProcessDNA_thenCheckSquare() {
        assertFalse(Util.validateMatriz(DNA_MUTANT_NOT_PATTERN_NOT_SQUARE));
    }

}
