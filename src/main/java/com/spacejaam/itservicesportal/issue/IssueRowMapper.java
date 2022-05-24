package com.spacejaam.itservicesportal.issue;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Repository
public class IssueRowMapper implements RowMapper<Issue> {
    @Override
    public Issue mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Issue issue = new Issue(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getNString("desc"),
                Category.valueOf(rs.getString("category")),
                SubCategory.valueOf(rs.getString("subcategory")),
                rs.getString("author"),
                State.valueOf(rs.getString("state")),
                rs.getObject("date", LocalDateTime.class)
        );
        final String tags = rs.getString("tags");
        if (tags != null) {
            final Set<Tag> tagSet = new HashSet<>();
            for (final String name : tags.split(",")) {
                tagSet.add(Tag.valueOf(name));
            }
            issue.setTags(tagSet);
        }
        return issue;
    }
}
