package com.mercadolibre.service.mutants.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MutantDNA {

    @JsonProperty
    private String[] dna;
}
