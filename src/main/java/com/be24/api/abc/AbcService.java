package com.be24.api.abc;

public class AbcService {
    private final AbcRepository abcRepository;

    public AbcService(AbcRepository abcRepository) {
        this.abcRepository = abcRepository;
    }
}
