package com.spacejaam.itservicesportal.comment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    CommentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertComment(Long issueId, String authorUsername, Comment comment) {
        final String sql = "begin transaction declare @CommentID int; declare @IssueID int; insert into Comment (message) values (?); select @CommentID = scope_identity(); select @IssueID = ?; declare @ClientID int; select @ClientID = id from Client where email = ?; insert into ClientComment (client_id, comment_id) values (@ClientID, @CommentID); insert into IssueComment (issue_id, comment_id) values (@IssueID, @CommentID); update Issue set state = case when (state = 'NEW' and (select c.role from Client c where c.id = @ClientID) = 'ITSTAFF') then 'PROGRESS' else state end where id = @IssueID; commit";
        this.jdbcTemplate.update(sql, comment.getMessage(), issueId, authorUsername);
    }

    public List<Comment> getCommentsByIssueId(Long id) {
        final String sql = "select c.*, cl.firstName + ' ' + cl.lastName as 'author name', cl.role as 'author role'\n" +
                "from Comment c\n" +
                "         join IssueComment IC on c.id = IC.comment_id\n" +
                "         join ClientComment CC on c.id = CC.comment_id\n" +
                "         join Client cl on CC.client_id = cl.id\n" +
                "where IC.issue_id = ?\n" +
                "order by date asc";
        return this.jdbcTemplate.query(sql, new CommentRowMapper(), id);
    }
}
