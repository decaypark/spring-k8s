package msa.api.user.controller;

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
}
