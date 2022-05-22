package com.spacejaam.itservicesportal.dao.performance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PerformanceDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    PerformanceDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ResolvedCount> getResolvedCount() {
        final String sql = "select * from resolved_last_7_days";
        return this.jdbcTemplate.query(sql, new ResolvedRowMapper());
    }

    public List<UnresolvedCount> getUnresolvedCount() {
        final String sql = "select * from unresolved_count";
        return this.jdbcTemplate.query(sql, new UnresolvedRowMapper());
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
