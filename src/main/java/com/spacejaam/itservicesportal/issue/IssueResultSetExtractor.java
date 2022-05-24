package com.spacejaam.itservicesportal.issue;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class IssueResultSetExtractor implements ResultSetExtractor<Map<String, List<Issue>>> {

    @Override
    public Map<String, List<Issue>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        final Map<String, List<Issue>> issuesListMap = new HashMap<>();
        while (rs.next()) {
            final String state = rs.getString("state");
            issuesListMap.computeIfAbsent(state, k -> new ArrayList<>());
            issuesListMap.get(state).add(new Issue(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getNString("desc"),
                    Category.valueOf(rs.getString("category")),
                    SubCategory.valueOf(rs.getString("subcategory")),
                    rs.getString("author"),
                    State.valueOf(state),
                    rs.getObject("date", LocalDateTime.class)
            ));
        }
        return issuesListMap;
    }
}
