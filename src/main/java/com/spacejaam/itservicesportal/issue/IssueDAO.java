package com.spacejaam.itservicesportal.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class IssueDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    IssueDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Issue> getIssuesByState(State state) {
        final String sql = "select i.*, concat(c.firstName, ' ', c.lastName) as 'author'\n" +
                "from Issue i\n" +
                "join ClientIssue CI on i.id = CI.issue_id\n" +
                "join Client c on CI.client_id = c.id\n" +
                "where i.state = ?\n" +
                "order by date asc";
        return this.jdbcTemplate.query(sql, new IssueRowMapper(), state.name());
    }

    public Issue getIssueById(Long id) {
        final String sql = "select I.*, C.firstName + ' ' + C.lastName as 'author'\n" +
                "from Issue I\n" +
                "join ClientIssue CI on I.id = CI.issue_id\n" +
                "join Client C on CI.client_id = C.id\n" +
                "where I.id = ?";
        return this.jdbcTemplate.queryForObject(sql, new IssueRowMapper(), id);
    }

    public void insertIssue(Issue issue, String username) {
//        final StringBuilder stringBuilder = new StringBuilder();
//        for (final Tag tag : issue.getTags()) {
//            stringBuilder.append(tag.name()).append(",");
//        }
        final String sql = "begin transaction declare @IssueID int; insert into Issue (title, [desc], category, subcategory) values (?, ?, ?, ?); select @IssueID = scope_identity(); insert into ClientIssue(client_id, issue_id) select c.id, @IssueID from Client c where c.email = ?; commit";
        this.jdbcTemplate.update(sql, issue.getTitle(), issue.getDesc(), issue.category().name(), issue.subCategory().name(), username);
    }

    public List<Issue> getIssuesByAuthorId(Long id) {
//        final String sql = "select i.*, c.firstName + ' ' + c.lastName as 'author' from Issue i, Client c, ClientIssue CI INNER JOIN Client C2 on C2.id = ? and CI.client_id = C2.id";
        final String sql = "select i.*, c.firstName + ' ' + c.lastName as 'author' from Issue i join ClientIssue CI on i.id = CI.issue_id join Client C on CI.client_id = C.id where C.id = ?";
        return this.jdbcTemplate.query(sql, new IssueRowMapper(), id);
    }

  /*  public int updateIssueState(Long issueId, State state) {
        final String sql = "update Issue set state = case when ";
        return this.jdbcTemplate.update(sql, state.name(), issueId);
    }*/

    public Map<String, List<Issue>> getAllIssues() {
        final String sql = "select i.*, concat(c.firstName, ' ', c.lastName) as 'author'\n" +
                "from Issue i\n" +
                "         join ClientIssue CI on i.id = CI.issue_id\n" +
                "         join Client C on CI.client_id = C.id\n" +
                "where state != 'RESOLVED'\n" +
                "order by date asc,\n" +
                "         case\n" +
                "             when i.state = 'NEW' then '1'\n" +
                "             when i.state = 'PROGRESS' then '2'\n" +
                "             when i.state = 'COMPLETE' then '3'\n" +
                "             else i.state end asc\n" +
                "\n";
        return this.jdbcTemplate.query(sql, new IssueResultSetExtractor());
    }

}
