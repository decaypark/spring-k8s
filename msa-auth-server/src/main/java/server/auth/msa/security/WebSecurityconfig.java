package server.auth.msa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

//@PropertySource("classpath:application.properties")
//@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityconfig extends WebSecurityConfigurerAdapter  {	
	
	@Autowired
	private UserInformationService userInformationService;

//	@Autowired
//	private CustomOAuth2UserService  customOAuth2UserService ;
//
//	private static List<String> clients = Arrays.asList("custom");
//
//	private static String CLIENT_PROPERTY_KEY
//			= "spring.security.oauth2.client.registration.";

//	@Autowired
//	private Environment env;

//	private ClientRegistration getRegistration(String client) {
////		String clientId = env.getProperty(
////				CLIENT_PROPERTY_KEY + client + ".client-id");
////
////		if (clientId == null) {
////			return null;
////		}
////
////		String clientSecret = env.getProperty(
////				CLIENT_PROPERTY_KEY + client + ".client-secret");
////
////		if (client.equals("google")) {
////			return CommonOAuth2Provider.GOOGLE.getBuilder(client)
////					.clientId(clientId).clientSecret(clientSecret).build();
////		}
////		if (client.equals("facebook")) {
////			return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
////					.clientId(clientId).clientSecret(clientSecret).build();
////		}
//		return null;
//	}

//	@Bean
//	public ClientRegistrationRepository clientRegistrationRepository() {
//		List<ClientRegistration> registrations = clients.stream()
//				.map(c -> getRegistration(c))
//				.filter(registration -> registration != null)
//				.collect(Collectors.toList());
//
//		return new InMemoryClientRegistrationRepository(registrations);
//	}

	@Override
    public void configure(HttpSecurity http) throws Exception {
		// h2 데이터를 확인하기위해 h2-console url의 권한을 permitAll으로 바꾸어 줍니다. 
		http.authorizeRequests()
				.antMatchers("/h2-console/**").permitAll()
				.and().formLogin()
				.loginPage("/oauth/login")
			.and().csrf().disable()
				.headers().disable()
				.httpBasic()
		;

        
    }


	
    @Override
    public void configure(AuthenticationManagerBuilder builder)
            throws Exception {
    	// custom user인증 서비스를 사용하기위한 설정입니다. 
    	builder.authenticationProvider(authenticationProvider());
    }


//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		// authenticationManage 빈 등록
//		return super.authenticationManagerBean();
//	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		// Spring5부터 PasswordEncoder 지정은 필수로 진행해주어야 합니다. 
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	// custom user인증 서비스를 사용하기위한 설정입니다. 
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userInformationService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

	//

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable() // csrf 공격을 막기 위해 state 값을 전달 받지 않는다
//				.formLogin() // 기본 제공하는 로그인 화면 사용
//				.and()
//				.httpBasic(); // http 통신으로 basic auth를 사용 할 수 있다. (ex: Authorization: Basic bzFbdGfmZrptWY30YQ==)
//	}

	/**
	 * authenticationManager bean 생성 하여 셋팅 안할시 grant_type : password 지원 안함
	 * @return
	 * @throws Exception
	 */
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
    
}
