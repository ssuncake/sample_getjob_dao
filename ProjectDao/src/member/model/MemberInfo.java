package member.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import member.dto.Job;
import member.dto.Member;
import member.dto.Skill;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfo {
    Member member;
    List<Job> job;
    List<Skill> skill;
}
