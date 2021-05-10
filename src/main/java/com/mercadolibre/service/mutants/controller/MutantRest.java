package com.mercadolibre.service.mutants.controller;


import com.mercadolibre.service.mutants.model.MutantDNA;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


public interface MutantRest {

    @PostMapping("/mutant")
    ResponseEntity getMutant(MutantDNA mutantDNA);

    @GetMapping("/stats")
    ResponseEntity getStats();

}
