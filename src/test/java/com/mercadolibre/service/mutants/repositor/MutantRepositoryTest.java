package com.mercadolibre.service.mutants.repositor;

import com.mercadolibre.service.mutants.model.SequenceDNA;
import com.mercadolibre.service.mutants.repository.RepositoryDNA;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.any;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {RepositoryDNA.class})
class MutantRepositoryTest {

    public static final String[] DNA_HUMAN = {"ATGAAT", "CTATGA", "TAGAAT", "ATCAGG", "CCGGTA", "CCACTG"};

    @Autowired
    private RepositoryDNA repositoryDNA;

    @MockBean
    private RedisTemplate redisTemplate;

    @MockBean
    private HashOperations hashOperations;

    @Test
    void givenValidDNAKey_whenSaveRepository_thenPutKey() {
        SequenceDNA sequenceDNA = new SequenceDNA(Arrays.toString(DNA_HUMAN), Boolean.TRUE.toString());

        Mockito.when(redisTemplate.opsForHash()).thenReturn(hashOperations);
        Mockito.doNothing().when(hashOperations).put(any(String.class),any(String.class),any(String.class));

        repositoryDNA.saveDnaSequence(sequenceDNA);
    }

    @Test
    void whenProcessStats_thenReturnAllKeys() {
        Map<Object, Object> mapMutant = new HashMap<>();

        Mockito.when(redisTemplate.opsForHash()).thenReturn(hashOperations);
        Mockito.when(hashOperations.values(any(String.class))).thenReturn(Arrays.asList(mapMutant));

        Map<Object, Object> allDNASequences = repositoryDNA.findAllDNASequences();

        assertNotNull(allDNASequences);
    }

}
