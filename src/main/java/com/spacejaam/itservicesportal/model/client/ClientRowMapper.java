package com.spacejaam.itservicesportal.model.client;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class ClientRowMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Client(
                rs.getLong("id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("contactNum"),
                Role.valueOf(rs.getString("role")),
                rs.getBoolean("isAccountNonExpired"),
                rs.getBoolean("isAccountNonLocked"),
                rs.getBoolean("isEnabled"),
                rs.getBoolean("isCredentialsNonExpired")
          /*      Boolean.parseBoolean(rs.getString("isAccountNonExpired")),
                Boolean.parseBoolean(rs.getString("isAccountNonLocked")),
                Boolean.parseBoolean(rs.getString("isEnabled")),
                Boolean.parseBoolean(rs.getString("isCredentialsNonExpired"))*/
        );
    }
}
