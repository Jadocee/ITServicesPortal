package com.spacejaam.itservicesportal.dao.performance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PerformanceDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    PerformanceDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getResolvedCount() {
        final String sql = "select * from resolved_last_7_days";
        return this.jdbcTemplate.query(sql, new ResolvedRowMapper());
    }

    public Map<String, Integer> getUnresolvedCount() {
        final String sql = "select *\n" +
                "from unresolved_count\n" +
                "order by case\n" +
                "             when [category] = 'NETWORK' then '1'\n" +
                "             when [category] = 'SOFTWARE' then '2'\n" +
                "             when [category] = 'HARDWARE' then '3'\n" +
                "             when [category] = 'ACCOUNT' then '4'\n" +
                "             when [category] = 'EMAIL' then '5'\n" +
                "             else [category]\n" +
                "             end";
        return this.jdbcTemplate.query(sql, new UnresolvedResultSetExtractor());
//        return this.jdbcTemplate.queryForList(sql);
    }

    public Double getStressRate() {
        final String sql = "begin transaction\n" +
                "    declare @unresolvedCount float;\n" +
                "    declare @itstaffCount float;\n" +
                "    select\n" +
                "        @itstaffCount = cast((sum(case when role = 'ITSTAFF' then 1 else 0 end) * 5) as float)\n" +
                "    from\n" +
                "        Client;\n" +
                "    select\n" +
                "        @unresolvedCount = cast(sum(case when (state = 'NEW' or state = 'PROGRESS' or state = 'COMPLETE') then 1 else 0 end) as float)\n" +
                "    from\n" +
                "        Issue;\n" +
                "    select @unresolvedCount / @itstaffCount as [stress rate];\n" +
                "commit";
        return this.jdbcTemplate.queryForObject(sql, Double.class);
    }

}
