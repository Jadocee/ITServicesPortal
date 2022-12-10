package com.spacejaam.itservicesportal.data;

import com.spacejaam.itservicesportal.models.Author;
import com.spacejaam.itservicesportal.models.Comment;
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
                new Author(
                        rs.getString("author name"),
                        rs.getString("author role"),
                        rs.getLong("author id")
                ),
                rs.getBoolean("recommended")
        );
    }
}
