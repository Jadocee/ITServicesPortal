package com.spacejaam.itservicesportal.data;

import com.spacejaam.itservicesportal.enums.Category;
import com.spacejaam.itservicesportal.enums.State;
import com.spacejaam.itservicesportal.enums.SubCategory;
import com.spacejaam.itservicesportal.enums.Tag;
import com.spacejaam.itservicesportal.models.Article;
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
                rs.getNString("solution"),
                rs.getDate("solution_provided_on").toLocalDate()
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
