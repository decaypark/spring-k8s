package server.auth.msa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@SpringBootApplication
public class AuthConfiguration extends AuthorizationServerConfigurerAdapter {
	
    @Autowired
	private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ResourceServerProperties resourceServerProperties;

    @Autowired
    public DataSource dataSource;

    @Bean
    public TokenStore JdbcTokenStore(DataSource dataSource ) {
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
    	// ?????? ?????? endpoint??? ?????? ????????? ????????????. 
        super.configure(endpoints);
        endpoints.accessTokenConverter(jwtAccessTokenConverter())
        		 .authenticationManager(authenticationManager)
//                 .tokenStore(JdbcTokenStore(dataSource))
        ;
    }

    @Override

    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception
    {
        oauthServer.checkTokenAccess("permitAll()");
    }
  	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// oauth_client_details ???????????? ????????? ???????????? ???????????????.
		clients.withClientDetails(clientDetailsService);
	}

	@Bean
	@Primary
	public JdbcClientDetailsService JdbcClientDetailsService(DataSource dataSource) {
    	// Jdbc(H2 ??????????????????)??? ????????? Oauth client ??????????????? ?????? ???????????????.
		return new JdbcClientDetailsService(dataSource);
	}
	
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
    	// JWT key-value ????????? ???????????? ?????? ???????????????.
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey(resourceServerProperties.getJwt().getKeyValue());
       
        return accessTokenConverter;
    }
	
}
