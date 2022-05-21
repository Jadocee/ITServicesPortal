package com.spacejaam.itservicesportal.dao.issue;

import com.spacejaam.itservicesportal.model.issue.Issue;
import com.spacejaam.itservicesportal.model.issue.State;
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
        final String sql = "select i.*, c.firstName, c.lastName from Issue i, Client c, ClientIssue CI inner join Client C2 on C2.id = CI.client_id";
        return this.jdbcTemplate.query(sql, new IssueRowMapper(), state.name());
    }

    public Issue getIssueById(Long id) {
        final String sql = "select I.*, C.firstName + ' ' + C.lastName as 'author' from Client C, Issue I, ClientIssue CI inner join Client C2 on CI.client_id = C2.id where CI.issue_id = I.id and I.id = ?";
        return this.jdbcTemplate.queryForObject(sql, new IssueRowMapper(), id);
    }

    public int insertIssue(Issue issue, String username) {
//        final StringBuilder stringBuilder = new StringBuilder();
//        for (final Tag tag : issue.getTags()) {
//            stringBuilder.append(tag.name()).append(",");
//        }
        final String sql = "begin transaction declare @IssueID int; insert into Issue (title, [desc], category, subcategory) values (?, ?, ?, ?); select @IssueID = scope_identity(); insert into ClientIssue(client_id, issue_id) select c.id, @IssueID from Client c where c.email = ?; commit";
        return this.jdbcTemplate.update(sql, issue.getTitle(), issue.getDesc(), issue.getCategory(), issue.getSubCategory(), username);
    }

    public List<Issue> getIssuesByAuthorId(Long id) {
        final String sql = "select i.*, c.firstName + ' ' + c.lastName as 'author' from Issue i, Client c, ClientIssue CI INNER JOIN Client C2 on C2.id = ? and CI.client_id = C2.id";
        return this.jdbcTemplate.query(sql, new IssueRowMapper(), id);
    }

}
