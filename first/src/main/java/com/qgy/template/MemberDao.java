package com.qgy.template;

import java.sql.ResultSet;
import java.util.List;

/**
 * @Author: qgy
 * @Date: 2018/3/16 15:54
 * @Description:
 */
public class MemberDao {

    private JdbcTemplate jdbcTemplate=new JdbcTemplate(null);
    public List<?> query() throws Exception {
        String sql="select * from t_member";
        return jdbcTemplate.executeQuery(sql, new RowMapper<Member>() {

            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws Exception {
                Member member=new Member();
                member.setUsername(rs.getString("USERNANE"));
                member.setPassword(rs.getString("password"));
                member.setAge(rs.getInt("age"));
                member.setAddr(rs.getString("addr"));
                return member;

            }
        },null);
    }
}
