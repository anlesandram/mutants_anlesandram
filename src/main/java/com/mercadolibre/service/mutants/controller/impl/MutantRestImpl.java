package com.mercadolibre.service.mutants.controller.impl;

import com.mercadolibre.service.mutants.controller.MutantRest;
import com.mercadolibre.service.mutants.model.MutantDNA;
import com.mercadolibre.service.mutants.service.MutantService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutantRestImpl implements MutantRest {

    @Autowired
    MutantService mutantService;

    @Override
    public ResponseEntity getMutant(@RequestBody @NotNull MutantDNA mutantDNA) {
        return mutantService.processDNAMutant(mutantDNA) ? ResponseEntity
                .status(HttpStatus.OK).build():ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @Override
    public ResponseEntity getStats() {
        return ResponseEntity.status(HttpStatus.OK).body(mutantService.calculateStats());
    }

}
