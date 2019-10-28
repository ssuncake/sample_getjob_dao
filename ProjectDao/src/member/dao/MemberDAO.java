package member.dao;

import member.dto.Job;
import member.dto.Skill;
import member.model.MemberInfo;

import java.util.List;
import java.util.Map;

public interface MemberDAO {
//    MemberInfo get_member_info = "{call member_find(?,?)}";
//List<MemberInfo> get_member_info(String id);
MemberInfo get_member_info(String id);
    void update_password(String id, String password);
    void update_auth(String id, String auth);
    int add_member_skill(String id, Skill skill);
    int delete_member_skill(String id, Skill skill);
    int add_member_job(String id, Job job);
    int delete_member_job(String id, Job job);
    int add_member(String id, String password,  String type,  String auth, String certificate);
}
