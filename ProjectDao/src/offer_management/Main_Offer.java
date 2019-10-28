package offer_management;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.dto.Offer;
import common.model.JobWithSkills;
import common.model.OfferDetail;
import offer_management.dao.Dao;

public class Main_Offer {
	// github webhook test
	public static void main(String[] args) {
		ApplicationContext factory 
			= new ClassPathXmlApplicationContext("beans.xml");
		Dao dao = (Dao) factory.getBean("Offer_management_DaoImpl");

//		delete_contents(dao);
//		get_offer_list(dao);
//		get_offer(dao);
		

	}

	private static void delete_contents(Dao dao) {
		dao.deleteOffer(101);
		dao.deleteOffer(102);
		dao.deleteOffer(103);
		dao.deleteOffer(104);
	}

	private static void get_offer_list(Dao dao) {
		List<Offer> offers = dao.selectAllOffers();
		System.out.printf("%-8s%-32s%-14s%-44s%-10s\n", "게시물번호", "제목", "내용", "기업로고 ", "마감일");
		for (Offer offer : offers) {
			System.out.print(offer.getNo() + "\t");
			System.out.print(offer.getTitle() + "\t");
			System.out.print(offer.getContent() + "\t");
			System.out.print(offer.getCi_img() + "\t");
			System.out.print(offer.getEnd_date() + "\t");
			System.out.println();
		}
	}

	static void get_offer(Dao dao) {
		OfferDetail offerWithSpecs = dao.selectOneOffer(101);
		System.out.println("-------채용공고---------");
		System.out.println(offerWithSpecs.getOffer().getNo());
		System.out.println(offerWithSpecs.getOffer().getTitle());
		System.out.println(offerWithSpecs.getOffer().getContent());
		System.out.println(offerWithSpecs.getOffer().getCi_img());
		System.out.println(offerWithSpecs.getOffer().getEnd_date());
		System.out.println("----------------------");
		for (JobWithSkills j : offerWithSpecs.getSpecs()) {
			for (String s : j.getSkills()) {
				System.out.println(j.getJobName() + ":" + s);
			}
		}
		System.out.println("----------------------");
	}
}
