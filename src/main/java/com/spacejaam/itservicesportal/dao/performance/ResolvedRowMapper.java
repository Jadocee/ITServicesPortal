package com.spacejaam.itservicesportal.dao.performance;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 */
public class ResolvedRowMapper implements RowMapper<ResolvedCount> {
    @Override
    public ResolvedCount mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ResolvedCount(
                rs.getObject("date", LocalDateTime.class),
                rs.getInt("nResolved")
        );
    }
}
