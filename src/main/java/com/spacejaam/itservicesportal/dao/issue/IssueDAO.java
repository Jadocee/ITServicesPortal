package com.spacejaam.itservicesportal.dao.issue;

import com.spacejaam.itservicesportal.dao.issue.comment.CommentRowMapper;
import com.spacejaam.itservicesportal.model.issue.Comment;
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

    public List<Comment> getCommentsByIssueId(Long id) {
        final String sql = "select c.*, cl.firstName + ' ' + cl.lastName as 'author' from Comment c join IssueComment IC on c.id = IC.comment_id join ClientComment CC on c.id = CC.comment_id join Client cl on CC.client_id = cl.id where IC.issue_id = ?";
        return this.jdbcTemplate.query(sql, new CommentRowMapper(), id);
    }

    public void insertComment(Long issueId, String authorUsername, Comment comment) {
        final String sql = "begin transaction declare @CommentID int; declare @IssueID int; insert into Comment (message) values (?); select @CommentID = scope_identity(); select @IssueID = ?; declare @ClientID int; select @ClientID = id from Client where email = ?; insert into ClientComment (client_id, comment_id) values (@ClientID, @CommentID); insert into IssueComment (issue_id, comment_id) values (@IssueID, @CommentID); update Issue set state = case when (state = 'NEW' and (select c.role from Client c where c.id = @ClientID) = 'ITSTAFF') then 'PROGRESS' else state end where id = @IssueID; commit";
        this.jdbcTemplate.update(sql, comment.getMessage(), issueId, authorUsername);
    }

  /*  public int updateIssueState(Long issueId, State state) {
        final String sql = "update Issue set state = case when ";
        return this.jdbcTemplate.update(sql, state.name(), issueId);
    }*/

}
