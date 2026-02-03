package com.be24.api.abc.repository;

import com.be24.api.abc.Abc;

import java.util.List;
import java.util.Optional;

public interface AbcReadRepository {
    Optional<Abc> findByIdx(Integer idx);
    List<Abc> findAll();
}
