package com.ps.repos.impl;

import com.ps.base.PetType;
import com.ps.ents.Pet;
import com.ps.ents.User;
import com.ps.repos.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 3/21/16.
 */
@Repository("petRepo")
public class JdbcPetRepo extends JdbcAbstractRepo<Pet> implements PetRepo {

    private RowMapper<Pet> rowMapper = new PetRowMapper();

    @Autowired
    public JdbcPetRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        findByIdQuery = "select id, name from pet";
    }

    @Override
    public Pet findByOwner(User owner, String name) {
        return null;
    }

    @Override
    public Set<Pet> findAllByOwner(User owner) {
        return null;
    }

    @Override
    public Set<Pet> findAllByType(PetType type) {
        return null;
    }

    @Override
    public Pet findById(Long entityId) {
        return jdbcTemplate.queryForObject(findByIdQuery, rowMapper, entityId);
    }

    /**
     * Maps a row returned from a query executed on the PET table to a Pet object.
     */
    private class PetRowMapper implements RowMapper<Pet> {

        public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("ID");
            String name = rs.getString("NAME");
            Pet pet = new Pet();
            pet.setId(id);
            pet.setName(name);
            return pet;
        }
    }
}
