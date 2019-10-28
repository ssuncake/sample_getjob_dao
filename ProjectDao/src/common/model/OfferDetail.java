package common.model;

import java.util.ArrayList;
import java.util.List;

import common.dto.Offer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferDetail {
	Offer offer;
	List<JobWithSkills> specs = new ArrayList<>();
	
	public JobWithSkills getJobWithSkills(String jobName) {
		return specs.stream()
				.filter(j->j.getJobName().equals(jobName))
				.findAny().orElse(new JobWithSkills(jobName));
	}
}
