package member;

import member.dao.MemberDAO;
import member.dto.Job;
import member.dto.Skill;
import member.model.MemberInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        MemberDAO dao = applicationContext.getBean("Member_DaoImpl",MemberDAO.class);
        String id = "jung@gmail.com";
        String id_new = "EE@gmail.com";
        String password1 = "3331";
        String password2 = "22222";

        String job_name_backend = "backend";
        String job_name_frontend = "frontend";
        Job backend = new Job(job_name_backend, "");
        Job frontend = new Job(job_name_frontend, "");

        String python_name = "python";
        String cplusplus = "c++";
        Skill python = new Skill(python_name,"");


        //아이디로 회원정보 찾기
        prn_member_info(id, dao);


        dao.add_member_job(id_new,backend);
        prn_member_info(id_new, dao);

//        dao.add_member_skill(id_new,python);
//        dao.delete_member_job(id_new,backend);
//        dao.delete_member_skill(id_new,python);
//        dao.update_auth(id_new,"프로필작성완료");
//        dao.update_password(id_new, password1);


        ((ClassPathXmlApplicationContext)applicationContext).close();

    }

    public static void prn_member_info(String id, MemberDAO dao){
        MemberInfo memberInfo = dao.get_member_info(id);
        System.out.println(memberInfo.getMember());
        System.out.println(memberInfo.getJob());
        System.out.println(memberInfo.getSkill());
    }


}
