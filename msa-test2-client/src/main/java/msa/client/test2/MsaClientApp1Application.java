package msa.client.test2;

//import msa.client.test1.vo.Member;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class MsaClientApp1Application  {

//	@Autowired
//	private MemberRepository memberRepository;

	public static void main(String[] args) {
		SpringApplication.run(MsaClientApp1Application.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		memberRepository.save(Member.join("member1"));
//		memberRepository.save(Member.join("member2"));
//	}

}
