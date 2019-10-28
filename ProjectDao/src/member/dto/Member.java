package member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    String id;
    String password;
    String name;
    String type;
    String auth;
    String certificate;
	@Override
	public String toString() {
		return "Member [id=" + id + ", password=" + password + ", name=" + name + ", type=" + type + ", auth=" + auth
				+ ", certificate=" + certificate + "]";
	}

    
}
