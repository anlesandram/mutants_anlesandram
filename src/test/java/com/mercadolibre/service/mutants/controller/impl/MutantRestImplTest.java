package com.mercadolibre.service.mutants.controller.impl;

import com.mercadolibre.service.mutants.model.MutantDNA;
import com.mercadolibre.service.mutants.model.MutantStatDNA;
import com.mercadolibre.service.mutants.service.MutantService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MutantRestImpl.class)
class MutantRestImplTest {

    public static final String MUTANT_DNA = "{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}";
    public static final String POST_MUTANT_REQUEST = "/mutant";
    public static final String GET_STATS_REQUEST = "/stats";

    public static final int NUMBER_MUTANT = 20;
    public static final int NUMBER_HUMANS = 10;
    public static final double RATIO = 2;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MutantService mutantService;

    @Test
    void givenValidDNA_whenPostMutant_thenSucceed() throws Exception {
        Mockito.when(mutantService.processDNAMutant(Mockito.any(MutantDNA.class))).thenReturn(Boolean.TRUE);

        mockMvc.perform(post(POST_MUTANT_REQUEST)
                .content(MUTANT_DNA)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void givenValidDNA_whenPostMutant_thenForbidden() throws Exception {
        Mockito.when(mutantService.processDNAMutant(Mockito.any(MutantDNA.class))).thenReturn(Boolean.FALSE);

        mockMvc.perform(post(POST_MUTANT_REQUEST)
                .content(MUTANT_DNA)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
    }

    @Test
    void whenGetStats_thenSucceed() throws Exception {
        MutantStatDNA mutantStatDNA = new MutantStatDNA();
        mutantStatDNA.setNumberMutants(NUMBER_MUTANT);
        mutantStatDNA.setNumberHumans(NUMBER_HUMANS);
        mutantStatDNA.setRatio(RATIO);

        Mockito.when(mutantService.calculateStats()).thenReturn(mutantStatDNA);

        mockMvc.perform(get(GET_STATS_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}
