package com.app.services;

public interface RandomGeneratorService<T> {
    public T generate(T lo, T hi);

    public String getLabel();
}
