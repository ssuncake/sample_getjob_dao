package it_introduction.dao;

import java.util.List;

import common.dto.Job;
import common.dto.Skill;
import common.model.JobWithSkills;

public interface Dao {
	public void insertJob(String name, String descript);
	public Job selectJob(String name);
	public List<Job> selectAllJob();
	public void updateJob(String name, String descript);
	public void deleteJob(String name);
	
	public void insertSkill(String name, String descript);
	public Skill selectSkill(String name);
	public List<Skill> selectAllSkill(String name);
	public void updateSkill(String name, String descript);
	public void deleteSkill(String name);
	
	public JobWithSkills selectJobSkills(String name);
	
//	public void insertMilestone(String name, String descript);
//	public Skill selectMilestone(String name);
//	public List<Skill> selectAllMilestone(String name);
//	public void updateMilestone(String name, String descript);
//	public void deleteMilestone(String name);
}
