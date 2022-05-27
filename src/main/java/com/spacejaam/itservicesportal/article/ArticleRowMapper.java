package com.spacejaam.itservicesportal.article;

import com.spacejaam.itservicesportal.issue.Category;
import com.spacejaam.itservicesportal.issue.State;
import com.spacejaam.itservicesportal.issue.SubCategory;
import com.spacejaam.itservicesportal.issue.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class ArticleRowMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Article article = new Article(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getNString("desc"),
                State.valueOf(rs.getString("state")),
                Category.valueOf(rs.getString("category")),
                SubCategory.valueOf(rs.getString("subcategory")),
                rs.getDate("added_to_knowledge_base_on").toLocalDate(),
                rs.getNString("solution")
        );
        final Date date = rs.getDate("resolved_on");
        if (date != null) {
            article.setResolvedOn(date.toLocalDate());
        }
        final String tags = rs.getString("tags");
        if (tags != null) {
            final Set<Tag> tagSet = new HashSet<>();
            for (final String name : tags.split(",")) {
                tagSet.add(Tag.valueOf(name));
            }
            article.setTags(tagSet);
        }
        return article;
    }
}
