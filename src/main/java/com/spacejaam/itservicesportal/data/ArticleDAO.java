package com.spacejaam.itservicesportal.data;

import com.spacejaam.itservicesportal.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    ArticleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Article> getKnowledgeBaseArticles() {
        final String sql = "select K.id,\n" +
                "       I.title,\n" +
                "       I.[desc],\n" +
                "       I.state,\n" +
                "       I.category,\n" +
                "       I.subcategory,\n" +
                "       I.tags,\n" +
                "       I.resolved_date as 'resolved_on',\n" +
                "       K.date_added    as 'added_to_knowledge_base_on',\n" +
                "       C.message as 'solution',\n" +
                "       C.date as 'solution_provided_on'\n" +
                "from KnowledgeBase K\n" +
                "         join Issue I on K.issue_id = I.id\n" +
                "         join IssueComment IC on I.id = IC.issue_id\n" +
                "         join Comment C on IC.comment_id = C.id\n" +
                "where C.recommended = 1;";
        return this.jdbcTemplate.query(sql, new ArticleRowMapper());
    }

    public Article getArticleById(Long id) {
        final String sql = "select K.id,\n" +
                "       I.title,\n" +
                "       I.[desc],\n" +
                "       I.state,\n" +
                "       I.category,\n" +
                "       I.subcategory,\n" +
                "       I.tags,\n" +
                "       I.resolved_date as 'resolved_on',\n" +
                "       K.date_added    as 'added_to_knowledge_base_on',\n" +
                "       C.message as 'solution',\n" +
                "       C.date as 'solution_provided_on'\n" +
                "from KnowledgeBase K\n" +
                "         join Issue I on K.issue_id = I.id\n" +
                "         join IssueComment IC on I.id = IC.issue_id\n" +
                "         join Comment C on IC.comment_id = C.id\n" +
                "where K.id = ? and C.recommended = 1;";
        return this.jdbcTemplate.queryForObject(sql, new ArticleRowMapper(), id);
    }


}
