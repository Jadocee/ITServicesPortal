package com.spacejaam.itservicesportal.dao.issue;

import com.spacejaam.itservicesportal.model.issue.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class IssueRowMapper implements RowMapper<Issue> {
    @Override
    public Issue mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Issue issue = new Issue(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("desc"),
                Category.valueOf(rs.getString("category")),
                SubCategory.valueOf(rs.getString("subcategory")),
                rs.getString("firstName").concat(rs.getString("lastName")),
                State.valueOf(rs.getString("state")),
                rs.getDate("date")
        );
        final Set<Tag> tagSet = new HashSet<>();
        for (final String name : rs.getString("tags").split(",")) {
            tagSet.add(Tag.valueOf(name));
        }
        issue.setTags(tagSet);
        return issue;
    }
}
