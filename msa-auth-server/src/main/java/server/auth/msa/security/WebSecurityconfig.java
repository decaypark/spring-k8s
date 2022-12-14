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
		// h2 ???????????? ?????????????????? h2-console url??? ????????? permitAll?????? ????????? ?????????. 
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
    	// custom user?????? ???????????? ?????????????????? ???????????????. 
    	builder.authenticationProvider(authenticationProvider());
    }


//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		// authenticationManage ??? ??????
//		return super.authenticationManagerBean();
//	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		// Spring5?????? PasswordEncoder ????????? ????????? ?????????????????? ?????????. 
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	// custom user?????? ???????????? ?????????????????? ???????????????. 
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userInformationService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

	//

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable() // csrf ????????? ?????? ?????? state ?????? ?????? ?????? ?????????
//				.formLogin() // ?????? ???????????? ????????? ?????? ??????
//				.and()
//				.httpBasic(); // http ???????????? basic auth??? ?????? ??? ??? ??????. (ex: Authorization: Basic bzFbdGfmZrptWY30YQ==)
//	}

	/**
	 * authenticationManager bean ?????? ?????? ?????? ????????? grant_type : password ?????? ??????
	 * @return
	 * @throws Exception
	 */
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
    
}
