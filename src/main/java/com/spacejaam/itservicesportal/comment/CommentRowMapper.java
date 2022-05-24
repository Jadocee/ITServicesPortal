package com.spacejaam.itservicesportal.comment;

import com.spacejaam.itservicesportal.author.Author;
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
                        rs.getString("author role")
                )
        );
    }
}
