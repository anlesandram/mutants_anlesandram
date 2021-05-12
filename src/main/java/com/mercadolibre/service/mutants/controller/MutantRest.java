package com.mercadolibre.service.mutants.controller;


import com.mercadolibre.service.mutants.model.MutantDNA;
import com.mercadolibre.service.mutants.model.MutantStatDNA;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Api(value = "Mutant Rest API")
public interface MutantRest {

    @ApiOperation(value = "Mutant Service - Mutant",
            notes = "Retrieve a DNA sequence and returns Successful if it is mutant and otherwise Forbidden")
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "DNA is Mutant"),
                    @ApiResponse(code = 403, message = "DNA is not Mutant")

            }
    )
    @PostMapping("/mutant")
    ResponseEntity getMutant(MutantDNA mutantDNA);


    @ApiOperation(value = "Mutant Service - Mutant",
            notes = "Returns the ratio between DNA human and mutant",
    response = MutantStatDNA.class)
    @ApiResponse(code = 200, message = "Stats about the verifications of DNA between Mutants and Humans")
    @GetMapping("/stats")
    ResponseEntity getStats();

}
