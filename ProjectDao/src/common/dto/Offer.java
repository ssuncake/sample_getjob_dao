package common.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Offer {
	int no;
	Date begin_date;
	Date end_date;
	String title;
	String content;
	String ci_img;
	String corporate_id;
}
