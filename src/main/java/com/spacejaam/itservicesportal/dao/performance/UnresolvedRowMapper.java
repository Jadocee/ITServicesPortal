package com.spacejaam.itservicesportal.dao.performance;

import com.spacejaam.itservicesportal.model.issue.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class UnresolvedRowMapper implements RowMapper<UnresolvedCount> {
    @Override
    public UnresolvedCount mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UnresolvedCount(
                Category.valueOf(rs.getString("category")),
                rs.getInt("unresolved")
        );
    }
}
