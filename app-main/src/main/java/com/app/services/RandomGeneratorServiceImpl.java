package com.app.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RandomGeneratorServiceImpl implements RandomGeneratorService<Integer> {
    @Value("${service.label:test_label}")
    public String label;

    @Override
    public Integer generate(Integer lo, Integer hi) {
        double result = (Math.random() * (hi - lo)) + lo;
        return (int) Math.floor(result);
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}
