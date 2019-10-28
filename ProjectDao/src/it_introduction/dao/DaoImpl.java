package it_introduction.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import common.dto.Job;
import common.dto.Skill;
import common.model.JobWithSkills;

public class DaoImpl extends JdbcDaoSupport implements Dao {

	@Override
	public Job selectJob(String name) {
		String sql = "select * from job where name = ?";
		Job job = getJdbcTemplate().queryForObject(sql, new RowMapper<Job>() {
			@Override
			public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
				Job job = new Job();
				job.setName(rs.getString(1));
				job.setDescript(rs.getString(2));
				return job;
			}
		}, new Object[] {name});
		return job;
	}

	@Override
	public void insertJob(String name, String descript) {
		String sql = "insert into job(name, descript) values(?, ?)";
		getJdbcTemplate().update(sql, new Object[] {name, descript});
	}

	@Override
	public void updateJob(String name, String descript) {
		String sql = "update job set descript = ? where name = ?";
		getJdbcTemplate().update(sql, new Object[] {descript, name});
	}

	@Override
	public void deleteJob(String name) {
		String sql = "delete job where name = ?";
		getJdbcTemplate().update(sql, new Object[] {name});
	}

	@Override
	public List<Job> selectAllJob() {
		String sql = "select * from job";
		List<Job> jobs = getJdbcTemplate().query(sql, new RowMapper<Job>() {
			@Override
			public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
				Job job = new Job();
				job.setName(rs.getString(1));
				job.setDescript(rs.getString(2));
				return job;
			}
		});
		return jobs;
	}
	
	@Override
	public void insertSkill(String name, String descript) {
		String sql = "insert into skill(name, descript) values(?, ?)";
		getJdbcTemplate().update(sql, new Object[] {name, descript});
	}

	@Override
	public Skill selectSkill(String name) {
		String sql = "select * from skill where name = ?";
		Skill skill = getJdbcTemplate().queryForObject(sql, new RowMapper<Skill>() {
			@Override
			public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
				Skill skill = new Skill();
				skill.setName(rs.getString(1));
				skill.setDescript(rs.getString(2));
				return skill;
			}
		}, new Object[] {name});
		return skill;
	}

	@Override
	public List<Skill> selectAllSkill(String name) {
		String sql = "select * from skill";
		List<Skill> skills = getJdbcTemplate().query(sql, new RowMapper<Skill>() {
			@Override
			public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
				Skill skill = new Skill();
				skill.setName(rs.getString(1));
				skill.setDescript(rs.getString(2));
				return skill;
			}
		});
		return skills;
	}

	@Override
	public void updateSkill(String name, String descript) {
		String sql = "update skill set descript = ? where name = ?";
		getJdbcTemplate().update(sql, new Object[] {descript, name});
	}

	@Override
	public void deleteSkill(String name) {
		String sql = "delete skill where name = ?";
		getJdbcTemplate().update(sql, new Object[] {name});
	}

	@Override
	public JobWithSkills selectJobSkills(String name) {
		String sql = "select * from job_skill where job_name = ?";
		JobWithSkills result = 
			getJdbcTemplate().query(sql, new ResultSetExtractor<JobWithSkills>() {
				JobWithSkills jobWithSkills = new JobWithSkills(name);

				@Override
				public JobWithSkills extractData(ResultSet rs) throws SQLException, DataAccessException {
					while(rs.next()) {
						jobWithSkills.addSkill(rs.getString(2));
					}
					return jobWithSkills;
				}
				
			}, new Object[] {name});
		return result;
	}

}
