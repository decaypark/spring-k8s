package msa.api.user;

import msa.api.user.controller.CodeRepository;
import msa.api.user.controller.MemberRepository;
import msa.api.user.vo.Code;
import msa.api.user.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.Date;


@SpringBootApplication
public class MsaUserApiApplication implements CommandLineRunner {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private CodeRepository codeRepository;

	public static void main(String[] args) {
		SpringApplication.run(MsaUserApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		memberRepository.save(Member.join("홍길동", Long.valueOf(10000), Calendar. getInstance().getTime()));
		memberRepository.save(Member.join("테스터", Long.valueOf(1234), Calendar. getInstance().getTime()));

		codeRepository.save(Code.join("code1", "codename1"));
	}

}
