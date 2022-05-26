package com.spacejaam.itservicesportal.issue;

import com.spacejaam.itservicesportal.author.Author;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

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
            final Issue issue = new Issue(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getNString("desc"),
                    Category.valueOf(rs.getString("category")),
                    SubCategory.valueOf(rs.getString("subcategory")),
                    new Author(
                            rs.getString("author name"),
                            rs.getString("author role"),
                            rs.getLong("author id")
                    ),
                    State.valueOf(state),
                    rs.getObject("date", LocalDateTime.class)
            );
            final Date resolvedDate = rs.getDate("resolved_date");
            if (resolvedDate != null) {
                issue.setResolvedOn(resolvedDate.toLocalDate());
            }
            final String tags = rs.getString("tags");
            if (tags != null) {
                final Set<Tag> tagSet = new HashSet<>();
                for (final String name : tags.split(",")) {
                    tagSet.add(Tag.valueOf(name));
                }
                issue.setTags(tagSet);
            }
            issuesListMap.get(state).add(issue);

        }
        return issuesListMap;
    }
}