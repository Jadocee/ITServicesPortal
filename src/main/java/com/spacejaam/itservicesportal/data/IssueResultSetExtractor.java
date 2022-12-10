package com.spacejaam.itservicesportal.data;

import com.spacejaam.itservicesportal.enums.Category;
import com.spacejaam.itservicesportal.enums.State;
import com.spacejaam.itservicesportal.enums.SubCategory;
import com.spacejaam.itservicesportal.enums.Tag;
import com.spacejaam.itservicesportal.models.Author;
import com.spacejaam.itservicesportal.models.Issue;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class IssueResultSetExtractor implements ResultSetExtractor<Map<String, List<Issue>>> {

    @Override
    public Map<String, List<Issue>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        final Map<String, List<Issue>> issuesListMap = new HashMap<>();
        issuesListMap.put("New", new ArrayList<>());
        issuesListMap.put("In Progress", new ArrayList<>());
        issuesListMap.put("Completed", new ArrayList<>());
        issuesListMap.put("Resolved", new ArrayList<>());
        while (rs.next()) {
            final State state = State.valueOf(rs.getString("state"));
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
                    state,
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
            issuesListMap.get(state.toString()).add(issue);

        }
        return issuesListMap;
    }
}
