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
        final String sql = "select i.*, concat(c.firstName, ' ', c.lastName) as 'author name', c.id as 'author id', c.role as 'author role'\n" +
                "from Issue i\n" +
                "join ClientIssue CI on i.id = CI.issue_id\n" +
                "join Client c on CI.client_id = c.id\n" +
                "where i.state = ?\n" +
                "order by date asc";
        return this.jdbcTemplate.query(sql, new IssueRowMapper(), state.name());
    }

    public Issue getIssueById(Long id) {
        final String sql = "select I.*, C.firstName + ' ' + C.lastName as 'author name', c.id as 'author id', c.role as 'author role'\n" +
                "from Issue I\n" +
                "join ClientIssue CI on I.id = CI.issue_id\n" +
                "join Client C on CI.client_id = C.id\n" +
                "where I.id = ?";
        return this.jdbcTemplate.queryForObject(sql, new IssueRowMapper(), id);
    }

    public void insertIssue(Issue issue, String username) {
        final String sql = "begin transaction declare @IssueID int; insert into Issue (title, [desc], category, subcategory) values (?, ?, ?, ?); select @IssueID = scope_identity(); insert into ClientIssue(client_id, issue_id) select c.id, @IssueID from Client c where c.email = ?; commit";
        this.jdbcTemplate.update(sql, issue.getTitle(), issue.getDesc(), issue.category().name(), issue.subCategory().name(), username);
    }

    public List<Issue> getIssuesByAuthorId(Long id) {
//        final String sql = "select i.*, c.firstName + ' ' + c.lastName as 'author' from Issue i, Client c, ClientIssue CI INNER JOIN Client C2 on C2.id = ? and CI.client_id = C2.id";
        final String sql = "select i.*, c.firstName + ' ' + c.lastName as 'author name', c.id as 'author id', c.role as 'author role' from Issue i join ClientIssue CI on i.id = CI.issue_id join Client C on CI.client_id = C.id where C.id = ?";
        return this.jdbcTemplate.query(sql, new IssueRowMapper(), id);
    }

    public Map<String, List<Issue>> getAllIssues() {
        final String sql = "select i.*, concat(c.firstName, ' ', c.lastName) as 'author name', c.id as 'author id', c.role as 'author role'\n" +
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

    public void updateIssueTag(Long issueId, Tag tag) {
        final String sql = "begin transaction\n" +
                "declare @tag varchar(10)\n" +
                "select @tag = ?;\n" +
                "declare @issueId int;\n" +
                "select @issueId = ?;\n" +
                "update Issue\n" +
                "set tags = case when tags is null then @tag else concat(tags, ',', @tag) end\n" +
                "where id = @issueId;\n" +
                "if @tag = 'ARTICLE'\n" +
                "insert into KnowledgeBase (issue_id) values (@issueId);\n" +
                "commit";
        this.jdbcTemplate.update(sql, tag.name(), issueId);
    }

    public void removeTag(Long issueId, Tag tag) {
        final String sql = "begin transaction\n" +
                "declare @tagToRemove varchar(10)\n" +
                "select @tagToRemove = ?;\n" +
                "declare @issueId int;\n" +
                "select @issueId = ?;\n" +
                "declare @currentTags varchar(30);\n" +
                "declare @tag varchar(10);\n" +
                "declare @pos int;\n" +
                "declare @table table\n" +
                "               (\n" +
                "                   [name] [varchar](10)\n" +
                "               );\n" +
                "select @currentTags = (select tags from Issue where id = @issueId);\n" +
                "while (charindex(',', @currentTags) > 0)\n" +
                "    begin\n" +
                "        select @pos = charindex(',', @currentTags);\n" +
                "        select @tag = substring(@currentTags, 1, @pos - 1);\n" +
                "        if @tag != @tagToRemove\n" +
                "            begin\n" +
                "                insert into @table select @tag;\n" +
                "            end\n" +
                "        select @currentTags = substring(@currentTags, @pos + 1, len(@currentTags) - @pos);\n" +
                "    end\n" +
                "if @currentTags != @tagToRemove\n" +
                "    begin\n" +
                "        insert into @table\n" +
                "        select @currentTags;\n" +
                "    end\n" +
                "update Issue\n" +
                "set tags = (select string_agg(isnull(name, ' '), ',') as 'tags' from @table)\n" +
                "where id = @issueId\n" +
                "if not exists(select * from @table where name = 'ARTICLE')\n" +
                "    begin\n" +
                "        delete from KnowledgeBase where issue_id = @issueId;\n" +
                "    end\n" +
                "commit";
        this.jdbcTemplate.update(sql, tag.name(), issueId);
    }

    public void acceptSolution(Long issueId, Long commentId) {
        final String sql = "begin transaction\n" +
                "declare @issueId int;\n" +
                "declare @commentId int;\n" +
                "select @issueId = ?, @commentId = ?;\n" +
                "declare @currentTags varchar(30);\n" +
                "declare @tag varchar(10);\n" +
                "declare @pos int;\n" +
                "declare @table table\n" +
                "               (\n" +
                "                   [name] [varchar](10)\n" +
                "               );\n" +
                "select @currentTags = (select tags from Issue where id = @issueId);\n" +
                "while (charindex(',', @currentTags) > 0)\n" +
                "    begin\n" +
                "        select @pos = charindex(',', @currentTags);\n" +
                "        select @tag = substring(@currentTags, 1, @pos - 1);\n" +
                "        insert into @table select @tag;\n" +
                "        select @currentTags = substring(@currentTags, @pos + 1, len(@currentTags) - @pos);\n" +
                "    end\n" +
                "insert into @table\n" +
                "select @currentTags;\n" +
                "if not exists(select *\n" +
                "              from @table\n" +
                "              where name = 'ARTICLE')\n" +
                "    begin\n" +
                "        update Issue\n" +
                "        set state = 'RESOLVED',\n" +
                "            tags  = IIF((select * from @table where name = 'ARTICLE') is not null, concat(tags, ',ARTICLE'), tags),\n" +
                "            resolved_date = getutcdate()" +
                "        where id = @issueId;\n" +
                "    end\n" +
                "if not exists(select *\n" +
                "              from KnowledgeBase\n" +
                "              where issue_id = @issueId)\n" +
                "    begin\n" +
                "        insert into KnowledgeBase(issue_id) values (@issueId);\n" +
                "    end\n" +
                "commit";
        this.jdbcTemplate.update(sql, issueId, commentId);
    }

    public void rejectSolution(Long issueId, Long commentId) {
        final String sql = "begin transaction\n" +
                "declare @issueId int;\n" +
                "declare @commentId int;\n" +
                "select @issueId = ?, @commentId = ?;\n" +
                "declare @currentTags varchar(30);\n" +
                "declare @tag varchar(10);\n" +
                "declare @pos int;\n" +
                "declare @table table\n" +
                "               (\n" +
                "                   [name] [varchar](10)\n" +
                "               );\n" +
                "select @currentTags = (select tags from Issue where id = @issueId);\n" +
                "while (charindex(',', @currentTags) > 0)\n" +
                "    begin\n" +
                "        select @pos = charindex(',', @currentTags);\n" +
                "        select @tag = substring(@currentTags, 1, @pos - 1);\n" +
                "        if @tag != 'ARTICLE'\n" +
                "            begin\n" +
                "                insert into @table select @tag;\n" +
                "            end\n" +
                "        select @currentTags = substring(@currentTags, @pos + 1, len(@currentTags) - @pos);\n" +
                "    end\n" +
                "if @currentTags != 'ARTICLE'\n" +
                "    begin\n" +
                "        insert into @table select @currentTags;\n" +
                "    end\n" +
                "update Issue\n" +
                "set state = 'PROGRESS',\n" +
                "    tags  = (select string_agg(isnull(name, ' '), ',') as 'tags'\n" +
                "             from @table),\n" +
                "    resolved_date = default\n" +
                "where id = @issueId;\n" +
                "if exists(select *\n" +
                "          from KnowledgeBase\n" +
                "          where issue_id = @issueId)\n" +
                "    begin\n" +
                "        delete from KnowledgeBase where issue_id = @issueId;\n" +
                "    end\n" +
                "update Comment\n" +
                "set recommended = 0\n" +
                "where id = @commentId;\n" +
                "commit";
        this.jdbcTemplate.update(sql, issueId, commentId);
    }


}
