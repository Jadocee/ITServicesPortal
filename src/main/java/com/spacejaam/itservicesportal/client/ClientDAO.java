package com.spacejaam.itservicesportal.client;

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

    public void insertClient(Client client) {
        final String sql = "insert into Client (email, password, firstName, lastName, contactNum, role) values (?, ?, ?, ?, ?, ?);";
        this.jdbcTemplate.update(sql, client.getEmail(), client.getPassword(), client.getFirstName(), client.getLastName(), client.getContactNum(), client.getRole().name());
    }
}
