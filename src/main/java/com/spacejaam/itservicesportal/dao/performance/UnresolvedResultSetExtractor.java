package com.spacejaam.itservicesportal.dao.performance;

import com.spacejaam.itservicesportal.model.issue.Category;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class UnresolvedResultSetExtractor implements ResultSetExtractor<Map<String, Integer>> {
    @Override
    public Map<String, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
        final Map<String, Integer> map = new HashMap<>();
        while (rs.next()) {
            map.put(Category.valueOf(rs.getString("category")).toString(), rs.getInt("unresolved"));
        }
        return map;
    }
}
