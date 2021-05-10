package com.mercadolibre.service.mutants.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MutantStatDNA {

    @JsonProperty("count_mutant_dna")
    private long numberMutants;

    @JsonProperty("count_human_dna")
    private long numberHumans;

    @JsonProperty
    private double ratio;

}
