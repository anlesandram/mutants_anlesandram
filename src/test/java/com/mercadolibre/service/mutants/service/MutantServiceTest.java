package com.mercadolibre.service.mutants.service;

import com.mercadolibre.service.mutants.model.MutantDNA;
import com.mercadolibre.service.mutants.model.MutantStatDNA;
import com.mercadolibre.service.mutants.model.SequenceDNA;
import com.mercadolibre.service.mutants.repository.RepositoryDNA;
import com.mercadolibre.service.mutants.util.MutantProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {MutantService.class, MutantProcessor.class})
class MutantServiceTest {

    public static final String[] DNA_MUTANT = {"ATGAAT", "CAATGA", "TAGCAT", "ATCAGG", "CCCGTA", "CCACTG"};
    public static final String[] DNA_HUMAN = {"ATGAAT", "CTATGA", "TAGAAT", "ATCAGG", "CCGGTA", "CCACTG"};

    @Autowired
    private MutantService mutantService;

    @MockBean
    private RepositoryDNA repositoryDNA;

    @Test
    void givenValidDNA_whenProcessDNA_thenMutant() {
        MutantDNA mutantDNA = new MutantDNA();
        mutantDNA.setDna(DNA_MUTANT);

        Mockito.doNothing().when(repositoryDNA).saveDnaSequence(Mockito.any(SequenceDNA.class));

        assertTrue(mutantService.processDNAMutant(mutantDNA));
    }

    @Test
    void givenValidDNA_whenProcessDNA_thenHuman() {
        MutantDNA mutantDNA = new MutantDNA();
        mutantDNA.setDna(DNA_HUMAN);

        Mockito.doNothing().when(repositoryDNA).saveDnaSequence(Mockito.any(SequenceDNA.class));

        assertFalse(mutantService.processDNAMutant(mutantDNA));
    }

    @Test
    void whenProcessDNA_thenMutantStats() {
        Map<Object, Object> mapMutant = new HashMap<>();
        mapMutant.put(Arrays.toString(DNA_MUTANT), Boolean.TRUE.toString());

        Mockito.when(repositoryDNA.findAllDNASequences()).thenReturn(mapMutant);

        MutantStatDNA mutantStatDNA = mutantService.calculateStats();
        assertNotNull(mutantStatDNA);
        assertTrue(mutantStatDNA.getNumberMutants() > 0);
    }

    @Test
    void whenProcessDNA_thenEmptyMutantStats() {
        Map<Object, Object> mapMutant = new HashMap<>();
        Mockito.when(repositoryDNA.findAllDNASequences()).thenReturn(mapMutant);

        MutantStatDNA mutantStatDNA = mutantService.calculateStats();
        assertNotNull(mutantStatDNA);
        assertEquals(0, mutantStatDNA.getNumberMutants());
    }

}
