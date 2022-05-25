package com.spacejaam.itservicesportal.comment;

import com.spacejaam.itservicesportal.author.Author;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class CommentResultSetExtractor implements ResultSetExtractor<Map<String, Object>> {
    @Override
    public Map<String, Object> extractData(ResultSet rs) throws SQLException, DataAccessException {
        final Map<String, Object> commentListMap = new HashMap<>();
        final ArrayList<Comment> comments = new ArrayList<>();
        while (rs.next()) {
            if (rs.getBoolean("recommended")) {
                commentListMap.put("recommended", new Comment(
                        rs.getLong("id"),
                        rs.getNString("message"),
                        rs.getObject("date", LocalDateTime.class),
                        new Author(
                                rs.getString("author name"),
                                rs.getString("author role"),
                                rs.getLong("author id")
                        )
                ));
            } else {
                comments.add(new Comment(
                        rs.getLong("id"),
                        rs.getNString("message"),
                        rs.getObject("date", LocalDateTime.class),
                        new Author(
                                rs.getString("author name"),
                                rs.getString("author role"),
                                rs.getLong("author id")
                        )
                ));
            }
        }
        commentListMap.put("comments", comments);
        return commentListMap;
    }
}
