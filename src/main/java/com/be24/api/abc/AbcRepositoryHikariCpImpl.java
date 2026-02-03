package com.be24.api.abc;

import com.be24.api.abc.repository.AbcRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AbcRepositoryHikariCpImpl implements AbcRepository {
    private final DataSource ds;

    public AbcRepositoryHikariCpImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Abc save(Abc abc) {
        try(Connection conn = ds.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO abc (name) VALUES (?)");
            pstmt.setString(1, abc.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    @Override
    public void deleteByIdx(Integer idx) {

    }

    @Override
    public Optional<Abc> findByIdx(Integer idx) {
        return Optional.empty();
    }

    @Override
    public List<Abc> findAll() {
        return List.of();
    }

    @Override
    public Abc update(Abc abc) {
        return null;
    }
}
