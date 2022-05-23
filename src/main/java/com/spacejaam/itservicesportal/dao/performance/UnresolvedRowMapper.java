package com.spacejaam.itservicesportal.dao.performance;

import com.spacejaam.itservicesportal.model.issue.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class UnresolvedRowMapper implements RowMapper<Map<String, Object>> {
    @Override
    public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Map<String, Object> map = new HashMap<>();
        map.put(Category.valueOf(rs.getString("category")).toString(), rs.getInt("unresolved"));
        return map;
    }
}
