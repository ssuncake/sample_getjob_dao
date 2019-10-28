package offer_management.dao;

import java.util.List;

import common.dto.Offer;
import common.model.JobWithSkills;
import common.model.OfferDetail;

public interface Dao {
	public OfferDetail selectOneOffer(int number);
	public List<Offer> selectAllOffers();
	public void deleteOffer(int number);
	public void updateOffer(OfferDetail detail);

	public void deleteOfferSpecs(int number);
	public void insertOfferSpecs(int number, JobWithSkills js);
}
