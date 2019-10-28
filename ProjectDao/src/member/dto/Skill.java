package member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    String name;
    String decription;

	@Override
	public String toString() {
		return "Skill [name=" + name + ", decription=" + decription + "]";
	}
    
    
}
