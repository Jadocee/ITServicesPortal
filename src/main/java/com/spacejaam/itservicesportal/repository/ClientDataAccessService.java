package com.spacejaam.itservicesportal.repository;

import com.spacejaam.itservicesportal.bean.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public class ClientDataAccessService implements IClientDataAccessService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClientDataAccessService(JdbcTemplate jdbcTemplate, JdbcTemplate jdbcTemplate1) {
//        this.jdbcTemplate = jdbcTemplate;
        this.jdbcTemplate = jdbcTemplate1;
    }


    @Override
    public Client findByUsername(String username) {
        return null;
    }

    @Override
    public void save(Client client) {
//        final String sql = "INSERT INTO [client] (email, password, firstName, lastName, ";
//        return jdbcTemplate.update()

    }
}
