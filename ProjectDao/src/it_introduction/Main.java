package it_introduction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.dto.Skill;
import common.model.JobWithSkills;
import it_introduction.dao.Dao;

public class Main {
	public static void main(String[] args) {
		ApplicationContext factory 
			= new ClassPathXmlApplicationContext("beans.xml");
		Dao dao = (Dao) factory.getBean("IT_Introduction_DaoImpl");
		
//		Job job = dao.selectJob("backend");
//		System.out.printf(job.getName() +" : " + job.getDescript());

//		dao.insertJob("블록체인 개발자", "블록체인...... 을 개발하는...");
//		dao.updateJob("블록체인 개발자", "블록체인 개발자는요...");
//		dao.deleteJob("블록체인 개발자");

//		Skill skill = dao.selectSkill("java");
//		System.out.printf(skill.getName() +" : " + skill.getDescript());
//
//		dao.insertSkill("html", "html은 구조화된 문서 형식을 작성하는 마크업 언어로..");
//		dao.updateSkill("html", "html은 문서의 구조를...");
//		dao.deleteJob("html");

		JobWithSkills jobWithSkills = dao.selectJobSkills("프론트엔드");
		
		String jobName = jobWithSkills.getJobName();
		for (String skillName : jobWithSkills.getSkills()) {
			System.out.println(jobName + " : " + skillName);
		}

		((ClassPathXmlApplicationContext)factory).close();
	}
}
