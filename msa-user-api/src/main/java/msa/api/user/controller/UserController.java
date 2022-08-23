package msa.api.user.controller;

import msa.api.user.vo.Code;
import msa.api.user.vo.CodeUser;
import msa.api.user.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CodeRepository codeRepository;

//    @Autowired
//    CodeRepository codeRepository;

    @ResponseBody
    @GetMapping("/api/users1")
    public List<Member> getUsers1(HttpServletRequest request) {

        List<Member> members = new ArrayList<Member>();
        Member member = new Member();
        member.setName("test1111");
        members.add(member);

        return members;
    }

    @ResponseBody
    @GetMapping("/api/users")
    public List<Member> getUsers(HttpServletRequest request) {
        return memberRepository.findAll();
    }

    @ResponseBody
    @GetMapping("/api/codes")
    public List<Code> getCodes(HttpServletRequest request) {
        return codeRepository.findAll();
    }

    @ResponseBody
    @GetMapping("/api/codeUsers")
    public List<CodeUser> getCodeUsers(HttpServletRequest request) {

        List<CodeUser> codeUsers = new ArrayList<CodeUser>();

        CodeUser codeUser = new CodeUser();
        codeUsers.add(codeUser);

        codeUser.setCode(codeRepository.findAll().get(0));
        codeUser.setMember(memberRepository.findAll().get(0));

        return codeUsers;
    }

    @ResponseBody
    @GetMapping("/api/codeUserEx1")
    public CodeUser getCodeUserEx1(HttpServletRequest request) {

        CodeUser codeUser = new CodeUser();

        codeUser.setCode(codeRepository.findAll().get(0));
        codeUser.setMember(memberRepository.getOne(Long.valueOf(2)));

        return codeUser;
    }

    @ResponseBody
    @GetMapping("/api/userEx1")
    public List<Member> getUserEx1(HttpServletRequest request) {

        return memberRepository.selectAllJPQL1();
    }

    @ResponseBody
    @GetMapping("/api/userEx2")
    public List<Member> getUserEx2(HttpServletRequest request) {

        return memberRepository.selectAllSQL1();
    }

    @ResponseBody
    @GetMapping("/api/userEx3")
    public List<Member> getUserEx3(HttpServletRequest request) {

        return memberRepository.selectSQLById2(Long.valueOf(3));
    }

    @ResponseBody
    @GetMapping("/api/userEx4")
    public List<Member> getUserEx4(HttpServletRequest request) {

        Member member = new Member();

        member.setId(Long.valueOf(2));

        return memberRepository.selectSQLById3(member);
    }

}
