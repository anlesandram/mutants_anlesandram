package com.mercadolibre.service.mutants.util;

import java.util.Arrays;

public class Util {

    public static final String PATTERN_A = "A";
    public static final String PATTERN_T = "T";
    public static final String PATTERN_C = "C";
    public static final String PATTERN_G = "G";

    public static double calculateRatio(long numberMutants, long numberHumans) {
        return (double) numberMutants / ((double) numberHumans==0 ? 1:numberHumans);
    }

    public static Boolean validateMatriz(String[] dna) {
        long total = Arrays.stream(dna)
                .filter(s -> validatePattern(s) || s.length()!=dna.length)
                .count();
        return total > 0 ? Boolean.FALSE:Boolean.TRUE;
    }

    private static Boolean validatePattern(String sequence) {
        long total = Arrays.stream(sequence.split(""))
                .filter(i -> !i.equals(PATTERN_A)
                        && !i.equals(PATTERN_T)
                        && !i.equals(PATTERN_C)
                        && !i.equals(PATTERN_G))
                .count();
        return total > 0 ? Boolean.TRUE:Boolean.FALSE;
    }
}
