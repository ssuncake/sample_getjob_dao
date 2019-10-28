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

//		dao.insertJob("���ü�� ������", "���ü��...... �� �����ϴ�...");
//		dao.updateJob("���ü�� ������", "���ü�� �����ڴ¿�...");
//		dao.deleteJob("���ü�� ������");

//		Skill skill = dao.selectSkill("java");
//		System.out.printf(skill.getName() +" : " + skill.getDescript());
//
//		dao.insertSkill("html", "html�� ����ȭ�� ���� ������ �ۼ��ϴ� ��ũ�� ����..");
//		dao.updateSkill("html", "html�� ������ ������...");
//		dao.deleteJob("html");

		JobWithSkills jobWithSkills = dao.selectJobSkills("����Ʈ����");
		
		String jobName = jobWithSkills.getJobName();
		for (String skillName : jobWithSkills.getSkills()) {
			System.out.println(jobName + " : " + skillName);
		}

		((ClassPathXmlApplicationContext)factory).close();
	}
}
