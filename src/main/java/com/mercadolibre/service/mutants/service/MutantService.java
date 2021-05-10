package com.mercadolibre.service.mutants.service;


import com.mercadolibre.service.mutants.model.SequenceDNA;
import com.mercadolibre.service.mutants.model.MutantDNA;
import com.mercadolibre.service.mutants.model.MutantStatDNA;
import com.mercadolibre.service.mutants.repository.RepositoryDNA;
import com.mercadolibre.service.mutants.util.MutantProcessor;
import com.mercadolibre.service.mutants.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;


@Service
public class MutantService implements Serializable {

    private RepositoryDNA repositoryDNA;
    private MutantProcessor mutantProcessor;

    @Autowired
    public MutantService(MutantProcessor mutantProcessor, RepositoryDNA repositoryDNA) {
        this.mutantProcessor = mutantProcessor;
        this.repositoryDNA = repositoryDNA;
    }

    public Boolean processDNAMutant(MutantDNA mutantDNA) {
        if(!Util.validateMatriz(mutantDNA.getDna())){
            return Boolean.FALSE;
        }
        Boolean isMutant =  mutantProcessor.findMutant(mutantDNA.getDna());
        repositoryDNA.saveDnaSequence(new SequenceDNA(Arrays.toString(mutantDNA.getDna()), isMutant.toString()));
        return isMutant;
    }

    public MutantStatDNA calculateStats() {
        Map<Object, Object> sequences = repositoryDNA.findAllDNASequences();
        MutantStatDNA mutantStatDNA = new MutantStatDNA();

        if (sequences.isEmpty()) {
            return mutantStatDNA;
        }
        long numberMutants = sequences
                .entrySet()
                .stream()
                .map(item -> (String) item.getValue())
                .filter(item -> item.
                        equals(Boolean.TRUE.toString()))
                .count();
        long numberHumans = sequences.size() - numberMutants;

        mutantStatDNA.setNumberMutants(numberMutants);
        mutantStatDNA.setNumberHumans(numberHumans);
        mutantStatDNA.setRatio(Util.calculateRatio(numberMutants, numberHumans));

        return mutantStatDNA;

    }

}
