package offer_management.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import common.dto.Offer;
import common.model.JobWithSkills;
import common.model.OfferDetail;

public class DaoImpl extends JdbcDaoSupport implements Dao {

	@Override
	public OfferDetail selectOneOffer(int number) {
		OfferDetail offerWithSpecs = new OfferDetail();

		String offer_sql = "select o.no, o.begin_date, o.end_date, o.title, o.content, o.ci_img, m.name " + 
				"from offer o join member m on o.corporate_id = m.id " + 
				"where o.no = ?";
		Offer offer = 
			getJdbcTemplate().queryForObject(offer_sql, new RowMapper<Offer>() {
				@Override
				public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
					Offer offer = new Offer();
					offer.setNo(rs.getInt(1));
					offer.setBegin_date(rs.getDate(2));
					offer.setEnd_date(rs.getDate(3));
					offer.setTitle(rs.getString(4));
					offer.setContent(rs.getString(5));
					offer.setCi_img(rs.getString(6));
					offer.setCorporate_id(rs.getString(7));
					return offer;
				}
			}, new Object[] {number});
		offerWithSpecs.setOffer(offer);

		String specs_sql = "select offer_no, job_name, skill_name, seq from offer_specs";

		ArrayList<JobWithSkills> specs = getJdbcTemplate().query(specs_sql, new ResultSetExtractor<ArrayList<JobWithSkills>>() {
			ArrayList<JobWithSkills> specs = new ArrayList<>();
			@Override
			public ArrayList<JobWithSkills> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					JobWithSkills jobWithSkills = offerWithSpecs.getJobWithSkills(rs.getString(2));
					System.out.println(rs.getString(3));
					jobWithSkills.addSkill(rs.getString(3));
					specs.add(jobWithSkills);
				}
				return specs;
			}
		});
		offerWithSpecs.setSpecs(specs);
		return offerWithSpecs;
	}

	@Override
	public List<Offer> selectAllOffers() {
		String sql = "select * from " + 
				"(select o.no, o.begin_date, o.end_date, o.title, o.content, o.ci_img, m.name " + 
				"from offer o join member m on o.corporate_id = m.id " + 
				"order by o.no) " + 
				"where rownum <= 200";
		List<Offer> offers = getJdbcTemplate().query(sql, new RowMapper<Offer>() {

			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();
				offer.setNo(rs.getInt(1));
				offer.setBegin_date(rs.getDate(2));
				offer.setEnd_date(rs.getDate(3));
				offer.setTitle(rs.getString(4));
				offer.setContent(rs.getString(5));
				offer.setCi_img(rs.getString(6));
				offer.setCorporate_id(rs.getString(7));
				return offer;
			}
		});
		return offers;
	}


	@Override
	public void deleteOffer(int number) {
		String delete_offer = "delete from offer where no = ?";
		String delete_offer_specs = "delete from offer_specs where offer_no = ?";
		String delete_member_offer = "delete from member_offer where offer_no = ?";
		String delete_member_recom_offer = "delete from member_recom_offer where offer_no = ?";
		
		boolean deleteAll = false;
		Connection conn;
		String[] sqls = new String[] {delete_offer_specs, delete_member_offer, delete_member_recom_offer, delete_offer};
		int res = Arrays.stream(sqls)
			.mapToInt((sql->getJdbcTemplate().update(sql, new Object[] {number})))
			.max().orElse(0);
		if (res > 0) {
			System.out.println("삭제되었습니다.");
		}
		if (deleteAll) {
			System.out.println("공고가 삭제되었습니다.");
		}
	}

	@Override
	public void updateOffer(OfferDetail detail) {
		String update_offer = "update offer set begin_date = ?, end_date = ?, title = ?, content = ?, ci_img = ?, corporate_id = ? "
				+ "where no = ?";
		Offer o = detail.getOffer();
		int res = getJdbcTemplate().update(update_offer, new Object[] {o.getBegin_date(), o.getEnd_date(), o.getTitle(),
															o.getContent(), o.getCi_img(), o.getCorporate_id(), o.getNo()});
		deleteOfferSpecs(o.getNo());
		detail.getSpecs().stream().forEach(js->insertOfferSpecs(o.getNo(), js));
	}

	public void insertOfferSpecs(int number, JobWithSkills js) {
		String sql = "insert into offer_specs values(?, ?, ?, ?)";
		js.getSkills().stream().forEach(skill->getJdbcTemplate().update(sql, new Object[] {js.getJobName(), skill}));
	}

	@Override
	public void deleteOfferSpecs(int number) {
		String delete_offer_specs = "delete from offer_specs where offer_no = ?";
		getJdbcTemplate().update(delete_offer_specs, new Object[] {number});
	}

	

