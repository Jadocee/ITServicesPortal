package com.spacejaam.itservicesportal.dao.client;

import com.spacejaam.itservicesportal.model.client.Client;
import com.spacejaam.itservicesportal.model.client.ClientRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    ClientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Client getClientByEmail(String email) {
        final String sql = "SELECT TOP 1 * FROM Client WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new ClientRowMapper(), email);
    }
}
