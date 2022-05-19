package com.spacejaam.itservicesportal.dao.issue;

import com.spacejaam.itservicesportal.model.issue.Issue;
import com.spacejaam.itservicesportal.model.issue.State;
import com.spacejaam.itservicesportal.model.issue.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IssueDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    IssueDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Issue> getIssuesByState(State state) {
        final String sql = "select i.*, c.firstName, c.lastName from Issue i, Client c, ClientIssue CI INNER JOIN Client C2 on C2.id = CI.client_id";
        return this.jdbcTemplate.query(sql, new IssueRowMapper(), state.name());
    }

    public int insertIssue(Issue issue) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final Tag tag : issue.getTags()) {
            stringBuilder.append(tag.name()).append(",");
        }
        final String sql = "insert into Issue (title, [desc], date, tags, state, category, subcategory) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return this.jdbcTemplate.update(sql, issue.getTitle(), issue.getDesc(), issue.getCreatedOn(),
                stringBuilder.toString(), issue.getState().name(), issue.getCategory().name(), issue.getSubCategory().name());
    }
}
