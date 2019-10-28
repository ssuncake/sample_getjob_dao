package member.dao;


import member.dto.Job;
import member.dto.Member;
import member.dto.Skill;
import member.model.MemberInfo;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;


public class MemberDAOImpl extends JdbcDaoSupport implements MemberDAO {

    @Override
    public MemberInfo get_member_info(String id) {
        String sql = "select * from member join (SELECT * from member_job join member_skill using (id) where id = ? ) using (id)";
        MemberInfo memberInfoList = null;
        try {
        	  memberInfoList = getJdbcTemplate().queryForObject(sql, new RowMapper<MemberInfo>() {
                 @Override
                 public MemberInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                     MemberInfo memberInfo = new MemberInfo();

                     Member member = new Member(rs.getString(1),
                             rs.getString(2),
                             rs.getString(3),
                             rs.getString(4),
                             rs.getString(5),
                             rs.getString(6)
                     );
                     Job job = new Job(rs.getString(7), "");
                     List<Job> jobs = new ArrayList<>();
                     jobs.add(job);

                     Skill skill = new Skill(rs.getString(8), "");
                     List<Skill> skills = new ArrayList<>();
                     skills.add(skill);

                     memberInfo.setMember(member);
                     memberInfo.setJob(jobs);
                     memberInfo.setSkill(skills);

                     return memberInfo;
                 }
             }, new Object[]{id});
        }catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
       
        return memberInfoList;
    }

    @Override
    public void update_password(String id, String password) {
        String sql = "UPDATE member SET password = ? WHERE id = ?";
        getJdbcTemplate().update(sql, new Object[] {id, password});
    }

    @Override
    public void update_auth(String id, String auth) {
        String sql = "UPDATE member SET auth=? where id = ?";
        getJdbcTemplate().update(sql, new Object[] {id, auth});
    }

    @Override
    public int add_member_skill(String id, Skill skill) {
        String sql = "INSERT INTO member_skill VALUES (?, ?);";
        getJdbcTemplate().update(sql, new Object[] {id, skill.getName()});
        return 0;
    }

    @Override
    public int delete_member_skill(String id, Skill skill) {
        String sql = "DELETE FROM member_skill WHERE id=? AND name=?";
        getJdbcTemplate().update(sql, new Object[] {id, skill.getName()});
        return 0;
    }

    @Override
    public int add_member_job(String id, Job job) {
        String sql = "INSERT INTO member_job VALUES(?, ?)";
        getJdbcTemplate().update(sql, new Object[] {id, job.getName()});
        return 0;
    }

    @Override
    public int delete_member_job(String id, Job job) {
        String sql = "DELETE FROM member_job WHERE id=? AND name=?";
        getJdbcTemplate().update(sql, new Object[] {id, job.getName()});
        return 0;
    }

    @Override
    public int add_member(String id, String password, String type,String name, String auth, String certificate) {
        String sql = "insert into member(id, password, name, type, auth, certificate) values(?,?,?,?,?,?)";
        getJdbcTemplate().update(sql, new Object[] {id, password, name, type, auth, certificate});
        return 0;
    }

    @Override
    public int delete_member(String id, String password) {
        String sql = "delete member where id = ? AND password = ?";
        getJdbcTemplate().update(sql, new Object[] {id, password});
        return 0;
    }

    //    public MemberInfo get_member_info_pro(String id) {
//
//        List<SqlParameter> parameters = Arrays.asList(
//                new SqlParameter(Types.VARCHAR), new SqlOutParameter("res_cursor", Types.REF_CURSOR));
//
//        Map<String, Object> t = getJdbcTemplate().call(new CallableStatementCreator() {
//            @Override
//            public CallableStatement createCallableStatement(Connection con) throws SQLException {
//                CallableStatement callableStatement = con.prepareCall("{call MEMBER_FIND (?, ?)}");
//                callableStatement.setString(1, id);
//                callableStatement.registerOutParameter(2, Types.REF_CURSOR);
//                return callableStatement;
//            }
//        }, parameters);
////        List<MemberInfo> memberInfolist = new ArrayList<>();
//        System.out.println(t.get("res_cursor").getClass());
//        return new MemberInfo();
//    }

}
