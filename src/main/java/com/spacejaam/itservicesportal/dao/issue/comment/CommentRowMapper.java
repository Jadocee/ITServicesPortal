package com.spacejaam.itservicesportal.dao.issue.comment;

import com.spacejaam.itservicesportal.model.issue.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class CommentRowMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Comment(
                rs.getLong("id"),
                rs.getNString("message"),
                rs.getObject("date", LocalDateTime.class),
                rs.getString("author")
        );
    }
}
