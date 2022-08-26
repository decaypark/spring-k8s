package msa.client.test1.controller;

import msa.client.test1.vo.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/api/hello")
    public Member test() {

        Member member = new Member();

        member.setId(Long.valueOf(12345));
        member.setName("test1");

        return member;
    }
}
