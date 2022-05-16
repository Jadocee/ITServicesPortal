package com.spacejaam.itservicesportal.repository;

import com.spacejaam.itservicesportal.bean.client.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Deprecated

public class ClientRowMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Client(
                rs.getLong("id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("contactNum"),
                rs.getString("role"),
                rs.getString("isAccountNonExpired"),
                rs.getString("isAccountNonLocked"),
                rs.getString("isCredentialsNonExpired"),
                rs.getString("isEnabled")
        );
    }
}
