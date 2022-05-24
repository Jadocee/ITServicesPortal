package com.spacejaam.itservicesportal.performance;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ResolvedRowMapper implements RowMapper<Map<String, Object>> {
    @Override
    public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Map<String, Object> map = new HashMap<>();
        map.put("x", rs.getDate("date").toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        map.put("y", rs.getInt("nResolved"));
        return map;
    }
}
