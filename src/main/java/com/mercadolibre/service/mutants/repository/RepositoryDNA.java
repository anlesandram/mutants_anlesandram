package com.mercadolibre.service.mutants.repository;

import com.mercadolibre.service.mutants.model.SequenceDNA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class RepositoryDNA {

    private static final String DNA_KEY = "DNA-KEY";

    private RedisTemplate redisTemplate;

    @Autowired
    public RepositoryDNA(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveDnaSequence(SequenceDNA sequenceDNA) {
        redisTemplate.opsForHash().put(DNA_KEY, sequenceDNA.getDna(), sequenceDNA.getType());
    }

    public Map<Object, Object> findAllDNASequences() {
        return redisTemplate.opsForHash().entries(DNA_KEY);
    }

}