//	@Override
//	public Job selectJob(String name) {
//		String sql = "select * from job where name = ?";
//		Job job = getJdbcTemplate().queryForObject(sql, new RowMapper<Job>() {
//			@Override
//			public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Job job = new Job();
//				job.setName(rs.getString(1));
//				job.setDescript(rs.getString(2));
//				return job;
//			}
//		}, new Object[] {name});
//		return job;
//	}
//
//	@Override
//	public void insertJob(String name, String descript) {
//		String sql = "insert into job(name, descript) values(?, ?)";
//		getJdbcTemplate().update(sql, new Object[] {name, descript});
//	}
//
//	@Override
//	public void updateJob(String name, String descript) {
//		String sql = "update job set descript = ? where name = ?";
//		getJdbcTemplate().update(sql, new Object[] {descript, name});
//	}
//
//	@Override
//	public void deleteJob(String name) {
//		String sql = "delete job where name = ?";
//		getJdbcTemplate().update(sql, new Object[] {name});
//	}
//
//	@Override
//	public List<Job> selectAllJob() {
//		String sql = "select * from job";
//		List<Job> jobs = getJdbcTemplate().query(sql, new RowMapper<Job>() {
//			@Override
//			public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Job job = new Job();
//				job.setName(rs.getString(1));
//				job.setDescript(rs.getString(2));
//				return job;
//			}
//		});
//		return jobs;
//	}
//	
//	@Override
//	public void insertSkill(String name, String descript) {
//		String sql = "insert into skill(name, descript) values(?, ?)";
//		getJdbcTemplate().update(sql, new Object[] {name, descript});
//	}
//
//	@Override
//	public Skill selectSkill(String name) {
//		String sql = "select * from skill where name = ?";
//		Skill skill = getJdbcTemplate().queryForObject(sql, new RowMapper<Skill>() {
//			@Override
//			public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Skill skill = new Skill();
//				skill.setName(rs.getString(1));
//				skill.setDescript(rs.getString(2));
//				return skill;
//			}
//		}, new Object[] {name});
//		return skill;
//	}
//
//	@Override
//	public List<Skill> selectAllSkill(String name) {
//		String sql = "select * from skill";
//		List<Skill> skills = getJdbcTemplate().query(sql, new RowMapper<Skill>() {
//			@Override
//			public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Skill skill = new Skill();
//				skill.setName(rs.getString(1));
//				skill.setDescript(rs.getString(2));
//				return skill;
//			}
//		});
//		return skills;
//	}
//
//	@Override
//	public void updateSkill(String name, String descript) {
//		String sql = "update skill set descript = ? where name = ?";
//		getJdbcTemplate().update(sql, new Object[] {descript, name});
//	}
//
//	@Override
//	public void deleteSkill(String name) {
//		String sql = "delete skill where name = ?";
//		getJdbcTemplate().update(sql, new Object[] {name});
//	}
//
//	@Override
//	public JobWithSkills selectJobSkills(String name) {
//		String sql = "select * from job_skill where job_name = ?";
//		JobWithSkills result = 
//			getJdbcTemplate().query(sql, new ResultSetExtractor<JobWithSkills>() {
//				JobWithSkills jobWithSkills = new JobWithSkills(name);
//
//				@Override
//				public JobWithSkills extractData(ResultSet rs) throws SQLException, DataAccessException {
//					while(rs.next()) {
//						jobWithSkills.addSkill(rs.getString(2));
//					}
//					return jobWithSkills;
//				}
//				
//			}, new Object[] {name});
//		return result;
//	}

}
