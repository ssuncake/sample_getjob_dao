package member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    String name;
    String decription;

	@Override
	public String toString() {
		return "Job [name=" + name + ", decription=" + decription + "]";
	}
    
    
}
